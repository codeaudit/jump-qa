package com.ibm.watson.ecosystem.util.random.lists;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

import com.ibm.watson.ecosystem.util.random.RandInt;

public class RandomIterator<E> implements Iterator<E> {
  
  public RandomIterator(List<E> aList) {
    _list = aList;
    _index = aList.size();
    _random = new RandInt();
  }
  
  public boolean hasNext() {
    return (_index > 0);
  }
  
  public E next() {
    _index--;
    swapRandomIndex();
    return _list.get(_index);
  }
  
  public void remove() {
    _list.remove(_index);
  }
  
  // Delegate seed setting to _random
  public void setSeed(long aSeed) {
    _random.setSeed(aSeed);
  }
  
  // ------------------------------------------------------------------------------------------ //
  // Private
  // ------------------------------------------------------------------------------------------ //
  private int _index;
  private final List<E> _list;
  private final RandInt _random; // the object which generates indexes
  
  private void swapRandomIndex() {
    int position = _random.randInt(_index + 1);
    Collections.swap(_list, position, _index);
  }

  @Override
  public void forEachRemaining(Consumer<? super E> action) {
	while(hasNext()) {
		action.accept(next());
	}
  }
  
}
