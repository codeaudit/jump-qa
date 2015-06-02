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
package com.ibm.watson.catalyst.jumpqa.template;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;

import com.ibm.watson.catalyst.jumpqa.answer.Answer;
import com.ibm.watson.catalyst.jumpqa.answer.Candidate;
import com.ibm.watson.catalyst.jumpqa.answer.GroundTruthEntry;
import com.ibm.watson.catalyst.jumpqa.answer.GroundTruthEntry.GroundTruthState;
import com.ibm.watson.catalyst.jumpqa.answer.QuestionAnswerPair;
import com.ibm.watson.catalyst.jumpqa.entry.IGroundTruthEntry;
import com.ibm.watson.catalyst.jumpqa.matcher.StringRegexMatcher;
import com.ibm.watson.catalyst.jumpqa.questioner.IQuestioner;
import com.ibm.watson.catalyst.jumpqa.questioner.TextTemplateQuestioner;
import com.ibm.watson.catalyst.jumpqa.replacer.ConstReplacer;
import com.ibm.watson.catalyst.jumpqa.replacer.SequentialReplacer;
import com.ibm.watson.catalyst.jumpqa.splitter.SplitterFactory.Size;
import com.ibm.watson.catalyst.jumpqa.stringcleaner.StringCleaner.Clean;

@SuppressWarnings("javadoc")
public class TextTemplateTest {
  
  TextTemplate t1;
  Candidate c1;
  
  QuestionAnswerPair qa1;
  GroundTruthEntry e1;
  
  TextTemplate t1a;
  TextTemplate t2;
  TextTemplate t3;
  TextTemplate t4;
  
  IQuestioner q1;
  IQuestioner q1a;
  
  String id1 = "0";
  SequentialReplacer s1 = new SequentialReplacer();
  StringRegexMatcher m1 = new StringRegexMatcher("is a");
  
  @Before
  public void setUp() {
    String id1a = "0";
    String id2 = "1";
    
    Answer a1 = new Answer("A is a B");
    c1 = new Candidate("A is a B", a1);
    
    q1 = new TextTemplateQuestioner("What is [s0]?");
    q1a = new TextTemplateQuestioner("What is [s0]?");
    
    SequentialReplacer s1a = new SequentialReplacer();
    
    StringRegexMatcher m1a = new StringRegexMatcher("is a");
    
    t1 = new TextTemplate(id1, Size.SENTENCE, Size.SENTENCE, q1, s1, Clean.NO, m1);
    
    t1a = new TextTemplate(id1a, Size.SENTENCE, Size.SENTENCE, q1a, s1a, Clean.NO, m1a);
    
    TextTemplateQuestioner q2 = new TextTemplateQuestioner("What is a [s0]?");
    t2 = new TextTemplate(id2, Size.SENTENCE, Size.SENTENCE, q2, s1, Clean.NO, m1);
    ConstReplacer cr = new ConstReplacer("a", "b");
    SequentialReplacer s3 = new SequentialReplacer(cr);
    t3 = new TextTemplate(id1, Size.SENTENCE, Size.SENTENCE, q1, s3, Clean.NO, m1);
    StringRegexMatcher m4 = new StringRegexMatcher("because");
    t4 = new TextTemplate(id1, Size.SENTENCE, Size.SENTENCE, q1, s1, Clean.NO, m4);
    
    qa1 = new QuestionAnswerPair("What is A?", a1);
    e1 = new GroundTruthEntry("1000001", qa1, GroundTruthState.APPROVED, "");
  }
  
  @Test
  public void testCandidate2entries() {
    IGroundTruthEntry e0 = t1.candidate2entries(c1).get(0);
    assertThat(e0, equalTo(e1));
  }
  
  @Test
  public void testEqualsReflexive() {
    assertThat(t1, equalTo(t1));
  }
  
  @Test
  public void testEqualsSymmetric() {
    assertThat(t1, equalTo(t1a));
  }
  
  @Test
  public void testNotEqualsAnswerSize() {
    TextTemplate tb = new TextTemplate(id1, Size.PARAGRAPH, Size.SENTENCE, q1, s1, Clean.NO, m1);
    assertThat(t1, not(equalTo(tb)));
  }
  
  @Test
  public void testNotEqualsCandidateSize() {
    TextTemplate tb = new TextTemplate(id1, Size.SENTENCE, Size.PARAGRAPH, q1, s1, Clean.NO, m1);
    assertThat(t1, not(equalTo(tb)));
  }
  
  @Test
  public void testNotEqualsClean() {
    TextTemplate tb = new TextTemplate(id1, Size.SENTENCE, Size.SENTENCE, q1, s1, Clean.YES, m1);
    assertThat(t1, not(equalTo(tb)));
  }
  
  @Test
  public void testNotEqualsId() {
    assertThat(t1, not(equalTo(t2)));
  }
  
  @Test
  public void testNotEqualsReplacer() {
    assertThat(t1, not(equalTo(t3)));
  }
  
  @Test
  public void testNotEqualsMatcher() {
    assertThat(t1, not(equalTo(t4)));
  }
  
}
