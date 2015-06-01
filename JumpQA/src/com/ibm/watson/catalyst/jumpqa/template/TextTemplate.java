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

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Pattern;

import com.ibm.watson.catalyst.jumpqa.answer.Answer;
import com.ibm.watson.catalyst.jumpqa.answer.Pau;
import com.ibm.watson.catalyst.jumpqa.answer.QuestionAnswerPair;
import com.ibm.watson.catalyst.jumpqa.entry.IGroundTruthEntry;
import com.ibm.watson.catalyst.jumpqa.heuristics.BooleanHeuristics;
import com.ibm.watson.catalyst.jumpqa.matcher.StringRegexMatcher;
import com.ibm.watson.catalyst.jumpqa.questioner.IQuestioner;
import com.ibm.watson.catalyst.jumpqa.questioner.TextTemplateQuestioner;
import com.ibm.watson.catalyst.jumpqa.replacer.SequentialReplacer;
import com.ibm.watson.catalyst.jumpqa.splitter.ISplitter;
import com.ibm.watson.catalyst.jumpqa.splitter.SplitterFactory;
import com.ibm.watson.catalyst.jumpqa.stringcleaner.IStringCleaner;
import com.ibm.watson.catalyst.jumpqa.stringcleaner.StringCleaner;
import com.ibm.watson.catalyst.jumpqa.wordlist.WordList;

/**
 * A template which evaluates the text of a TREC to generate matches.
 * 
 * @author Will Beason
 * @version 0.1.1
 * @since 0.1.0
 *
 */
public class TextTemplate extends ATemplate {
  
  private final ISplitter _matchSplitter;
  private final IQuestioner _questioner;
  private final StringRegexMatcher _matcher;
  private final BooleanHeuristics<String> _bh;
  private final SequentialReplacer _replacer;
  private final IStringCleaner _cleaner;
  
  /**
   * @param aTemplateId the id of the template
   * @param aAnswerSplitter an object to split the TREC into possible answers
   * @param aMatchSplitter an object to split answers into pieces about which to
   *          ask questions
   * @param aQuestioner an object to take a good sentence and create questions
   *          to be asked about it
   * @param aMatcher an object to determine if a sentence is a match
   * @param aBooleanHeuristics boolean conditions about a string which, if met,
   *          mean the sentence shouldn't be considered
   * @param aSequentialReplacer an object to sequentially make replacements on
   *          the text before transformation into a question
   * @param aCleaner an object to clean strings
   */
  public TextTemplate(final String aTemplateId, final ISplitter aAnswerSplitter,
      final ISplitter aMatchSplitter, final TextTemplateQuestioner aQuestioner,
      final StringRegexMatcher aMatcher, final BooleanHeuristics<String> aBooleanHeuristics,
      final SequentialReplacer aSequentialReplacer, final IStringCleaner aCleaner) {
    super(aTemplateId, aAnswerSplitter);
    _matchSplitter = aMatchSplitter;
    _questioner = aQuestioner;
    _matcher = aMatcher;
    _bh = aBooleanHeuristics;
    _replacer = aSequentialReplacer;
    _cleaner = aCleaner;
  }
  
  /**
   * Instantiates a Text Template
   * 
   * @param aTemplateId the id of the template
   * @param aAnswerSize how large the answer should be
   * @param aMatchSize the size of the text to match against
   * @param aQuestion the question generator
   * @param aRegex the regular expression to search for
   * @param aBadWordsList a set of words which should not appear in certain
   *          positions of a question
   * @param words3 a set of words which should be replaced by specified
   *          alternatives
   * @param clean whether to clean question strings with the StringCleaner class
   */
  public TextTemplate(final String aTemplateId, final String aAnswerSize, final String aMatchSize,
      final String aQuestion, final String aRegex, final String aBadWordsList, final String words3,
      final String clean) {
    super(aTemplateId, aAnswerSize);
    _matcher = new StringRegexMatcher(aRegex, Pattern.CASE_INSENSITIVE);
    _matchSplitter = SplitterFactory.build(aMatchSize);
    _questioner = new TextTemplateQuestioner(aQuestion);
    _cleaner = new StringCleaner(clean);
    _replacer = new SequentialReplacer(new File(words3));
    
    _bh = new BooleanHeuristics<String>();
    if (!aBadWordsList.equals("")) {
      final WordList wl = new WordList("WordLists/" + aBadWordsList);
      _bh.add((s) -> wl.containsFirstWord(s));
      _bh.add((s) -> wl.containsLastWord(s));
      _bh.add((s) -> s.contains(" - "));
    }
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
   * @param aString
   * @return
   */
  protected Collection<IGroundTruthEntry> genMatchesFromSplit(final Answer aAnswer,
      final String aString) {
    final Collection<IGroundTruthEntry> result = new ArrayList<IGroundTruthEntry>();
    final String cleanedString = _cleaner.clean(aString);
    if (!goodString(cleanedString)) return result;
    final String[] splits = _matcher.split(cleanedString);
    splits[1] = _replacer.replace(splits[1]);
    
    if (_bh.anyTrue(cleanedString)) return result;
    
    String questionText = _questioner.makeQuestion(splits);
    QuestionAnswerPair qaPair = new QuestionAnswerPair(questionText, aAnswer);
    gteb.setQaPair(qaPair);
    result.add(gteb.build());
    return result;
    
  }
  
  @Override
  public Collection<IGroundTruthEntry> genEntriesFromString(final Pau aPau, final String aString) {
    Answer answer = new Answer(aString, aPau);
    final Collection<String> splits = _matchSplitter.split(aString);
    
    final Collection<IGroundTruthEntry> result = new ArrayList<IGroundTruthEntry>();
    splits.forEach((s) -> result.addAll(genMatchesFromSplit(answer, s)));
    
    return result;
  }
  
}
