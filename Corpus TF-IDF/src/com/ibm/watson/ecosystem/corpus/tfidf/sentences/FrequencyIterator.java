package com.ibm.watson.ecosystem.corpus.tfidf.sentences;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.function.Consumer;

public final class FrequencyIterator<K> implements Iterator<K> {
  
  protected FrequencyIterator(Hashtable<K, ?> aHashtable) {
    _keys = aHashtable.keys();
  }
  
  @Override
  public boolean hasNext() {
    return _keys.hasMoreElements();
  }
  
  @Override
  public K next() {
    return _keys.nextElement();
  }
  
  @Override
  public void remove() {
    throw new UnsupportedOperationException();
  }
  
  // ------------------------------------------------------------------------------------------ //
  // Private
  // ------------------------------------------------------------------------------------------ //
  private final Enumeration<K> _keys;

@Override
public void forEachRemaining(Consumer<? super K> action) {
	// TODO Auto-generated method stub
	
}
  
}
