package com.ibm.watson.catalyst.corpus.tfidf.sentences;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.Before;

import com.ibm.watson.catalyst.corpus.tfidf.sentences.WordFrequencyHashtable;

public class WordFrequencyHashtableTest {
  
  WordFrequencyHashtable wfh;
  
  @Before
  public void init() {
    wfh = new WordFrequencyHashtable();
    wfh.add("the");
  }
  
  @Test
  public void testGetObject() {
    assertEquals((int) wfh.get("the"), 1);
  }

  @Test
  public void testPutStringInteger() {
    wfh.put("apple", 2);
    assertEquals((int) wfh.get("apple"), 2);
  }

  @Test
  public void testSortedIteratorInteger() {
    
  }

  @Test
  public void testSortedIterator() {
    
  }

  @Test
  public void testToJsonNode() {
    
  }

  @Test
  public void testGetMaxFrequency() {
    assertEquals(wfh.getMaxFrequency(), 1);
  }

  @Test
  public void testAddK() {
    wfh.add("the");
    wfh.add("the");
    wfh.add("apple");
    
    assertEquals((int) wfh.get("the"), 3);
    assertEquals((int) wfh.get("apple"), 1);
  }

  @Test
  public void testAddKInteger() {
    wfh.add("the", 10);
    wfh.add("apple", 5);
    
    assertEquals((int) wfh.get("the"), 11);
    assertEquals((int) wfh.get("apple"), 5);
  }

  @Test
  public void testAddAllListOfK() {
    List<String> strings = new ArrayList<String>();
    
    strings.add("The");
    strings.add("APPLE");
    strings.add("orange");
    strings.add("THE");
    
    wfh.addAll(strings);
    
    assertEquals((int) wfh.get("the"), 3);
    assertEquals((int) wfh.get("orange"), 1);
    assertEquals((int) wfh.get("apple"), 1);
  }

  @Test
  public void testAddAllFrequencyHashtableOfK() {
    WordFrequencyHashtable wfh2 = new WordFrequencyHashtable();
    
    wfh2.put("the", 6);
    wfh2.add("apples", 2);
    
    wfh.addAll(wfh2);
    
    assertEquals((int) wfh.get("the"), 7);
    assertEquals((int) wfh.get("apples"), 2);
    
  }

  @Test
  public void testContainsObject() {
    assertTrue(wfh.contains("the"));
    assertTrue(wfh.contains("THE"));
    assertFalse(wfh.contains("apple"));
  }

}
