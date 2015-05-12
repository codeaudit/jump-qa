package com.ibm.watson.ecosystem.corpus;

import java.util.List;

import com.ibm.watson.ecosystem.corpus.document.Document;

/**
 * A basic representation of a corpus populated with basic document objects.
 * @author wabeason
 *
 */
public final class BasicCorpus extends Corpus<Document> {

  public BasicCorpus(String aName, List<Document> aDocuments) {
    super(aName, aDocuments);
  }

}
