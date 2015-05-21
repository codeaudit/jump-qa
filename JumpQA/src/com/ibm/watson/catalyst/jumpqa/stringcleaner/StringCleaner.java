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
package com.ibm.watson.catalyst.jumpqa.stringcleaner;

import java.util.regex.Pattern;

public class StringCleaner implements IStringCleaner {
  
  public StringCleaner(String clean) {
    _clean = Boolean.parseBoolean(clean);
  }
  
  @Override
  public String clean(String aString) {
    String result = aString;
    if (_clean) {
      result = removePattern(result, PARENTHETICAL);
      result = removePattern(result, ETAL);
      result = replacePattern(result, DOUBLEFIRST, "$1");
      result = removePattern(result, APPOSITIVE);
      //result = removePattern(result, COMMA);
      result = removePattern(result, STARTQUOTE);
      result = replaceUppercaseArticle(result);
    }
    return result.trim();
  }
  
  private static final String replacePattern(String aString, Pattern aPattern, String replacement) {
    return aPattern.matcher(aString).replaceAll(replacement);
  }
  
  private static final String removePattern(String aString, Pattern aPattern) {
    return replacePattern(aString, aPattern, "");
  }
  
  private final boolean _clean;
  
  private static final Pattern PARENTHETICAL = Pattern.compile("\\s\\([^\\)]*\\)");
  private static final Pattern ETAL = Pattern.compile("^.*(et al\\.|[A-Z]{1,2}):");
  private static final Pattern DOUBLEFIRST = Pattern.compile("^(.*) \\1\\b");
  private static final Pattern APPOSITIVE = Pattern.compile(",[^,]+,");
  //private static final Pattern COMMA = Pattern.compile("^.*, ");
  private static final Pattern STARTQUOTE = Pattern.compile("^['\"]");
  
  private static final Pattern UPPERCASEARTICLE = Pattern.compile("^(An?|The|One)\\b");
  private static final String replaceUppercaseArticle(String aString) {
    if (UPPERCASEARTICLE.matcher(aString).find()) {
      return aString.substring(0, 1).toLowerCase() + aString.substring(1);
    } else {
      return aString;
    }
  }
  
}
