package com.ibm.watson.catalyst.jumpqa.matcher;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringRegexMatcher implements IMatcher {
  
  /**
   * 
   * @param aRegex A regular expression to match.
   * @param flags Uses flags from the Pattern class.
   */
  public StringRegexMatcher(String aRegex, int flags) {
    _textRegex = Pattern.compile(aRegex, flags);
  }
  
  public StringRegexMatcher(String aRegex) {
    this(aRegex, 0);
  }
  
  @Override
  public boolean matches(String... strings) {
    return _textRegex.matcher(strings[0]).find();
  }
  
  public String[] split(String aString) {
    String[] result = new String[3];
    
    String[] beforeAfter = _textRegex.split(aString, 2);
    result[0] = beforeAfter[0].trim();
    Matcher m = _textRegex.matcher(aString);
    m.find();
    result[1] = m.group().trim();
    result[2] = beforeAfter[1].trim();
    
    return result;
  }
  
  private final Pattern _textRegex;
  
}
