package com.ibm.watson.ecosystem.corpus.tfidf.corpus;

import java.io.File;

import com.fasterxml.jackson.databind.JsonNode;
import com.ibm.watson.ecosystem.corpus.CorpusBuilder;
import com.ibm.watson.ecosystem.corpus.tfidf.document.CombineableDocument;
import com.ibm.watson.ecosystem.corpus.tfidf.document.CombineableDocumentFactory;
import com.ibm.watson.ecosystem.corpus.tfidf.document.DocumentCombiner;

public class CombineableCorpusBuilder extends CorpusBuilder<CombineableDocument, CombineableCorpus> {

  @Override
  public CombineableCorpus build() {
    if (_documents.size() == 0) {
      _documents = getDocumentsFromDirectory(_directory);
    }
    System.out.println("Combining Documents.");
    _documents = _documentCombiner.combine(_documents);
    CombineableCorpus result = new CombineableCorpus(_name, _documents);
    return result;
  }

  public CombineableCorpusBuilder setDocumentCombiner(int aLevel, int aMin) {
    return setDocumentCombiner(new DocumentCombiner<CombineableDocument>(aLevel, aMin));
  }
  
  public CombineableCorpusBuilder setDocumentCombiner(DocumentCombiner<CombineableDocument> aDocumentCombiner) {
    _documentCombiner = aDocumentCombiner;
    return this;
  }
  
  // ------------------------------------------------------------------------------------------ //
  // Private
  // ------------------------------------------------------------------------------------------ //
  private String _name = "";
  private DocumentCombiner<CombineableDocument> _documentCombiner = new DocumentCombiner<CombineableDocument>(-1, 0);
  
  @Override
  protected CombineableDocument getDocumentFromJson(JsonNode aNode) {
    return cdf.getDocument(aNode);
  }
  
  @Override
  protected CombineableDocument getDocumentFromFile(File aFile) {
    return new CombineableDocument(dr.read(aFile));
  }
  
  private static CombineableDocumentFactory cdf = new CombineableDocumentFactory();
  
  
}
