package com.ibm.watson.catalyst.jumpqa.splitter;

import static org.junit.Assert.*;

import org.junit.Test;

public class WordSplitterTest {

  @Test
  public void testSplit() {
    String aString = "Some words.";
    String[] aStringSplit = new String[] { "Some", "words." };
    assertArrayEquals(WordSplitter.split(aString), aStringSplit);
    
    String[] x = WordSplitter.split("");
    System.out.println(x.length);
  }

}
