package com.ibm.watson.catalyst.corpus.tfidf.document;

import com.fasterxml.jackson.databind.JsonNode;
import com.ibm.watson.catalyst.corpus.document.BasicDocumentFactory;
import com.ibm.watson.catalyst.corpus.document.Document;

public class CombineableDocumentFactory extends BasicDocumentFactory {
  
  @Override
  public CombineableDocument getDocument(JsonNode aNode) {
    Document d = super.getDocument(aNode);
    return new CombineableDocument(d);
  }
  
}
