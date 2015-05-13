package com.ibm.watson.catalyst.corpus.tfidf.sentences;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class WordSet {
  
  private static Pattern wordBoundary = Pattern.compile("[\\(\\)\\s\\.,]+");
  public WordSet(String aSentence) {
    String[] words = wordBoundary.split(aSentence.toLowerCase());
    for (String word : words) {
      if (!isBadWord(word)) {
        add(word);
      }
    }
  }
  
  public List<Bigram> genBigrams() {
    List<Bigram> result = new ArrayList<Bigram>();
    
    for (int i = 0; i < (_words.size() - 1); i++) {
      for (int j = i + 1; j < _words.size(); j++) {
        result.add(new Bigram(_words.get(i), _words.get(j)));
      }
    }
    
    return result;
  }
  
  public int size() {
    return _words.size();
  }
  
  // ------------------------------------------------------------------------------------------ //
  // Private
  // ------------------------------------------------------------------------------------------ //
  private final List<String> _words = new ArrayList<String>();
  private static final Pattern cite = Pattern.compile("[\\(\\[][-\\d]+[\\]\\)]");
  
  private boolean isBadWord(String aWord) {
    return cite.matcher(aWord).find();
  }
  
  private boolean add(String s) {
    return _words.contains(s) ? false : _words.add(s);
  }
  
}
