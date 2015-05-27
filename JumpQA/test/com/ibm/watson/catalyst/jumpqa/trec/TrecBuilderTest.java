package com.ibm.watson.catalyst.jumpqa.trec;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

@SuppressWarnings("javadoc")
public class TrecBuilderTest {
  
  Trec t1;
  final String file = "file";
  final String pauId = "id";
  final String pauTitle = "title";
  final String sourceDoc = "doc";
  List<String> pars;
  
  @Before
  public void setUp() {
    pars = new ArrayList<String>();
    pars.add("some text");
    t1 = new Trec(file, pauId, pauTitle, sourceDoc, pars);
  }
  
  @Test
  public void testBuild() {
    TrecBuilder tb = new TrecBuilder();
    tb.setFile(file)
      .setPauId(pauId)
      .setPauTitle(pauTitle)
      .setSourceDoc(sourceDoc)
      .setParagraphs(pars);
    Trec t2 = tb.build();
    assertThat(t1, equalTo(t2));
  }
  
}
