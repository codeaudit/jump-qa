package com.ibm.watson.ecosystem.corpus.tfidf;

import com.ibm.watson.ecosystem.corpus.tfidf.corpus.TermCorpus;
import com.ibm.watson.ecosystem.corpus.tfidf.corpus.TermCorpusBuilder;

public final class Doc2Vec {

  public static void main(String[] args) {
    
    TermCorpusBuilder cb = new TermCorpusBuilder();
    cb.setDocumentCombiner(0, 0)
      .setJson("health-corpus.json");
    
    System.out.println("Building corpus.");
    TermCorpus c = cb.build();
    System.out.println(c.size());
    System.out.println("Corpus created.");
    
    c.genTerms();
    System.out.println(c.numTerms());
    System.out.println("Terms generated.");
    
    
    
    
  }

}
