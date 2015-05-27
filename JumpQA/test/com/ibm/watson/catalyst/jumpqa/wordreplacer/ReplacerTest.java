/**
 * 
 */
package com.ibm.watson.catalyst.jumpqa.wordreplacer;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

@SuppressWarnings("javadoc")
public class ReplacerTest {
  
  Replacer r1;
  
  @Before
  public void setUp() {
    r1 = new Replacer("pattern", "replacement");
  }
  
  @Test
  public void testReplaceAll() {
    Replacer r = new Replacer("a", "__");
    String testInput = "alpha";
    String expectedOutput = "__lph__";
    assertEquals(expectedOutput, r.replaceAll(testInput));
  }
  
  @Test
  public void testEqualsRelexive() {
    assertTrue(r1.equals(r1));
  }
  
  @Test
  public void testEqualsSymmetric() {
    Replacer r2 = new Replacer("pattern", "replacement");
    assertTrue(r1.equals(r2));
  }
  
  @Test
  public void testNotEqualsPattern() {
    Replacer r2 = new Replacer("another pattern", "replacement");
    assertFalse(r1.equals(r2));
  }
  
  @Test
  public void testNotEqualsReplacement() {
    Replacer r2 = new Replacer("pattern", "another replacement");
    assertFalse(r1.equals(r2));
  }
  
  @Test
  public void testHashRelexive() {
    assertTrue(r1.hashCode() == r1.hashCode());
  }
  
  @Test
  public void testHashSymmetric() {
    Replacer r2 = new Replacer("pattern", "replacement");
    assertTrue(r1.hashCode() == r2.hashCode());
  }
  
  @Test
  public void testHashNotEqualsPattern() {
    Replacer r2 = new Replacer("another pattern", "replacement");
    assertFalse(r1.hashCode() == r2.hashCode());
  }
  
  @Test
  public void testHashNotEqualsReplacement() {
    Replacer r2 = new Replacer("pattern", "another replacement");
    assertFalse(r1.hashCode() == r2.hashCode());
  }
  
}
