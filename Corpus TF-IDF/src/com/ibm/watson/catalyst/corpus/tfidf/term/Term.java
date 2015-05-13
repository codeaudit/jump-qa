package com.ibm.watson.catalyst.corpus.tfidf.term;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;


public class Term {
  
  public Term(JsonNode aNode) {
    _term = aNode.get("string").asText();
    _frequency = aNode.get("frequency").asInt();
    _idf = aNode.get("tfidf").asDouble();
  }
  
  public Term(String aTerm, int aFrequency, double aIdf) {
    _term = aTerm.substring(0, 1).toUpperCase() + aTerm.substring(1);
    _frequency = aFrequency;
    _idf = aIdf;
    //_pattern = Pattern.compile("\\b" + _term + "\\b", Pattern.CASE_INSENSITIVE);
  }
  
  public Term(String aTerm, int aFrequency) {
    this(aTerm, aFrequency, 0.0);
  }
  
  public Term(String aTerm) {
    this(aTerm, 1);
  }
  
  public Term(Term t1) {
    _term = t1.getTerm();
    _frequency = t1.getFrequency();
    _idf = 0.0;
    //_pattern = Pattern.compile("\\b" + _term + "\\b", Pattern.CASE_INSENSITIVE);
  }
  
  public Term(Term t1, Term t2) {
    assert(t1.equals(t2));
    _term = t1.getTerm();
    _frequency = t1.getFrequency() + t2.getFrequency();
    _idf = 0.0;
    //_pattern = Pattern.compile("\\b" + _term + "\\b", Pattern.CASE_INSENSITIVE);
  }
  
  public String getTerm() { return _term; }
  public int getFrequency() { return _frequency; }
  public double getIdf() { return _idf; }
  //public Pattern getPattern() { return _pattern; }
  
  @Override
  public boolean equals(Object o) {
    return o.toString().equals(_term);
  }
  
  public String toString() {
    return _term;
  }
  
  public ObjectNode toObjectNode() {
    ObjectNode on = MAPPER.createObjectNode();
    on.put("term", _term);
    on.put("frequency", _frequency);
    on.put("tfidf", _idf);
    return on;
  }
  
  // ------------------------------------------------------------------------------------------ //
  // Private
  // ------------------------------------------------------------------------------------------ //
  private final String _term;
  private final int _frequency;
  private double _idf;
  private static final ObjectMapper MAPPER = new ObjectMapper();
  //private final Pattern _pattern;
  
}
