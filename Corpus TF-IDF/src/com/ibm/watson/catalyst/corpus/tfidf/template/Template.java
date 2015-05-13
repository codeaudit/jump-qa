package com.ibm.watson.catalyst.corpus.tfidf.template;

import java.util.regex.Pattern;

public final class Template {
  
  public Template(String match) {
    _match = Pattern.compile(match);
  }
  
  public boolean matches(String aString) {
    return _match.matcher(aString).find();
  }
  
  
  
  private final Pattern _match;
  
}
