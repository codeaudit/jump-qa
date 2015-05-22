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

import java.util.Hashtable;

/**
 * A class to retrieve splitters
 * 
 * @author Will Beason
 * @version 0.1.0
 * @since 0.1.0
 *
 */
public class SplitterGetter {
  
  /**
   * Gets a splitter corresponding to the answer size argument
   * @param answerSize the desired answer size
   * @return a splitter to split a TREC
   */
  public static ISplitter getSplitter(final String answerSize) {
    return splitters.get(answerSize);
  }
  
  private static final Hashtable<String, ISplitter> splitters = new Hashtable<String, ISplitter>();
  
  static {
    splitters.put("sentence", new SentenceSplitter());
    splitters.put("paragraph", new ParagraphSplitter());
    //splitters.put("trec", new TrecSplitter());
  }
  
}
