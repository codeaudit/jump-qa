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

public class SplitterFactory {
  
  private enum AnswerSize {
    PARAGRAPH, SENTENCE, TREC
  }
  
  public static ISplitter createSplitter(final AnswerSize answerSize) {
    switch (answerSize) {
      case SENTENCE:
        return new SentenceSplitter();
      case PARAGRAPH:
        return new ParagraphSplitter();
      default:
        throw new RuntimeException("Answer size not implemented: " + answerSize);
    }
  }
  
  public static ISplitter createSplitter(final String answerSize) {
    return createSplitter(toAnswerSize(answerSize));
  }
  
  private static AnswerSize toAnswerSize(final String aString) {
    switch (aString.toLowerCase()) {
      case "sentence":
        return AnswerSize.SENTENCE;
      case "paragraph":
        return AnswerSize.PARAGRAPH;
      case "trec":
        return AnswerSize.TREC;
      default:
        throw new RuntimeException("Unknown answer size: " + aString);
    }
  }
  
}
