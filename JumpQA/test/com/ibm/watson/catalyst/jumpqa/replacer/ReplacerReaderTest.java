package com.ibm.watson.catalyst.jumpqa.replacer;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.ibm.watson.catalyst.jumpqa.replacer.ConstReplacer;
import com.ibm.watson.catalyst.jumpqa.replacer.ReplacerReader;
import com.ibm.watson.catalyst.jumpqa.replacer.ReplacerReader.ReplacerType;

@SuppressWarnings("javadoc")
public class ReplacerReaderTest {
  
  String line;
  ConstReplacer r1;
  ConstReplacer r2;
  
  @Before
  public void setUp() {
    line = "a=_";
    r1 = new ConstReplacer("a", "_");
    r2 = new ConstReplacer("\\ba\\b", "_");
  }
  
  @Test
  public void testRead() {
    ConstReplacer r3 = (new ReplacerReader()).read(line).get(0);
    assertThat(r3, equalTo(r3));
  }
  
  @Test
  public void testReadWord() {
    ConstReplacer r3 = (new ReplacerReader(ReplacerType.WORD)).read(line).get(0);
    assertThat(r2, equalTo(r3));
  }
  
}
