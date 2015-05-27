package com.ibm.watson.catalyst.jumpqa.matcher;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

import java.util.regex.Pattern;

import org.junit.Test;

@SuppressWarnings("javadoc")
public class StringRegexMatcherTest {
  
  StringRegexMatcher srm1 = new StringRegexMatcher("a");
  StringRegexMatcher srm1b = new StringRegexMatcher("a");
  StringRegexMatcher srm1c = new StringRegexMatcher("a", 0);
  StringRegexMatcher srm2 = new StringRegexMatcher("b");
  StringRegexMatcher srm3 = new StringRegexMatcher("a", Pattern.CASE_INSENSITIVE);
  
  @Test
  public void testMatchesTrue() {
    assertTrue(srm1.matches("alpha"));
  }
  
  @Test
  public void testMatchesFalse() {
    assertFalse(srm1.matches("omicron"));
  }
  
  @Test
  public void testSplitWithNoMatches() {
    String s = "omicron";
    assertThat(srm1.split(s), equalTo(new String[] {"omicron"}));
  }
  
  @Test
  public void testSplitWithOneMatch() {
    String s = "If a dog";
    assertThat(srm1.split(s), equalTo(new String[] {"If", "a", "dog"}));
  }
  
  @Test
  public void testSplitWithOneUppercaseMatch() {
    String s = "If A dog";
    assertThat(srm3.split(s), equalTo(new String[] {"If", "A", "dog"}));
  }
  
  @Test
  public void testSplitWithTwoMatches() {
    String s = "If a cat";
    assertThat(srm1.split(s), equalTo(new String[] {"If", "a", "cat"}));
  }
  
  @Test
  public void testEqualsReflexive() {
    assertThat(srm1, equalTo(srm1));
  }
  
  @Test
  public void testEqualsSymmetric() {
    assertThat(srm1b, equalTo(srm1));
  }
  
  @Test
  public void testEqualsFlag() {
    assertThat(srm1c, equalTo(srm1));
  }
  
  @Test
  public void testNotEqualsRegex() {
    assertThat(srm2, not(equalTo(srm1)));
  }
  
  @Test
  public void testNotEqualsFlags() {
    assertThat(srm3, not(equalTo(srm1)));
  }
  
  @Test
  public void testHashCodeEqualsReflexive() {
    assertThat(srm1.hashCode(), equalTo(srm1.hashCode()));
  }
  
  @Test
  public void testHashCodeEqualsSymmetric() {
    assertThat(srm1b.hashCode(), equalTo(srm1.hashCode()));
  }
  
  @Test
  public void testHashCodeEqualsFlag() {
    assertThat(srm1c.hashCode(), equalTo(srm1.hashCode()));
  }
  
  @Test
  public void testNotHashCodeEqualsRegex() {
    assertThat(srm2.hashCode(), not(equalTo(srm1.hashCode())));
  }
  
  @Test
  public void testNotHashCodeEqualsFlags() {
    assertThat(srm3.hashCode(), not(equalTo(srm1.hashCode())));
  }
  
}
