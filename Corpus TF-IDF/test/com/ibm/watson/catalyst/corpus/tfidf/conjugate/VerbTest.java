package com.ibm.watson.catalyst.corpus.tfidf.conjugate;

import static org.junit.Assert.*;

import org.junit.Test;

public class VerbTest {

  @Test
  public void testNormal() {
    Verb v1 = new Verb("accept");
    assertEquals(v1.getConjugations(), "accept\naccepts\naccepting\naccepted");
    Verb v2 = new Verb("add");
    assertEquals(v2.getConjugations(), "add\nadds\nadding\nadded");
  }
  
  public void testY() {
    Verb v1 = new Verb("annoy");
    assertEquals(v1.getConjugations(), "annoy\nannoys\nannoying\nannoyed");
    Verb v2 = new Verb("try");
    assertEquals(v2.getConjugations(), "try\ntries\ntrying\ntried");
    
  }
  
}
