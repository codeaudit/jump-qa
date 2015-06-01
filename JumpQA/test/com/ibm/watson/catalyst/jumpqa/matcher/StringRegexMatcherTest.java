/*******************************************************************************
 * Copyright 2015 IBM Corporation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
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
    assertThat(srm1.split(s), equalTo(new String[] { "omicron" }));
  }
  
  @Test
  public void testSplitWithOneMatch() {
    String s = "If a dog";
    assertThat(srm1.split(s), equalTo(new String[] { "If", "a", "dog" }));
  }
  
  @Test
  public void testSplitWithOneUppercaseMatch() {
    String s = "If A dog";
    assertThat(srm3.split(s), equalTo(new String[] { "If", "A", "dog" }));
  }
  
  @Test
  public void testSplitWithTwoMatches() {
    String s = "If a cat";
    assertThat(srm1.split(s), equalTo(new String[] { "If", "a", "cat" }));
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
