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
package com.ibm.watson.catalyst.jumpqa.match;

public class TemplateMatchFactory {
  
  private static final TemplateMatchFactory INSTANCE;
  
  static {
    try {
      INSTANCE = new TemplateMatchFactory();
    } catch (RuntimeException e) {
      throw new RuntimeException("Error making TemplateMatchFactory instance", e);
    }
  }
  
  public static TemplateMatchFactory getInstance() {
    return INSTANCE;
  }
  
  private TemplateMatchFactory() { }
  
  public TemplateMatch build() {
    id++;
    return new TemplateMatch(
        id.toString(),
        _questionText,
        _answerText,
        _pauTitle,
        _pauId,
        _state,
        id.toString(),
        _templateId);
  }
  
  public void setQuestionText(String _questionText) {
    this._questionText = _questionText;
  }

  public void setAnswerText(String _answerText) {
    this._answerText = _answerText;
  }

  public void setPauTitle(String _pauTitle) {
    this._pauTitle = _pauTitle;
  }

  public void setPauId(String _pauId) {
    this._pauId = _pauId;
  }

  public void setState(String _state) {
    this._state = _state;
  }

  public void setTemplateId(String _templateId) {
    this._templateId = _templateId;
  }
  
  private static volatile Integer id = 1000000;
  
  private String _questionText;
  private String _answerText;
  private String _pauTitle;
  private String _pauId;
  private String _state;
  
  private String _templateId;
  
}
