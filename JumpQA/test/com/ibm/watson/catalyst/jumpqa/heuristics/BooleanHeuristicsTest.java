package com.ibm.watson.catalyst.jumpqa.heuristics;

import java.util.function.Predicate;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

@SuppressWarnings("javadoc")
public class BooleanHeuristicsTest {
  
  BooleanHeuristics<Integer> b0;
  BooleanHeuristics<Integer> b1;
  BooleanHeuristics<Integer> b1a;
  BooleanHeuristics<Integer> b2;
  
  Predicate<Integer> p0 = (s) -> false;
  Predicate<Integer> p1 = (s) -> true;
  Predicate<Integer> p2 = (s) -> s > 0;
  Predicate<Integer> p3 = (s) -> s < 10;
  
  {
    b0 = new BooleanHeuristics<Integer>();
    
    b1 = new BooleanHeuristics<Integer>();
    b1.add(p1);
    
    b1a = new BooleanHeuristics<Integer>();
    b1a.add(p1);
    
    b2 = new BooleanHeuristics<Integer>();
    b2.add(p2);
    
  }
  
  @Before
  public void setUp() {
    
  }
  
  @Test
  public void testEqualsReflexive() {
    assertThat(b1, equalTo(b1));
  }
  
  @Test
  public void testEqualsSymmetric() {
    assertThat(b1, equalTo(b1a));
  }
  
  @Test
  public void testNotEquals() {
    assertThat(b1, not(equalTo(b2)));
  }
  
  @Test
  public void testHashCodeEqualsReflexive() {
    assertThat(b1.hashCode(), equalTo(b1.hashCode()));
  }
  
  @Test
  public void testHashCodeEqualsSymmetric() {
    assertThat(b1.hashCode(), equalTo(b1a.hashCode()));
  }
  
  @Test
  public void testHashCodeNotEquals() {
    assertThat(b1.hashCode(), not(equalTo(b2).hashCode()));
  }
  
}
