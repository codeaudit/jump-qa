package com.ibm.watson.ecosystem.util.random.lists;

import java.util.ArrayList;

public class RandomArrayList<E> extends ArrayList<E> {

  // ------------------------------------------------------------------------------------------ //
  // Public
  // ------------------------------------------------------------------------------------------ //
  @Override
  public RandomIterator<E> iterator() {
    return new RandomIterator<E>(this);
  }
  
  // ------------------------------------------------------------------------------------------ //
  // Private
  // ------------------------------------------------------------------------------------------ //
  private static final long serialVersionUID = 203188804783113876L;
  
}
