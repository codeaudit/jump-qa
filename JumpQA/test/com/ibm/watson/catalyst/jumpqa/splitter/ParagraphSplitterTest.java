package com.ibm.watson.catalyst.jumpqa.splitter;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;

@SuppressWarnings("javadoc")
public class ParagraphSplitterTest {

  ISplitter ps = SplitterFactory.build("paragraph");
  List<String> expected;
  
  @Before
  public void setUp() {
    expected = new ArrayList<String>();
  }
  
  @Test
  public void testSplitStringNoParagraphs() {
    String s = "";
    assertThat(ps.split(s), equalTo(expected));
  }
  
  @Test
  public void testSplitStringOneParagraph() {
    String s = "A sentence.";
    expected.add(s);
    assertThat(ps.split(s), equalTo(expected));
  }
  
  @Test
  public void testSplitStringTwoParagraphs() {
    String s = "A paragraph.\nAnother paragraph.\n";
    String s1 = "A paragraph.";
    String s2 = "Another paragraph.";
    expected.add(s1);
    expected.add(s2);
    assertThat(ps.split(s), equalTo(expected));
  }
  
  @Test
  public void testSplitList() {
    String s1 = "A paragraph.";
    String s2 = "Another paragraph.\nYet another.";
    List<String> s = new ArrayList<String>();
    s.add(s1);
    s.add(s2);
    expected.add(s1);
    expected.add("Another paragraph.");
    expected.add("Yet another.");
    assertThat(ps.split(s), equalTo(expected));
  }
  
}
