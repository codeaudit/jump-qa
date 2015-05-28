package com.ibm.watson.catalyst.jumpqa.questioner;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Before;
import org.junit.Test;


@SuppressWarnings("javadoc")
public class TextTemplateQuestionerTest {
  
  TextTemplateQuestioner ttq0 = new TextTemplateQuestioner("");
  TextTemplateQuestioner ttq1 = new TextTemplateQuestioner("a [s0] b");
  TextTemplateQuestioner ttq2 = new TextTemplateQuestioner("a [s1] b");
  TextTemplateQuestioner ttq3 = new TextTemplateQuestioner("a [s2] b");
  TextTemplateQuestioner ttq4 = new TextTemplateQuestioner("a [s0] b [s1] c [s2] d");
  
  String[] splits;
  
  @Before
  public void setUp() {
    splits = new String[] {"one", "two", "three"};
  }
  
  @Test
  public void testMakeEmptyQuestion() {
    assertThat(ttq0.makeQuestion(splits), equalTo(""));
  }
  
  @Test
  public void testMakeQuestionOne() {
    assertThat(ttq1.makeQuestion(splits), equalTo("a one b"));
  }
  
  @Test
  public void testMakeQuestionTwo() {
    assertThat(ttq2.makeQuestion(splits), equalTo("a two b"));
  }
  
  @Test
  public void testMakeQuestionThree() {
    assertThat(ttq3.makeQuestion(splits), equalTo("a three b"));
  }
  
  @Test
  public void testMakeQuestionAll() {
    assertThat(ttq4.makeQuestion(splits), equalTo("a one b two c three d"));
  }
  
}
