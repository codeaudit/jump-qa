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
package com.ibm.watson.catalyst.jumpqa.splitter;

import java.util.Map;
import java.util.TreeMap;

/**
 * A class to retrieve splitters
 * 
 * @author Will Beason
 * @version 0.1.1
 * @since 0.1.0
 *
 */
public class SplitterFactory {
  
  /**
   * TODO: Class description
   * 
   * @author Will Beason
   * @version 0.1.0
   * @since 0.1.0
   *
   */
  public enum Splitters {
    /**
     * 
     */
    WORD,
    /**
     * 
     */
    SENTENCE,
    /**
     * 
     */
    PARAGRAPH
  }
  
  /**
   * Gets a splitter corresponding to the argument
   * 
   * @param answerSize the desired answer size
   * @return a splitter to split a TREC
   */
  public static Splitter build(final String answerSize) {
    switch (splitters.get(answerSize)) {
      case WORD:
        return new Splitter("[?!,;:\\.\\s]+");
      case SENTENCE:
        return new Splitter("[\\.!?]([\"']|\\s*\\[[^\\]^\\[]*\\])*(\\s+|$)");
      case PARAGRAPH:
        return new Splitter("\r?\n");
      default:
        throw new IllegalArgumentException(answerSize);
    }
  }
  
  private static final Map<String, Splitters> splitters = new TreeMap<String, Splitters>(
      String.CASE_INSENSITIVE_ORDER);
  
  static {
    splitters.put("word", Splitters.WORD);
    splitters.put("sentence", Splitters.SENTENCE);
    splitters.put("paragraph", Splitters.PARAGRAPH);
  }
  
}
