package com.ibm.watson.ecosystem.util.random.lists;

import static org.junit.Assert.*;

import org.junit.Test;

public class RandomArrayListTest {

  @Test
  public void test() {
    
    RandomArrayList<Integer> ral = new RandomArrayList<Integer>();
    for (int i = 0; i < 5; i++) {
      ral.add(i);
    }
    
    RandomIterator<Integer> rali = ral.iterator();
    rali.setSeed(0);
    assertTrue(rali.next() == 0);
    assertTrue(rali.next() == 3);
    assertTrue(rali.next() == 1);
    assertTrue(rali.next() == 2);
    assertTrue(rali.next() == 4);
    assertFalse(rali.hasNext());
    
    rali = ral.iterator();
    rali.setSeed(1);
    assertTrue(rali.next() == 4);
    assertTrue(rali.next() == 0);
    assertTrue(rali.next() == 2);
    assertTrue(rali.next() == 3);
    assertTrue(rali.next() == 1);
    assertFalse(rali.hasNext());
    
    
  }

}
