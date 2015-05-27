/**
 * 
 */
package com.ibm.watson.catalyst.jumpqa.wordreplacer;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.equalTo;
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
    assertThat(r.replaceAll(testInput), equalTo(expectedOutput));
  }
  
  @Test
  public void testEqualsRelexive() {
    assertThat(r1, equalTo(r1));
  }
  
  @Test
  public void testEqualsSymmetric() {
    Replacer r2 = new Replacer("pattern", "replacement");
    assertThat(r1, equalTo(r2));
  }
  
  @Test
  public void testNotEqualsPattern() {
    Replacer r2 = new Replacer("another pattern", "replacement");
    assertThat(r1, not(equalTo(r2)));
  }
  
  @Test
  public void testNotEqualsReplacement() {
    Replacer r2 = new Replacer("pattern", "another replacement");
    assertThat(r1, not(equalTo(r2)));
  }
  
  @Test
  public void testHashRelexive() {
    assertThat(r1.hashCode(), equalTo(r1.hashCode()));
  }
  
  @Test
  public void testHashSymmetric() {
    Replacer r2 = new Replacer("pattern", "replacement");
    assertThat(r1.hashCode(), equalTo(r2.hashCode()));
  }
  
  @Test
  public void testHashNotEqualsPattern() {
    Replacer r2 = new Replacer("another pattern", "replacement");
    assertThat(r1.hashCode(), not(equalTo(r2.hashCode())));
  }
  
  @Test
  public void testHashNotEqualsReplacement() {
    Replacer r2 = new Replacer("pattern", "another replacement");
    assertThat(r1.hashCode(), not(equalTo(r2.hashCode())));
  }
  
}
