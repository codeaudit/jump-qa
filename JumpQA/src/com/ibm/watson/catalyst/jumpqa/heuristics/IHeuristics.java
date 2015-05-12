package com.ibm.watson.catalyst.jumpqa.heuristics;

import java.util.List;

public interface IHeuristics<T, U> {
  
  public List<U> evaluate(T input);
  
}
