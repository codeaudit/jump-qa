package com.ibm.watson.ecosystem.corpus.tfidf.document;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.ibm.watson.ecosystem.corpus.document.Document;
import com.ibm.watson.ecosystem.corpus.tfidf.sentences.WordFrequencyBuilder;
import com.ibm.watson.ecosystem.corpus.tfidf.sentences.WordFrequencyHashtable;

public final class TermDocument extends CombineableDocument {
  
  public TermDocument(Document aDocument) {
    super(aDocument);
    _frequencies = (new WordFrequencyBuilder(_paragraphs)).build();
    _maxFrequency = _frequencies.getMaxFrequency();
  }
  
  public TermDocument(String aPauTitle) {
    this(aPauTitle, new ArrayList<String>());
  }
  
  public TermDocument(String aPauTitle, List<String> aParagraphs) {
    this(null, null, aPauTitle, aParagraphs);
  }
  
  public TermDocument(File aFile, String aPauID, String aPauTitle, List<String> aParagraphs) {
    super(aFile, aPauID, aPauTitle, aParagraphs);
    _frequencies = (new WordFrequencyBuilder(_paragraphs)).build();
    _maxFrequency = _frequencies.getMaxFrequency();
  }
  
  public boolean containsTerm(String aString) {
    return (_frequencies.get(aString) != 0);
  }
  
  public int frequency(String aString) {
    return _frequencies.get(aString);
  }
  
  public double augmentedFrequency(String aString) {
    double result = 0.5;
    result += 0.5 * frequency(aString) / _maxFrequency;
    return result;
  }
  
  @Override
  protected void addParagraphs(List<String> newParagraphs) {
    super.addParagraphs(newParagraphs);
    _frequencies.addAll((new WordFrequencyBuilder(newParagraphs)).build());
    _maxFrequency = _frequencies.getMaxFrequency();
  }
  
  public WordFrequencyHashtable getFrequencies() { return _frequencies; }
  
  // ------------------------------------------------------------------------------------------ //
  // Private
  // ------------------------------------------------------------------------------------------ //
  private final WordFrequencyHashtable _frequencies;
  private int _maxFrequency;
  
}
