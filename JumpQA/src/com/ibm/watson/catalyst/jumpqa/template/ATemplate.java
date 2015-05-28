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
package com.ibm.watson.catalyst.jumpqa.template;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.collections4.IteratorUtils;

import com.ibm.watson.catalyst.jumpqa.match.ITemplateMatch;
import com.ibm.watson.catalyst.jumpqa.match.TemplateMatchFactory;
import com.ibm.watson.catalyst.jumpqa.splitter.ISplitter;
import com.ibm.watson.catalyst.jumpqa.splitter.SplitterFactory;
import com.ibm.watson.catalyst.jumpqa.trec.Trec;

/**
 * The basic implementation of a template.
 * 
 * @author Will Beason
 * @version 0.1.0
 * @since 0.1.0
 *
 */
public abstract class ATemplate implements ITemplate {
  
  private final ISplitter _answerSplitter;
  private final String _templateId;
  
  /**
   * Instantiates a template
   * @param aTemplateId the id of the template
   * @param aAnswerSize how large the answer should be
   */
  public ATemplate(final String aTemplateId, final String aAnswerSize) {
    this(aTemplateId, SplitterFactory.build(aAnswerSize));
  }
  
  /**
   * @param aTemplateId the id of the template
   * @param aAnswerSplitter a splitter to break the TREC into answer units
   */
  public ATemplate(final String aTemplateId, final ISplitter aAnswerSplitter) {
    _templateId = aTemplateId;
    _answerSplitter = aAnswerSplitter;
  }
  
  @Override
  public final List<ITemplateMatch> genMatchesFromTrecs(final Collection<Trec> trecs) {
    TMF.setTemplateId(_templateId);
    TMF.setState("APPROVED");
    
    final Collection<Trec> goodTrecs = IteratorUtils.toList(trecs.iterator());
    goodTrecs.removeIf((trec) -> !goodTrec(trec));
    
    final List<ITemplateMatch> result = new ArrayList<ITemplateMatch>();
    goodTrecs.forEach((trec) -> result.addAll(genMatchesFromTrec(trec)));
    return result;
  }
  
  /**
   * Checks whether the id of this template matches a value.
   * @param anObject the id to check against
   * @return whether the id matches
   */
  public final boolean idMatches(final Object anObject) {
    return _templateId.equals(anObject);
  }
  
  /**
   * Generates matches from a given string.
   * @param aString a string to search through and generate matches
   * @return a collection of matches
   */
  protected abstract Collection<ITemplateMatch> genMatchesFromString(String aString);
  
  /**
   * Generates matches from a list of strings.
   * @param strings the strings to iterate through
   * @return
   */
  protected final Collection<ITemplateMatch> genMatchesFromStrings(final Collection<String> strings) {
    final Collection<ITemplateMatch> result = new ArrayList<ITemplateMatch>();
    strings.forEach((s) -> result.addAll(genMatchesFromString(s)));
    return result;
  }
  
  /**
   * Generates matches from a TREC
   * @param aTrec the TREC to search through and generate matches for
   * @return a collection of matches
   */
  protected final Collection<ITemplateMatch> genMatchesFromTrec(final Trec aTrec) {
    TMF.setPauTitle(aTrec.getPauTitle());
    TMF.setPauId(aTrec.getPauId());
    final List<String> strings = _answerSplitter.split(aTrec.getParagraphs());
    strings.removeIf((s) -> !goodString(s));
    return genMatchesFromStrings(strings);
  }
  
  /**
   * Runs heuristics on a string to determine whether the template should process it
   * @param aString a string to evaluate
   * @return whether the string is suitable for processing
   */
  protected abstract boolean goodString(String aString);
  
  /**
   * Runs heuristics on a TREC to determine whether the template should process it
   * @param aTrec a TREC to evaluate
   * @return whether the TREC is suitable for processing
   */
  protected abstract boolean goodTrec(Trec aTrec);
  
  /**
   * Uses a single TemplateMatchFactory so that question IDs do not overlap.
   */
  protected static final TemplateMatchFactory TMF = TemplateMatchFactory.getInstance();
  
}
