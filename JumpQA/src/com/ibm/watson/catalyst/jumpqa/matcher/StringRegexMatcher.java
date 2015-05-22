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
package com.ibm.watson.catalyst.jumpqa.matcher;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringRegexMatcher implements IMatcher {
  
  private final Pattern _textRegex;
  
  public StringRegexMatcher(final String aRegex) {
    this(aRegex, 0);
  }
  
  /**
   * 
   * @param aRegex
   *          A regular expression to match.
   * @param flags
   *          Uses flags from the Pattern class.
   */
  public StringRegexMatcher(final String aRegex, final int flags) {
    _textRegex = Pattern.compile(aRegex, flags);
  }
  
  @Override
  public boolean matches(final String... strings) {
    return _textRegex.matcher(strings[0]).find();
  }
  
  public String[] split(final String aString) {
    final String[] result = new String[3];
    
    final String[] beforeAfter = _textRegex.split(aString, 2);
    result[0] = beforeAfter[0].trim();
    final Matcher m = _textRegex.matcher(aString);
    m.find();
    result[1] = m.group().trim();
    result[2] = beforeAfter[1].trim();
    
    return result;
  }
  
}
