package com.ibm.watson.catalyst.corpus.tfidf.conjugate;

import static org.junit.Assert.*;

import org.junit.Test;

public class VerbTest {

  @Test
  public void testNormal() {
    Verb v1 = new Verb("accept");
    assertEquals(v1.getConjugations(), "accept, accepts, accepting, accepted");
    Verb v2 = new Verb("add");
    assertEquals(v2.getConjugations(), "add, adds, adding, added");
  }
  
  public void testY() {
    Verb v1 = new Verb("annoy");
    assertEquals(v1.getConjugations(), "annoy, annoys, annoying, annoyed");
    Verb v2 = new Verb("try");
    assertEquals(v2.getConjugations(), "try, tries, trying, tried");
    
  }
  
}
