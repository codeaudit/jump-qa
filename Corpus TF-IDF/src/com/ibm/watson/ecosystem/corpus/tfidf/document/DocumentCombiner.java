package com.ibm.watson.ecosystem.corpus.tfidf.document;

import java.util.ArrayList;
import java.util.List;

public class DocumentCombiner<E extends CombineableDocument> {
  
  public DocumentCombiner(Integer aLevel, Integer aMinFrequency) {
    _level = aLevel;
    _min = aMinFrequency;
  }
  
  public List<E> combine(List<E> aDocuments) {
    if (_level == -1) { return aDocuments; }
    
    List<E> base = getBase(aDocuments);
    
    List<String> contexts = getContexts(base);
    List<E> result = new ArrayList<E>();

    int numFiles = 0;
    for (E d : aDocuments) {
      int index = contexts.indexOf(d.getContext(_level).toLowerCase());
      if (index != -1) {
        base.get(index).combineWith(d);
      }
      if (++numFiles % 1000 == 0) {
        System.out.print(numFiles + " documents merged.\r");
      }
    }
    
    for (E d : base) {
      if (d.getNumDocs() >= _min) {
        result.add(d);
      }
    }
    
    System.out.println();
    
    return result;
  }
  
  // ------------------------------------------------------------------------------------------ //
  // Private
  // ------------------------------------------------------------------------------------------ //
  private final Integer _level;
  private final Integer _min;
  
  private List<E> getBase(List<E> aDocuments) {
    List<E> result = new ArrayList<E>();
    List<String> contexts = new ArrayList<String>();
    
    for (E document : aDocuments) {
      String context = document.getContext(_level).toLowerCase();
      if (contexts.indexOf(context) == -1) {
        contexts.add(context);
        result.add(document);
      }
    }
    return result;
  }
  
  private List<String> getContexts(List<E> aDocuments) {
    List<String> result = new ArrayList<String>();
    for (E document : aDocuments) {
      result.add(document.getContext(_level).toLowerCase());
    }
    return result;
  }
  
}
