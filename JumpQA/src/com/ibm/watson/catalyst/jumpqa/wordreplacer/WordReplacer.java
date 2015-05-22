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

/**
 * A class for replacing matched regular expressions with given strings. The keys in the hashtable
 *   are the regular expressions, and the mapped values are the replacements.
 * 
 * @author Will Beason
 * @version 0.1.0
 * @since 0.1.0
 *
 */
public class WordReplacer implements IWordReplacer {
  
  private final Hashtable<Pattern, String> _replacements;
  
  /**
   * Instantiates a new WordReplacer with the given hashtable
   * @param replacements the hashtable of searches and replacements
   */
  public WordReplacer(final Hashtable<Pattern, String> replacements) {
    _replacements = replacements;
  }
  
  /**
   * Instantiates a new WordReplacer by reading the hashtable from a file
   * @param aFile the file to read
   */
  public WordReplacer(final String aFile) {
    this((new ReplacementHashtableReader()).read(aFile));
  }
  
  @Override
  public String replace(final String aString) {
    String result = aString;
    for (final Entry<Pattern, String> replacement : _replacements.entrySet()) {
      final Pattern p = replacement.getKey();
      final String r = replacement.getValue();
      result = p.matcher(result).replaceAll(r);
    }
    return result;
  }
  
}
