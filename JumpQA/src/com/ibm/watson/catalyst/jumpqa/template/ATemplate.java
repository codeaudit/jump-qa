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

import com.ibm.watson.catalyst.jumpqa.answer.GroundTruthEntryBuilder;
import com.ibm.watson.catalyst.jumpqa.answer.GroundTruthEntry.GroundTruthState;
import com.ibm.watson.catalyst.jumpqa.answer.Pau;
import com.ibm.watson.catalyst.jumpqa.entry.IGroundTruthEntry;
import com.ibm.watson.catalyst.jumpqa.splitter.ISplitter;
import com.ibm.watson.catalyst.jumpqa.splitter.SplitterFactory;
import com.ibm.watson.catalyst.jumpqa.trec.Trec;

/**
 * The basic implementation of a template.
 * 
 * @author Will Beason
 * @version 0.1.1
 * @since 0.1.0
 *
 */
public abstract class ATemplate implements ITemplate {
  
  private final ISplitter _answerSplitter;
  private final String _templateId;
  
  /**
   * Instantiates a template
   * 
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
  public final List<IGroundTruthEntry> genEntriesFromTrecs(final Collection<Trec> trecs) {
    gteb.setTemplateId(_templateId);
    gteb.setState(GroundTruthState.APPROVED);
    
    final Collection<Trec> goodTrecs = IteratorUtils.toList(trecs.iterator());
    goodTrecs.removeIf((trec) -> !goodTrec(trec));
    
    final List<IGroundTruthEntry> result = new ArrayList<IGroundTruthEntry>();
    goodTrecs.forEach((trec) -> result.addAll(genMatchesFromTrec(trec)));
    return result;
  }
  
  /**
   * Checks whether the id of this template matches a value.
   * 
   * @param anObject the id to check against
   * @return whether the id matches
   */
  public final boolean idMatches(final Object anObject) {
    return _templateId.equals(anObject);
  }
  
  @Override
  public abstract Collection<IGroundTruthEntry> genEntriesFromString(final Pau aPau,
      final String aString);
  
  /**
   * TODO: Method description
   * 
   * @param aString the string to generate matches from
   * @return the collection of ground truth entries
   */
  public Collection<IGroundTruthEntry> genEntriesFromString(String aString) {
    return genEntriesFromString(new Pau(), aString);
  }
  
  /**
   * Generates matches from a list of strings.
   * 
   * @param strings the strings to iterate through
   * @return
   */
  protected final Collection<IGroundTruthEntry> genMatchesFromStrings(final Pau aPau,
      final Collection<String> strings) {
    final Collection<IGroundTruthEntry> result = new ArrayList<IGroundTruthEntry>();
    strings.forEach((s) -> result.addAll(genEntriesFromString(aPau, s)));
    return result;
  }
  
  /**
   * Generates matches from a TREC
   * 
   * @param aTrec the TREC to search through and generate matches for
   * @return a collection of matches
   */
  public final Collection<IGroundTruthEntry> genMatchesFromTrec(final Trec aTrec) {
    final Pau pau = aTrec.getPau();
    final List<String> strings = _answerSplitter.split(aTrec.getParagraphs());
    strings.removeIf((s) -> !goodString(s));
    return genMatchesFromStrings(pau, strings);
  }
  
  /**
   * Runs heuristics on a string to determine whether the template should
   * process it
   * 
   * @param aString a string to evaluate
   * @return whether the string is suitable for processing
   */
  protected boolean goodString(String aString) {
    return true;
  }
  
  /**
   * Runs heuristics on a TREC to determine whether the template should process
   * it
   * 
   * @param aTrec a TREC to evaluate
   * @return whether the TREC is suitable for processing
   */
  protected boolean goodTrec(Trec aTrec) {
    return true;
  }
  
  /**
   * Uses a single TemplateMatchFactory so that question IDs do not overlap.
   */
  protected static final GroundTruthEntryBuilder gteb = GroundTruthEntryBuilder.getInstance();
  
}
