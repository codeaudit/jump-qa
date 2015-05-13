package com.ibm.watson.catalyst.corpus;

import java.io.File;

import com.fasterxml.jackson.databind.JsonNode;
import com.ibm.watson.catalyst.corpus.BasicCorpus;
import com.ibm.watson.catalyst.corpus.CorpusBuilder;
import com.ibm.watson.catalyst.corpus.document.BasicDocumentFactory;
import com.ibm.watson.catalyst.corpus.document.Document;

/**
 * A builder object which creates a BasicCorpus object.
 * @author wabeason
 *
 */
public class BasicCorpusBuilder extends CorpusBuilder<Document, BasicCorpus> {
  
  @Override
  public BasicCorpus build() {
    if (_documents.size() == 0) {
      _documents = getDocumentsFromDirectory(_directory);
    }
    return new BasicCorpus(_name, _documents);
  }
  
  @Override
  protected Document getDocumentFromJson(JsonNode aNode) {
    return bdf.getDocument(aNode);
  }
  
  private static BasicDocumentFactory bdf = new BasicDocumentFactory();

  @Override
  protected Document getDocumentFromFile(File aFile) {
    return dr.read(aFile);
  }
  
}
