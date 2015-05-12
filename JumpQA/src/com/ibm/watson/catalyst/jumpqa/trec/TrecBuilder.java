package com.ibm.watson.catalyst.jumpqa.trec;

import java.util.List;

public class TrecBuilder {
  
  public void setFile(String aFile) { _file = aFile; }
  public void setPauId(String aPauId) { _pauId = aPauId; }
  public void setPauTitle(String aPauTitle) { _pauTitle = aPauTitle; }
  public void setSourceDoc(String aSourceDoc) { _sourceDoc = aSourceDoc; }
  public void setParagraphs(List<String> aParagraphs) { _paragraphs = aParagraphs; }
  
  public Trec build() {
    return new Trec(_file, _pauId, _pauTitle, _sourceDoc, _paragraphs);
  }
  
  private String _file;
  private String _pauId;
  private String _pauTitle;
  private String _sourceDoc;
  private List<String> _paragraphs;
  
}
