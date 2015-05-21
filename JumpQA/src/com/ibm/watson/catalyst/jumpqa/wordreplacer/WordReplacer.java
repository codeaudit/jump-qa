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
package com.ibm.watson.catalyst.jumpqa.wordreplacer;

import java.util.Hashtable;
import java.util.Map.Entry;
import java.util.regex.Pattern;

public class WordReplacer implements IWordReplacer {
  
  public WordReplacer(Hashtable<Pattern, String> replacements) {
    _replacements = replacements;
  }
  
  public WordReplacer(String aFile) {
    this((new ReplacementHashtableReader()).read(aFile));
  }
  
  @Override
  public String replace(String aString) {
    String result = aString;
    for (Entry<Pattern, String> replacement : _replacements.entrySet()) {
      Pattern p = replacement.getKey();
      String r = replacement.getValue();
      result = p.matcher(result).replaceAll(r);
    }
    return result;
  }
  
  private final Hashtable<Pattern, String> _replacements;
  
}
