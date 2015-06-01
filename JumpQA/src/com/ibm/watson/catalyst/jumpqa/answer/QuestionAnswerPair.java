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
/**
 * 
 */
package com.ibm.watson.catalyst.jumpqa.answer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.ibm.watson.catalyst.jumpqa.util.IPrintable;
import com.ibm.watson.catalyst.jumpqa.util.IWritable;

/**
 * TODO: Class description
 * 
 * @author Will Beason
 * @version 0.1.1
 * @since 0.1.1
 *
 */
public class QuestionAnswerPair implements IWritable, IPrintable {
  
  /**
   * @param aQuestionText the text of the question
   * @param aAnswer the answer
   */
  public QuestionAnswerPair(String aQuestionText, Answer aAnswer) {
    _questionText = aQuestionText;
    _answer = new Answer(aAnswer);
  }
  
  /**
   * 
   */
  public QuestionAnswerPair() {
    this("", "");
  }
  
  /**
   * @param aQaPair the question/answer pair
   */
  public QuestionAnswerPair(QuestionAnswerPair aQaPair) {
    _questionText = aQaPair._questionText;
    _answer = aQaPair._answer;
  }
  
  /**
   * @param aQuestionText the text of the question
   * @param aAnswer the text of the answer
   */
  public QuestionAnswerPair(String aQuestionText, String aAnswer) {
    _questionText = aQuestionText;
    _answer = new Answer(aAnswer);
  }
  
  @Override
  public StringBuilder toStringBuilder() {
    StringBuilder sb = new StringBuilder();
    sb.append(_questionText).append(",").append(_answer.toStringBuilder());
    return sb;
  }
  
  @Override
  public List<String> toList() {
    List<String> result = new ArrayList<String>();
    result.add(_questionText);
    result.addAll(_answer.toList());
    return result;
  }
  
  @Override
  public Iterator<String> iterator() {
    return this.toList().iterator();
  }
  
  private final String _questionText;
  private final Answer _answer;
  
}
