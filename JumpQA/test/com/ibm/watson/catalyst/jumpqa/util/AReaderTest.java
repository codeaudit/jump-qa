/**
 * 
 */
package com.ibm.watson.catalyst.jumpqa.util;

import static org.junit.Assert.*;

import org.junit.Test;


@SuppressWarnings("javadoc")
public class AReaderTest {
  
  @Test
  public void testIsBlankComment() {
    assertTrue(AReader.isComment("#"));
  }
  
  @Test
  public void testIsNonblankComment() {
    assertTrue(AReader.isComment("#comment"));
  }
  
  @Test
  public void testIsNotCommentEmpty() {
    assertFalse(AReader.isComment(""));
  }
  
  @Test
  public void testIsNotComment() {
    assertFalse(AReader.isComment("some text#"));
  }
  
  @Test
  public void testIsEmpty() {
    assertTrue(AReader.isCommentOrEmpty(""));
  }
  
}
