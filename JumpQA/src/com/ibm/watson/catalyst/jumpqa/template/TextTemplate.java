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
import java.util.List;

import com.ibm.watson.catalyst.jumpqa.answer.Answer;
import com.ibm.watson.catalyst.jumpqa.answer.Candidate;
import com.ibm.watson.catalyst.jumpqa.answer.Pau;
import com.ibm.watson.catalyst.jumpqa.answer.QuestionAnswerPair;
import com.ibm.watson.catalyst.jumpqa.entry.IGroundTruthEntry;
import com.ibm.watson.catalyst.jumpqa.matcher.StringRegexMatcher;
import com.ibm.watson.catalyst.jumpqa.questioner.IQuestioner;
import com.ibm.watson.catalyst.jumpqa.replacer.SequentialReplacer;
import com.ibm.watson.catalyst.jumpqa.splitter.SplitterFactory.Size;
import com.ibm.watson.catalyst.jumpqa.stringcleaner.IStringCleaner;
import com.ibm.watson.catalyst.jumpqa.stringcleaner.StringCleaner;

/**
 * A template which evaluates the text of a TREC to generate matches.
 * 
 * @author Will Beason
 * @version 0.1.1
 * @since 0.1.0
 *
 */
public class TextTemplate extends ATemplate {
  
  private final IQuestioner _questioner;
  private final StringRegexMatcher _matcher;
  private final SequentialReplacer _replacer;
  private final IStringCleaner _cleaner;
  
  /**
   * @param aTemplateId the id of the template
   * @param aAnswerSize the size of the answer
   * @param aCandidateSize the size of the match
   * @param aQuestioner an object to take a good sentence and create questions
   *          to be asked about it
   * @param aMatcher an object to determine if a sentence is a match
   * @param aSequentialReplacer an object to sequentially make replacements on
   *          the text before transformation into a question
   * @param aCleaner an object which cleans strings
   */
  public TextTemplate(final String aTemplateId, final Size aAnswerSize,
      final Size aCandidateSize, final IQuestioner aQuestioner,
      final SequentialReplacer aSequentialReplacer, final StringCleaner aCleaner,
      final StringRegexMatcher aMatcher) {
    super(aTemplateId,
        aAnswerSize,
        aCandidateSize,
        (trec) -> aMatcher.matches(trec),
        (answer) -> aMatcher.matches(answer),
        (candidate) -> aMatcher.matches(candidate));
    _questioner = aQuestioner;
    _replacer = aSequentialReplacer;
    _matcher = aMatcher;
    _cleaner = aCleaner;
  }
  
  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    
    return true;
  }
  
  /**
   * Generates matches from string
   * 
   * @param matchText
   * @return
   */
  protected List<IGroundTruthEntry> genEntriesFromAnswer(final String matchText,
      final Answer aAnswer) {
    final List<IGroundTruthEntry> result = new ArrayList<IGroundTruthEntry>();
    final String cleanedString = _cleaner.clean(matchText);
    final String[] splits = _matcher.split(cleanedString);
    splits[1] = _replacer.replace(splits[1]);
    
    String questionText = _questioner.makeQuestion(splits);
    QuestionAnswerPair qaPair = new QuestionAnswerPair(questionText, aAnswer);
    gteb.setQaPair(qaPair);
    result.add(gteb.build());
    return result;
  }
  
  @Override
  public List<IGroundTruthEntry> genEntriesFromString(String answerText, Pau aPau) {
    return genEntriesFromAnswer(answerText, new Answer(answerText, aPau));
  }
  
  @Override
  public List<IGroundTruthEntry> genEntriesFromString(String aString) {
    return genEntriesFromString(aString, new Pau());
  }
  
  @Override
  protected List<IGroundTruthEntry> candidates2entries(List<Candidate> candidates) {
    final List<IGroundTruthEntry> result = new ArrayList<IGroundTruthEntry>();
    candidates.forEach((c) -> result.addAll(candidate2entries(c)));
    return result;
  }
  
  /** 
   * TODO: Method description
   * @param aCandidate the candidate to create entries for
   * @return the ground truth entries
   */
  @Override
  public List<IGroundTruthEntry> candidate2entries(Candidate aCandidate) {
    final List<IGroundTruthEntry> result = new ArrayList<IGroundTruthEntry>();
    final String cleanedString = _cleaner.clean(aCandidate.getMatchText());
    final String[] splits = _matcher.split(cleanedString);
    splits[1] = _replacer.replace(splits[1]);
    
    String questionText = _questioner.makeQuestion(splits);
    QuestionAnswerPair qaPair = new QuestionAnswerPair(questionText, aCandidate.getAnswer());
    gteb.setQaPair(qaPair);
    result.add(gteb.build());
    return result;
  }
  
}
