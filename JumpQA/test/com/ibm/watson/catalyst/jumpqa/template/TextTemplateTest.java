package com.ibm.watson.catalyst.jumpqa.template;

import static org.easymock.EasyMock.*;

import java.util.ArrayList;
import java.util.List;

import org.easymock.TestSubject;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.easymock.Mock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ibm.watson.catalyst.jumpqa.entry.GTEntry;
import com.ibm.watson.catalyst.jumpqa.entry.GTEntryFactory;
import com.ibm.watson.catalyst.jumpqa.entry.IGTEntry;
import com.ibm.watson.catalyst.jumpqa.heuristics.BooleanHeuristics;
import com.ibm.watson.catalyst.jumpqa.matcher.StringRegexMatcher;
import com.ibm.watson.catalyst.jumpqa.questioner.TextTemplateQuestioner;
import com.ibm.watson.catalyst.jumpqa.replacer.SequentialReplacer;
import com.ibm.watson.catalyst.jumpqa.splitter.ISplitter;
import com.ibm.watson.catalyst.jumpqa.stringcleaner.IStringCleaner;

@SuppressWarnings("javadoc")
public class TextTemplateTest {
  
  private ISplitter aAnswerSplitter = null;

  List<IGTEntry> entries = new ArrayList<IGTEntry>();
  
  
  private String aTemplateId = "0";
  
  
  String s1 = "A is a B";
  String s2 = "C is a D";
  String[] s1s = new String[] {"A", "is a", "B"};

  @Mock
  private IStringCleaner aCleaner;
  @Before
  public void setUpCleaner() {
    aCleaner = createMock(IStringCleaner.class);
    expect(aCleaner.clean(s1)).andReturn(s1).anyTimes();
    replay(aCleaner);
  }
  
  @Mock
  private StringRegexMatcher aMatcher;
  @Before
  public void setUpStringRegexMatcher() {
    aMatcher = createMock(StringRegexMatcher.class);
    expect(aMatcher.split(s1)).andReturn(s1s).anyTimes();
    replay(aMatcher);
  }
  
  @Mock
  private SequentialReplacer sr;
  @Before
  public void setUpSequentialReplacer() {
    sr = createMock(SequentialReplacer.class);
    expect(sr.replace(s1s[1])).andReturn(s1s[1]).anyTimes();
    replay(sr);
  }

  private BooleanHeuristics<String> bh = new BooleanHeuristics<String>();
/*  private abstract class BHString extends BooleanHeuristics<String>{};
  @Mock
  @Before
  public void setUpBooleanHeuristics() {
    bh = createMock(BHString.class);
    expect(bh.anyTrue(s1)).andReturn(false);
  }*/
  
  @Mock
  private TextTemplateQuestioner q;
  @Before
  public void setUpQuestioner() {
    q = createMock(TextTemplateQuestioner.class);
    expect(q.makeQuestion(s1s)).andReturn("What is A?").anyTimes();
    replay(q);
  }
  
  @TestSubject
  private TextTemplate tt;
  
  @Mock
  private ISplitter ms;
  @Before
  public void setUpSplitter() {
    ms = createMock(ISplitter.class);
    List<String> splits = new ArrayList<String>();
    splits.add(s1);
    splits.add(s1);
    expect(ms.split(s1)).andReturn(splits);
    replay(ms);
  }
  
  @After
  public void tearDown() {
    GTEntryFactory.getInstance().reset();
  }
  
  @Test
  public void testGenMatchesFromSplit() {
    tt = new TextTemplate(aTemplateId, aAnswerSplitter, ms, q,
        aMatcher, bh, sr, aCleaner);
    
    List<GTEntry> entries = new ArrayList<GTEntry>();
    entries.add(new GTEntry("", "What is A?", "", "", "", "", "", ""));
    
    assertThat(tt.genMatchesFromSplit(s1), equalTo(entries));
  }
  
  @Test
  public void testGenMatchesFromString() {
    tt = new TextTemplate(aTemplateId, aAnswerSplitter, ms, q,
        aMatcher, bh, sr, aCleaner);
    List<GTEntry> entries = new ArrayList<GTEntry>();
    entries.add(new GTEntry("", "What is A?", "A is a B", "", "", "", "", ""));
    entries.add(new GTEntry("", "What is A?", "A is a B", "", "", "", "", ""));
    assertThat(tt.genMatchesFromString(s1), equalTo(entries));
  }
  
  @Test
  public void testEquals() {
    
  }
  
}
