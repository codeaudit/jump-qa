package com.ibm.watson.catalyst.jumpqa.wordreplacer;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

@SuppressWarnings("javadoc")
public class SequentialReplacerTest {
  
  Replacer r1;
  Replacer r2;
  Replacer r3;
  List<Replacer> replacers;
  
  @Before
  public void setUp() {
    r1 = new Replacer("a", "_");
    r2 = new Replacer("b", "!");
    r3 = new Replacer("_", "c");
    
    replacers = new ArrayList<Replacer>();
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
  
}
