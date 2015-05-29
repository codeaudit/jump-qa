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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * Holds data needed to ingest a question into a Watson Q&A instance.
 * 
 * @author Will Beason
 * @version 0.1.0
 * @since 0.1.0
 *
 */
public class GTEntry implements IGTEntry {
  
  private final String _answerText;
  private final String _clusterId;
  private final String _pauId;
  private final String _pauTitle;
  private final String _questionId;
  private final String _questionText;
  private final String _state;
  private final String _templateId;
  
  /**
   * Holds data for a match.
   * @param questionId the ID of the question
   * @param questionText the text of the question
   * @param answerText the text of the answer
   * @param pauTitle the PAU title of the originating document
   * @param pauId the PAU ID of the originating document
   * @param state the state of the question
   * @param clusterId the cluster ID of the question; the question it is mapped to
   * @param templateId the ID of the template which generated the match
   */
  public GTEntry(final String questionId, final String questionText, final String answerText,
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
  public boolean equals(final Object obj) {
    if (obj == this) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    GTEntry other = (GTEntry) obj;
    if (!Objects.equals(other._questionText, this._questionText)) return false;
    if (!Objects.equals(other._answerText, this._answerText)) return false;
    return true;
  }
  
  @Override
  public String getTemplateId() {
    return _templateId;
  }
  
  public String getQuestion() {
    return _questionText;
  }
  
  public String getAnswer() {
    return _answerText;
  }
  
  @Override
  public int hashCode() {
    return (new HashCodeBuilder(SEED, MULTIPLY))
        .append(_questionText)
        .append(_answerText)
        .hashCode();
  }
  
  private static final int SEED = 354469711;
  private static final int MULTIPLY = 1363866467;
  
  @Override
  public Iterator<String> iterator() {
    final List<String> result = new ArrayList<String>();
    result.add(_questionId);
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
