package com.ibm.watson.ecosystem.corpus.tfidf.corpus;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;

import com.ibm.watson.ecosystem.corpus.Corpus;
import com.ibm.watson.ecosystem.corpus.tfidf.document.TermDocument;

public final class TermCorpus extends Corpus<TermDocument> {
  
  public TermCorpus(String aName, List<TermDocument> aDocuments) {
    super(aName, aDocuments);
  }

  public int frequency(String aString) {
    int result = 0;
    for (TermDocument d : _documents) {
      result += d.frequency(aString);
    }
    return result;
  }
  
  private double idf(String aString) {
    int N = _documents.size();
    assert (N > 0) : "There must be documents.";
    
    int docFreq = _df.get(aString);
    
    if (docFreq == 0) {
      System.out.println(aString);
      return 0;
    }
    return Math.log(N / docFreq);
  }
  
  public int size() {
    return _documents.size();
  }
  
  public int numTerms() {
    return _words.size();
  }
  
  public void genTerms() {
    int index = 0;
    for (TermDocument d : getDocuments()) {
      for (String word : d.getFrequencies()) {
        int newdf;
        if (_words.add(word)) {
          newdf = 1;
        } else {
          newdf = _df.get(word) + 1;
        }
        _df.put(word, newdf);
      }
      if (++index % 100 == 0) System.out.print(index + " documents' term sets merged.\r");
    }
    System.out.println(index + " documents' term frequencies merged.\r");
  }
  
  public double getIdf(String term) {
    return _idfs.get(term);
  }
  
  public void genIdfs() {
    System.out.println("Generating idfs for " + _words.size() + " terms.");
    //int index = 0;
    for (String term : _words) {
      _idfs.put(term, idf(term));
      //if (++index % 1000 == 0) System.out.print(index + " idfs calculated.\r");
    }
    System.out.println(_idfs.size() + " idfs calculated.");
  }
  
  public HashSet<String> getWords() { return _words; }
  
  // ------------------------------------------------------------------------------------------ //
  // Private
  // ------------------------------------------------------------------------------------------ //
  //private final WordFrequencyHashtable _frequencies = new WordFrequencyHashtable();
  private final HashSet<String> _words = new HashSet<String>();
  private final Hashtable<String, Integer> _df = new Hashtable<String, Integer>();
  private final Hashtable<String, Double> _idfs = new Hashtable<String, Double>();
  
}
