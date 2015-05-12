package com.ibm.watson.catalyst.jumpqa.heuristics;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class BooleanHeuristics<T> implements IHeuristics<T, Boolean> {
  
  public BooleanHeuristics() { }
  
  public final List<Boolean> evaluate(T input) {
    List<Boolean> result = new ArrayList<Boolean>();
    _predicates.forEach((p) -> p.test(input));
    return result;
  }
  
  public final boolean anyTrue(T input) {
    for (Predicate<T> p : _predicates) {
      if (p.test(input)) return true;
    }
    return false;
  }

  public final boolean allTrue(T input) {
    for (Predicate<T> p : _predicates) {
      if (!p.test(input)) return false;
    }
    return true;
  }
  
  public boolean add(Predicate<T> aPredicate) {
    return _predicates.add(aPredicate);
  }
  
  private final List<Predicate<T>> _predicates = new ArrayList<Predicate<T>>();
  
}
