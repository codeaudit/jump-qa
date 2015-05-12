package com.ibm.watson.ecosystem.corpus.tfidf.document;

import com.fasterxml.jackson.databind.JsonNode;
import com.ibm.watson.ecosystem.corpus.document.BasicDocumentFactory;
import com.ibm.watson.ecosystem.corpus.document.Document;

public class CombineableDocumentFactory extends BasicDocumentFactory {
  
  @Override
  public CombineableDocument getDocument(JsonNode aNode) {
    Document d = super.getDocument(aNode);
    return new CombineableDocument(d);
  }
  
}
