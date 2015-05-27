package com.ibm.watson.catalyst.jumpqa.trec;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

@SuppressWarnings("javadoc")
public class TrecReaderTest {
  
  StringBuffer trecs;
  List<String> pars1;
  
  @Before
  public void setUp() {
    trecs = new StringBuffer();
    trecs.append("{\"corpus-name\":\"\",\"documents\":[")
           .append("{")
             .append("\"file\":\"sample\\\\sampletrec1.xml\",")
             .append("\"pauID\":\"792D9A2361B65155B2B882C36766701D\",")
             .append("\"pauTitle\":\"New_York_City\",")
             .append("\"sourceDoc\":\"New_York_City.html\",")
             .append("\"paragraphs\":[\"New York City is big.\", \"Very big.\"]")
           .append("}")
         .append("]}");
    
    pars1 = new ArrayList<String>();
    pars1.add("New York City is big.");
    pars1.add("Very big.");
  }
  
  @Test
  public void testReadInputStream() {
    TrecReader tr = new TrecReader();
    
    Trec t1 = new Trec(
        "sample\\sampletrec1.xml",
        "792D9A2361B65155B2B882C36766701D",
        "New_York_City",
        "New_York_City.html",
        pars1);
    Trec t2 = tr.read(trecs.toString()).get(0);
    System.out.println(t1.getPauTitle());
    System.out.println(t2.getPauTitle());
    
    assertEquals(t1, t2);
  }
  
}
