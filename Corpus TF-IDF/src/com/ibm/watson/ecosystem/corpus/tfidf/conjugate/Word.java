package com.ibm.watson.ecosystem.corpus.tfidf.conjugate;

public abstract class Word {
  
  public Word(String aBaseWord) {
    _baseWord = aBaseWord;
    _word = _baseWord;
  }
  
  protected String getBaseWord() { return _baseWord; }
  
  protected String _word;
  private final String _baseWord;
  
}
