package com.ibm.watson.ecosystem.corpus.tfidf.ngrams;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ibm.watson.ecosystem.corpus.tfidf.document.TermDocument;
import com.ibm.watson.ecosystem.corpus.tfidf.sentences.WordFrequencyHashtable;

public final class DocumentNgrammer {
  
  public DocumentNgrammer(TermDocument aDocument, String aWord) {
    _word = capitalize(aWord);
    List<String> paragraphs = aDocument.getParagraphs();
    
    for (String paragraph : paragraphs) {
      _frequencies.putAll(paragraphBigrams(paragraph));
    }
    
  }
  
  public WordFrequencyHashtable getFrequencies() { return _frequencies; }
  
  private final WordFrequencyHashtable _frequencies = new WordFrequencyHashtable();
  private final String _word;
  
  private final Pattern sentenceEnd = Pattern.compile("(?<=([A-Z]{2}|[a-z]{2}))[\\.\\!\\?]");
  private WordFrequencyHashtable paragraphBigrams(String aParagraph) {
    WordFrequencyHashtable result = new WordFrequencyHashtable();
    String[] sentences = sentenceEnd.split(aParagraph);
    for (String sentence : sentences) {
      result.putAll(sentenceBigrams(sentence));
    }
    return result;
  }

  private static Pattern word = Pattern.compile("(\\w|'\\w)+");
  private WordFrequencyHashtable sentenceBigrams(String aSentence) {
    WordFrequencyHashtable result = new WordFrequencyHashtable();
    List<String> words = new ArrayList<String>();
    
    Matcher m = word.matcher(aSentence);
    while (m.find()) words.add(aSentence.substring(m.start(), m.end()));
    
    if (words.size() < 2) return result;
    
    String firstWord;
    String secondWord = capitalize(words.get(0));
    boolean flag = true;
    for (int i = 0; i < words.size() - 1; i++) {
      firstWord = secondWord;
      secondWord = capitalize(words.get(i + 1));
      if (firstWord.equals(_word) || secondWord.equals(_word)) {
        String bigram = firstWord + " " + secondWord;
        result.put(bigram, 1);
        if (flag) {
          System.out.println(aSentence);
          flag = false;
        }
      }
    }
    
    return result;
  }
  
  private static String capitalize(String s) {
    return s.substring(0,1).toUpperCase() + s.substring(1);
  }
  
}
