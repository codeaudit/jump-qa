package com.ibm.watson.catalyst.jumpqa.wordreplacer;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.ibm.watson.catalyst.jumpqa.wordreplacer.ReplacerReader.ReplacerType;

@SuppressWarnings("javadoc")
public class ReplacerReaderTest {
  
  String line;
  Replacer r1;
  Replacer r2;
  
  @Before
  public void setUp() {
    line = "a=_";
    r1 = new Replacer("a", "_");
    r2 = new Replacer("\\ba\\b", "_");
  }
  
  @Test
  public void testRead() {
    Replacer r3 = (new ReplacerReader()).read(line).get(0);
    assertEquals(r1, r3);
  }
  
  @Test
  public void testReadWord() {
    Replacer r3 = (new ReplacerReader(ReplacerType.WORD)).read(line).get(0);
    assertEquals(r2, r3);
  }
  
}
