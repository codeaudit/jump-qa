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
package com.ibm.watson.catalyst.jumpqa.entry;

/**
 * A class which builds template matches, the Q&A pairs to be ingested by a Watson Q&A instance.
 * 
 * @author Will Beason
 * @version 0.1.0
 * @since 0.1.0
 *
 */
public class GTEntryFactory {
  
  private String _answerText;
  
  private String _pauId;
  
  private String _pauTitle;
  
  private String _questionText;
  
  private String _state;
  
  private String _templateId;
  
  private GTEntryFactory() {}
  
  /**
   * Builds a TemplateMatch from the current data in the TemplateMatchFactory
   * @return a new TemplateMatch
   */
  public GTEntry build() {
    id++;
    return new GTEntry(id.toString(), _questionText, _answerText, _pauTitle, _pauId, _state,
        id.toString(), _templateId);
  }
  
  /**
   * Sets the answer text
   * @param aAnswerText the answer text
   */
  public void setAnswerText(final String aAnswerText) {
    _answerText = aAnswerText;
  }
  
  /**
   * Sets the PAU ID
   * @param aPauId the PAU ID
   */
  public void setPauId(final String aPauId) {
    _pauId = aPauId;
  }
  
  /**
   * Sets the PAU Title
   * @param aPauTitle the PAU Title
   */
  public void setPauTitle(final String aPauTitle) {
    this._pauTitle = aPauTitle;
  }
  
  /**
   * Sets the question text
   * @param aQuestionText the question text
   */
  public void setQuestionText(final String aQuestionText) {
    this._questionText = aQuestionText;
  }
  
  /**
   * Sets the state of the question
   * @param aState the question's state
   */
  public void setState(final String aState) {
    this._state = aState;
  }
  
  /**
   * Sets the template ID
   * @param aTemplateId the ID of the template which generated the question
   */
  public void setTemplateId(final String aTemplateId) {
    this._templateId = aTemplateId;
  }
  
  private static volatile Integer id = 1000000;
  private static final GTEntryFactory INSTANCE;
  static {
    try {
      INSTANCE = new GTEntryFactory();
    } catch (final RuntimeException e) {
      throw new RuntimeException("Error making TemplateMatchFactory instance", e);
    }
  }
  
  /**
   * Returns the TemplateMatchFactory singleton instance
   * @return the TemplateMatchFactory singleton
   */
  public static GTEntryFactory getInstance() {
    return INSTANCE;
  }
  
}
