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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

public class TemplateMatch implements ITemplateMatch {
  
  private final String _answerText;
  
  private final String _clusterId;
  
  private final String _pauId;
  
  private final String _pauTitle;
  
  private final String _questionId;
  
  private final String _questionText;
  
  private final String _state;
  private final String _templateId;
  
  public TemplateMatch(final String questionId, final String questionText, final String answerText,
      final String pauTitle, final String pauId, final String state, final String clusterId,
      final String templateId) {
    _questionId = questionId;
    _questionText = questionText;
    _answerText = answerText;
    _pauTitle = pauTitle;
    _pauId = pauId;
    _clusterId = clusterId;
    _state = state;
    
    _templateId = templateId;
  }
  
  @Override
  public boolean equals(final Object o) {
    if (o instanceof TemplateMatch) {
      final TemplateMatch tm = (TemplateMatch) o;
      if (!tm._questionText.equals(_questionText)) return false;
      if (!tm._answerText.equals(_answerText)) return false;
      return true;
    } else
      return false;
  }
  
  public String getTemplateId() {
    return _templateId;
  }
  
  @Override
  public int hashCode() {
    return (_questionText + _answerText).hashCode();
  }
  
  @Override
  public Iterator<String> iterator() {
    final List<String> result = new ArrayList<String>();
    result.add(_questionId);
    // String[] words = WordSplitter.split(_questionText);
    // result.add(words.length > 2 ? words[2] : "");
    // result.add(words[words.length - 1]);
    result.add(_questionText);
    result.add(_answerText);
    result.add(_pauTitle);
    result.add(_pauId);
    result.add(_clusterId);
    result.add(_state);
    return result.iterator();
  }
  
  @Override
  public JsonNode toJsonNode() {
    return null;
  }
  
}
