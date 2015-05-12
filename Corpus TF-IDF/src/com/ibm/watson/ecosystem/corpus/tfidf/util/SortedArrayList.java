package com.ibm.watson.ecosystem.corpus.tfidf.util;

import java.util.ArrayList;

@SuppressWarnings("serial")
public final class SortedArrayList<E> extends ArrayList<E> {
  
  public SortedArrayList() {
    super();
    _min = 0.0;
  }
  
  public SortedArrayList(Integer min) {
    this((double) min.intValue());
  }
  
  public SortedArrayList(Double min) {
    super();
    _min = min;
  }
  
  public boolean sortedAdd(E element, Double value) {
    if (value < _min) return false;
    int index = 0;
    while (index < size()) {
      if (value > _values.get(index)) break;
      index++;
    }
    this.add(index, element);
    _values.add(index, value);
    return true;
  }
  
  public boolean sortedAdd(E element, Integer value) {
    return this.sortedAdd(element, (double) value.intValue());
  }
  
  public Double getValue(int index) {
    return _values.get(index);
  }
  
  private final Double _min;
  private final ArrayList<Double> _values = new ArrayList<Double>();
  
  
}
