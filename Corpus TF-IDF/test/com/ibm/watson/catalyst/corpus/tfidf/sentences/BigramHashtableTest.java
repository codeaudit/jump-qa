package com.ibm.watson.catalyst.corpus.tfidf.sentences;

import static org.junit.Assert.*;

import org.junit.Test;

public class BigramHashtableTest {

  @Test
  public void testPutBigramInt() {
    BigramHashtable bh = new BigramHashtable();
    
    Bigram b = new Bigram("a", "b");
    bh.add(b);
    assertTrue(b.equals(b));
    assertTrue(bh.contains(b));
    assertEquals(bh.get(b), (Integer) 1);
    bh.add(b);
    assertEquals(bh.get(b), (Integer) 2);
    
  }

  @Test
  public void testPutStringStringInt() {
  }

  @Test
  public void testGetStringString() {
  }

  @Test
  public void testAddStringString() {
  }

  @Test
  public void testBfipf() {
  }

  @Test
  public void testToJson() {
  }

}
