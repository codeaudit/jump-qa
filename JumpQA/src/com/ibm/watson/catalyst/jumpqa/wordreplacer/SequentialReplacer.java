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

import java.util.List;

/**
 * A class for replacing matched regular expressions with given strings. The keys in the hashtable
 *   are the regular expressions, and the mapped values are the replacements.
 * 
 * @author Will Beason
 * @version 0.1.0
 * @since 0.1.0
 *
 */
public class SequentialReplacer implements IWordReplacer {
  
  private final List<Replacer> _replacers;
  
  /**
   * Instantiates a new WordReplacer with the given hashtable
   * @param replacements the hashtable of searches and replacements
   */
  public SequentialReplacer(final List<Replacer> replacements) {
    _replacers = replacements;
  }
  
  /**
   * Instantiates a new WordReplacer by reading the hashtable from a file
   * @param aFile the file to read
   */
  public SequentialReplacer(final String aFile) {
    this((new ReplacerReader()).readFile(aFile));
  }
  
  @Override
  public String replace(final String aString) {
    String result = aString;
    for (final Replacer replacer : _replacers) {
      result = replacer.replaceAll(result);
    }
    return result;
  }
  
}