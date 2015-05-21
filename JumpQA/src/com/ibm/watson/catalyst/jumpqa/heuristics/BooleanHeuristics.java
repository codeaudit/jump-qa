/*******************************************************************************
 * Copyright 2015 IBM Corporation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
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
