package com.ibm.watson.catalyst.jumpqa.wordreplacer;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

@SuppressWarnings("javadoc")
public class SequentialReplacerTest {
  
  Replacer r1;
  Replacer r1b;
  Replacer r2;
  Replacer r3;
  List<Replacer> replacers;
  
  SequentialReplacer s0;
  SequentialReplacer s1;
  SequentialReplacer s1a;
  SequentialReplacer s1b;
  SequentialReplacer s2;
  
  @Before
  public void setUp() {
    r1 = new Replacer("a", "_");
    r1b = new Replacer("a", "_");
    r2 = new Replacer("b", "!");
    r3 = new Replacer("_", "c");
    
    replacers = new ArrayList<Replacer>();
    
    List<Replacer> l0 = new ArrayList<Replacer>();
    s0 = new SequentialReplacer(l0);
    
    List<Replacer> l1 = new ArrayList<Replacer>();
    l1.add(r1);
    s1 = new SequentialReplacer(l1);
    
    List<Replacer> l1a = new ArrayList<Replacer>();
    l1a.add(r1);
    s1a = new SequentialReplacer(l1a);
    
    List<Replacer> l1b = new ArrayList<Replacer>();
    l1b.add(r1b);
    s1b = new SequentialReplacer(l1b);
    
    List<Replacer> l2 = new ArrayList<Replacer>();
    l2.add(r2);
    s2 = new SequentialReplacer(l2);
  }
  
  @Test
  public void replace1() {
    replacers.add(r1);
    SequentialReplacer wr = new SequentialReplacer(replacers);
    assertThat(wr.replace("alpha"), equalTo("_lph_"));
  }
  
  @Test
  public void replace2() {
    replacers.add(r1);
    replacers.add(r2);
    SequentialReplacer wr = new SequentialReplacer(replacers);
    assertThat(wr.replace("beta"), equalTo("!et_"));
  }
  
  @Test
  public void replaceOver() {
    replacers.add(r1);
    replacers.add(r3);
    SequentialReplacer wr = new SequentialReplacer(replacers);
    assertThat(wr.replace("alpha"), equalTo("clphc"));
  }
  
  @Test
  public void testEqualsReflexive() {
    assertThat(s1, equalTo(s1));
  }
  
  @Test
  public void testEqualsSymmetricIdentialList() {
    assertThat(s1, equalTo(s1a));
  }
  
  @Test
  public void testEqualsSymmetricIdentialElements() {
    assertThat(s1, equalTo(s1b));
  }
  
  @Test
  public void testNotEqualNull() {
    assertThat(s1, not(equalTo(null)));
  }
  
  @Test
  public void testNotEqualNullList() {
    assertThat(s1, not(equalTo(s0)));
  }
  
  @Test
  public void testNotEqualList() {
    assertThat(s1, not(equalTo(s2)));
  }
  
  @Test
  public void testHashCodeEqualsReflexive() {
    assertThat(s1.hashCode(), equalTo(s1.hashCode()));
  }
  
  @Test
  public void testHashCodeEqualsSymmetricIdentialList() {
    assertThat(s1.hashCode(), equalTo(s1a.hashCode()));
  }
  
  @Test
  public void testHashCodeEqualsSymmetricIdentialElements() {
    assertThat(s1.hashCode(), equalTo(s1b.hashCode()));
  }
  
  @Test
  public void testHashCodeNotEqualNullList() {
    assertThat(s1.hashCode(), not(equalTo(s0).hashCode()));
  }
  
  @Test
  public void testHashCodeNotEqualList() {
    assertThat(s1.hashCode(), not(equalTo(s2).hashCode()));
  }
  
}
