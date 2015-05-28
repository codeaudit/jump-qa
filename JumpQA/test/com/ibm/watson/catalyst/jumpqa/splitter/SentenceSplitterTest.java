package com.ibm.watson.catalyst.jumpqa.splitter;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;

@SuppressWarnings("javadoc")
public class SentenceSplitterTest {

  ISplitter ss = SplitterFactory.build("sentence");
  List<String> sentences;
  
  @Before
  public void setUp() {
    sentences = new ArrayList<String>();
  }
  
  @Test
  public void testSplitNoSentences() {
    String s = "";
    assertThat(ss.split(s), equalTo(sentences));
  }
  
  @Test
  public void testSplitOneSentence() {
    String s = "A sentence.";
    sentences.add("A sentence");
    assertThat(ss.split(s), equalTo(sentences));
  }
  
  @Test
  public void testSplitTwoSentences() {
    String s = "One sentence. Another sentence.";
    sentences.add("One sentence");
    sentences.add("Another sentence");
    assertThat(ss.split(s), equalTo(sentences));
  }
  
}
