package com.ibm.watson.catalyst.jumpqa.trec;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

@SuppressWarnings("javadoc")
public class TrecTest {
  
  Trec t1;
  Trec t1b;
  Trec t2;
  Trec t3;
  Trec t4;
  Trec t5;
  Trec t6;
  List<String> pars1;
  
  @Before
  public void setUp() {
    pars1 = new ArrayList<String>();
    pars1.add("New York City is big.");
    pars1.add("Very big.");
    
    t1 = new Trec(
        "sample\\sampletrec1.xml",
        "792D9A2361B65155B2B882C36766701D",
        "New_York_City",
        "New_York_City.html",
        pars1);

    t1b = new Trec(
        "sample\\sampletrec1.xml",
        "792D9A2361B65155B2B882C36766701D",
        "New_York_City",
        "New_York_City.html",
        pars1);

    t2 = new Trec(
        "sample\\sampletrec1.xml",
        "792D9A2361B65155B2B882C36766701D",
        "New_York_City",
        "New_York_City.html",
        pars1);

    t3 = new Trec(
        "sample\\sampletrec1.xml",
        "792D9A2361B65155B2B882C36766701D",
        "New_York_City",
        "New_York_City.html",
        pars1);

    t4 = new Trec(
        "sample\\sampletrec1.xml",
        "792D9A2361B65155B2B882C36766701D",
        "New_York_City",
        "New_York_City.html",
        pars1);

    t5 = new Trec(
        "sample\\sampletrec1.xml",
        "792D9A2361B65155B2B882C36766701D",
        "New_York_City",
        "New_York_City.html",
        pars1);

    t6 = new Trec(
        "sample\\sampletrec1.xml",
        "792D9A2361B65155B2B882C36766701D",
        "New_York_City",
        "New_York_City.html",
        pars1);
  }
  
  @Test
  public void testHashCodeReflexive() {
    assertEquals(t1.hashCode(), t1b.hashCode());
  }
  
  @Test
  public void testEqualsReflexive() {
    assertEquals(t1, t1);
  }
  
  @Test
  public void testEqualsSymmetric() {
    assertEquals(t1, t1b);
  }
  
  
  
}
