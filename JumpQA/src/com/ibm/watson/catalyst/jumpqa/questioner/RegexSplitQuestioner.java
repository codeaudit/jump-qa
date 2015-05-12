package com.ibm.watson.catalyst.jumpqa.questioner;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Splits a sentence with a regular expression, then writes a question.
 * @author wabeason
 *
 */
public class RegexSplitQuestioner implements IQuestioner {
  
  public RegexSplitQuestioner(String aQuestion) {
    _question = aQuestion;
  }
  
  public String makeQuestion(String[] splits) {
    String result = _question;
    result = s0.matcher(result).replaceAll(Matcher.quoteReplacement(splits[0]));
    result = s1.matcher(result).replaceAll(Matcher.quoteReplacement(splits[1]));
    result = s2.matcher(result).replaceAll(Matcher.quoteReplacement(splits[2]));
    if (result.contains(", ")) System.out.println(result);
    return result;
  }
  
  private final String _question;
  
  private final Pattern s0 = Pattern.compile(Pattern.quote("[s0]"));
  private final Pattern s1 = Pattern.compile(Pattern.quote("[s1]"));
  private final Pattern s2 = Pattern.compile(Pattern.quote("[s2]"));
  
  
}
