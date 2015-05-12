package com.ibm.watson.ecosystem.corpus.tfidf.document;

import com.fasterxml.jackson.databind.JsonNode;
import com.ibm.watson.ecosystem.corpus.document.Document;

public class TermDocumentFactory extends CombineableDocumentFactory {
  
  @Override
  public TermDocument getDocument(JsonNode aNode) {
    Document d = super.getDocument(aNode);
    return new TermDocument(d);
  }
  
}
