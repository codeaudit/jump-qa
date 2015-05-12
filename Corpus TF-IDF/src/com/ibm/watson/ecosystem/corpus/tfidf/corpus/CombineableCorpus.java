package com.ibm.watson.ecosystem.corpus.tfidf.corpus;

import java.util.List;

import com.ibm.watson.ecosystem.corpus.Corpus;
import com.ibm.watson.ecosystem.corpus.tfidf.document.CombineableDocument;

public final class CombineableCorpus extends Corpus<CombineableDocument> {
  
  public CombineableCorpus(String aName, List<CombineableDocument> aDocuments) {
    super(aName, aDocuments);
  }
  
}
