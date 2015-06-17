/**
 * 
 */
package com.ibm.watson.catalyst.jumpqa.matcher;

import java.util.regex.Pattern;

/** TODO: Class description
 * 
 * @author Will Beason
 * @version 0.1.2
 * @since 0.1.2
 *
 */
public class TemplateMatcherFactory {

  /** 
   * TODO: Method description
   * @param aString the title match regular expression
   * @param flags the pattern flags
   * @return the TemplateMatcher
   */
  public static EntryPatterns titleMatcher(String aString, int flags) {
    Pattern p = Pattern.compile(aString, flags);
    return new EntryPatterns(p, null, null);
  }
  
  /** 
   * TODO: Method description
   * @param aString the title match regular expression
   * @return the TemplateMatcher
   */
  public static EntryPatterns titleMatcher(String aString) {
    return titleMatcher(aString, 0);
  }
  
  /** 
   * TODO: Method description
   * @param aString the answer match regular expression
   * @param flags the pattern flags
   * @return the TemplateMatcher
   */
  public static EntryPatterns answerMatcher(String aString, int flags) {
    Pattern p = Pattern.compile(aString, flags);
    return new EntryPatterns(null, p, null);
  }

  /** 
   * TODO: Method description
   * @param aString the answer match regular expression
   * @return the TemplateMatcher
   */
  public static EntryPatterns answerMatcher(String aString) {
    return answerMatcher(aString, 0);
  }
  
  /** 
   * TODO: Method description
   * @param aString the text match regular expression
   * @param flags the pattern flags
   * @return the TemplateMatcher
   */
  public static EntryPatterns textMatcher(String aString, int flags) {
    Pattern p = Pattern.compile(aString, flags);
    return new EntryPatterns(null, null, p);
  }

  /** 
   * TODO: Method description
   * @param aString the text match regular expression
   * @return the TemplateMatcher
   */
  public static EntryPatterns textMatcher(String aString) {
    return textMatcher(aString, 0);
  }
  
  
  
}
