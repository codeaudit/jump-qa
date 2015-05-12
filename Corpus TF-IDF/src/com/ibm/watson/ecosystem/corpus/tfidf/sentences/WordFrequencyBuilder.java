package com.ibm.watson.ecosystem.corpus.tfidf.sentences;

import java.util.List;
import java.util.regex.Pattern;

public class WordFrequencyBuilder {
  
  public WordFrequencyBuilder(List<String> aParagraphs) {
    _paragraphs = aParagraphs;
  }
  
  public WordFrequencyHashtable build() {
    WordFrequencyHashtable result = new WordFrequencyHashtable();
    for (String paragraph : _paragraphs) {
      result.addAll(genFrequenciesFromParagraph(paragraph));
    }
    return result;
  }
  
  // ------------------------------------------------------------------------------------------ //
  // Private
  // ------------------------------------------------------------------------------------------ //
  private List<String> _paragraphs;
  
  private static Pattern wordSplit = Pattern.compile("[\\s\\.!?\\(\\)\\[\\].\";:/,]+");
  private static Pattern letters = Pattern.compile("[A-Za-z]");
  private static WordFrequencyHashtable genFrequenciesFromParagraph(String paragraph) {
    WordFrequencyHashtable result = new WordFrequencyHashtable();
    
    String[] words = wordSplit.split(paragraph);
    for (String newWord : words) {
      if (newWord.length() <= 2) continue;
      if (!letters.matcher(newWord).find()) continue;
      result.add(newWord);
    }
    
    return result;
  }
  
  
}
