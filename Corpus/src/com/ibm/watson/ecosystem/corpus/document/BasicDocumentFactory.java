package com.ibm.watson.ecosystem.corpus.document;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

public class BasicDocumentFactory extends DocumentFactory<Document> {
  
  public Document getDocument(JsonNode aNode) {
    File aFile = new File(aNode.get("file").asText());
    String aPauID = aNode.get("pauID").asText();
    String aPauTitle = aNode.get("pauTitle").asText();
    String aSourceDoc = aNode.get("sourceDoc").asText();
    List<String> aParagraphs = getParagraphs(aNode);
    return new Document(aFile, aPauID, aPauTitle, aSourceDoc, aParagraphs);
  }
  
  private static List<String> getParagraphs(JsonNode aNode) {
    List<String> result = new ArrayList<String>();
    for (JsonNode paragraph : aNode.get("paragraphs")) {
      result.add(paragraph.asText());
    }
    return result;
  }
  
}
