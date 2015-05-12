package com.ibm.watson.catalyst.jumpqa.trec;

import java.util.List;

public class Trec {
  
  public Trec(String aFile, String aPauId,
      String aPauTitle, String aSourceDoc, List<String> paragraphs) {
    _file = aFile;
    _pauId = aPauId;
    _pauTitle = aPauTitle;
    _sourceDoc = aSourceDoc;
    _paragraphs = paragraphs;
  }
  
  public String getFile() {
    return _file;
  }
  public String getPauId() {
    return _pauId;
  }
  public String getPauTitle() {
    return _pauTitle;
  }
  public String getSourceDoc() {
    return _sourceDoc;
  }
  public List<String> getParagraphs() {
    return _paragraphs;
  }

  private final String _file;
  private final String _pauId;
  private final String _pauTitle;
  private final String _sourceDoc;
  private final List<String> _paragraphs;
  
}
