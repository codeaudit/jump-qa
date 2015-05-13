package com.ibm.watson.catalyst.corpus.document;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.ibm.watson.catalyst.corpus.document.Document;
import com.ibm.watson.catalyst.corpus.document.DocumentBuilder;

public class DocumentBuilder {
  
  public DocumentBuilder setFile(File aFile) {
    _file = aFile;
    return this;
  }
  
  public DocumentBuilder setPauID(String pauID) {
    _pauID = pauID;
    return this;
  }
  
  public DocumentBuilder setPauTitle(String pauTitle) {
    _pauTitle = pauTitle;
    return this;
  }
  
  public DocumentBuilder setSourceDoc(String aSourceDoc) {
    _sourceDoc = aSourceDoc;
    return this;
  }
  
  public DocumentBuilder setParagraphs(List<String> paragraphs) {
    _paragraphs = paragraphs;
    return this;
  }
  
  public Document build() {
    return new Document(_file, _pauID, _pauTitle, _sourceDoc, _paragraphs);
  }
  
  // ------------------------------------------------------------------------------------------ //
  // Private
  // ------------------------------------------------------------------------------------------ //
  private File _file = new File("");
  private String _pauID = "";
  private String _pauTitle = "";
  private String _sourceDoc = "";
  private List<String> _paragraphs = new ArrayList<String>();
  
}
