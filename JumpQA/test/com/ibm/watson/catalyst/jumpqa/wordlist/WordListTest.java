package com.ibm.watson.catalyst.jumpqa.wordlist;

import org.junit.Before;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

@SuppressWarnings("javadoc")
public class WordListTest {
  
  List<String> words;
  WordList wl;
  
  @Before
  public void setUp() {
    words = new ArrayList<String>();
    words.add("stuff");
    words.add("more");
    
    wl = new WordList(words);
  }
  
  @Test
  public void testWordListListConstuctor() {
    assertEquals(words, wl.getList());
  }
  
  @Test
  public void testContains() {
    assertTrue(wl.contains("stuff"));
  }
  
  @Test
  public void testContainsCaps() {
    assertTrue(wl.contains("StuFf"));
  }
  
  @Test
  public void testContainsFirstWord() {
    assertTrue(wl.containsFirstWord("Stuff is a"));
  }
  
  @Test
  public void testContainsFirstWordPunctBefore() {
    assertTrue(wl.containsFirstWord("? Stuff is a "));
  }
  
  @Test
  public void testContainsLastWord() {
    assertTrue(wl.containsLastWord("some more"));
  }
  
  @Test
  public void testContainsLastWordPunctAfter() {
    assertTrue(wl.containsLastWord("some more?"));
  }
  
}
