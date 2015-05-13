package com.ibm.watson.ecosystem.corpus.tfidf.sentences;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.ibm.watson.catalyst.corpus.tfidf.sentences.WordFrequencyBuilder;
import com.ibm.watson.catalyst.corpus.tfidf.sentences.WordFrequencyHashtable;

public class WordFrequencyBuilderTest {

  @Test
  public void testBuild() {
    
    List<String> strings = new ArrayList<String>();
    
    strings.add("the apple");
    strings.add("The banana");
    
    WordFrequencyBuilder wfb = new WordFrequencyBuilder(strings);
    
    WordFrequencyHashtable wfh = wfb.build();
    
    assertEquals(wfh.get("the"), (Integer) 2);
    assertEquals(wfh.get("apple"), (Integer) 1);
    
  }

}
