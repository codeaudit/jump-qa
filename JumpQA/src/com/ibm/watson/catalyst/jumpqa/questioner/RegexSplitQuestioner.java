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
 * A class to compose questions based on how a question was split by a pattern.
 * 
 * @author Will Beason
 * @version 0.1.0
 * @since 0.1.0
 *
 */
public class RegexSplitQuestioner implements IQuestioner {
  
  private final String _question;
  
  private final Pattern s0 = Pattern.compile(Pattern.quote("[s0]"));
  
  private final Pattern s1 = Pattern.compile(Pattern.quote("[s1]"));
  
  private final Pattern s2 = Pattern.compile(Pattern.quote("[s2]"));
  
  /**
   * Instantiates a new RegexSplitQuestioner
   * @param aQuestion the generic question string
   */
  public RegexSplitQuestioner(final String aQuestion) {
    _question = aQuestion;
  }
  
  @Override
  public String makeQuestion(final String[] splits) {
    String result = _question;
    result = s0.matcher(result).replaceAll(Matcher.quoteReplacement(splits[0]));
    result = s1.matcher(result).replaceAll(Matcher.quoteReplacement(splits[1]));
    result = s2.matcher(result).replaceAll(Matcher.quoteReplacement(splits[2]));
    if (result.contains(", ")) {
      System.out.println(result);
    }
    return result;
  }
  
}
