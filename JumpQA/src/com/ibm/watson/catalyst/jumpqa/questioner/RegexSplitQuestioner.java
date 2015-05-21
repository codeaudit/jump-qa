/*******************************************************************************
 * Copyright 2015 IBM Corporation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
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
