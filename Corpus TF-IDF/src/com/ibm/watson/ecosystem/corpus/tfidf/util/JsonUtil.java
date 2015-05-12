package com.ibm.watson.ecosystem.corpus.tfidf.util;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.ibm.watson.ecosystem.corpus.tfidf.term.Term;

public class JsonUtil {
  public static void main(String[] args) 
  {
    
    
    Term t = new Term("aterm", 100, 5.236);
    ObjectNode on = t.toObjectNode();
    System.out.println(on.toString());
    
  }
}
