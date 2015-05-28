/**
 * 
 */
package com.ibm.watson.catalyst.jumpqa.replacer;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.ibm.watson.catalyst.jumpqa.replacer.ConstReplacer;

@SuppressWarnings("javadoc")
public class ConstReplacerTest {
  
  ConstReplacer r1;
  
  @Before
  public void setUp() {
    r1 = new ConstReplacer("pattern", "replacement");
  }
  
  @Test
    public void testReplace() {
      ConstReplacer r = new ConstReplacer("a", "__");
      String testInput = "alpha";
      String expectedOutput = "__lph__";
      assertThat(r.replace(testInput), equalTo(expectedOutput));
    }
  
  @Test
  public void testEqualsRelexive() {
    assertThat(r1, equalTo(r1));
  }
  
  @Test
  public void testEqualsSymmetric() {
    ConstReplacer r2 = new ConstReplacer("pattern", "replacement");
    assertThat(r1, equalTo(r2));
  }
  
  @Test
  public void testNotEqualsPattern() {
    ConstReplacer r2 = new ConstReplacer("another pattern", "replacement");
    assertThat(r1, not(equalTo(r2)));
  }
  
  @Test
  public void testNotEqualsReplacement() {
    ConstReplacer r2 = new ConstReplacer("pattern", "another replacement");
    assertThat(r1, not(equalTo(r2)));
  }
  
  @Test
  public void testHashRelexive() {
    assertThat(r1.hashCode(), equalTo(r1.hashCode()));
  }
  
  @Test
  public void testHashSymmetric() {
    ConstReplacer r2 = new ConstReplacer("pattern", "replacement");
    assertThat(r1.hashCode(), equalTo(r2.hashCode()));
  }
  
  @Test
  public void testHashNotEqualsPattern() {
    ConstReplacer r2 = new ConstReplacer("another pattern", "replacement");
    assertThat(r1.hashCode(), not(equalTo(r2.hashCode())));
  }
  
  @Test
  public void testHashNotEqualsReplacement() {
    ConstReplacer r2 = new ConstReplacer("pattern", "another replacement");
    assertThat(r1.hashCode(), not(equalTo(r2.hashCode())));
  }
  
}
