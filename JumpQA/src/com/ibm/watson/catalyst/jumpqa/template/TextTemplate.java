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
import java.util.regex.Pattern;

import com.ibm.watson.catalyst.jumpqa.heuristics.BooleanHeuristics;
import com.ibm.watson.catalyst.jumpqa.match.ITemplateMatch;
import com.ibm.watson.catalyst.jumpqa.matcher.StringRegexMatcher;
import com.ibm.watson.catalyst.jumpqa.questioner.IQuestioner;
import com.ibm.watson.catalyst.jumpqa.questioner.RegexSplitQuestioner;
import com.ibm.watson.catalyst.jumpqa.splitter.ISplitter;
import com.ibm.watson.catalyst.jumpqa.splitter.SplitterGetter;
import com.ibm.watson.catalyst.jumpqa.stringcleaner.IStringCleaner;
import com.ibm.watson.catalyst.jumpqa.stringcleaner.StringCleaner;
import com.ibm.watson.catalyst.jumpqa.trec.Trec;
import com.ibm.watson.catalyst.jumpqa.wordlist.WordList;
import com.ibm.watson.catalyst.jumpqa.wordreplacer.SequentialReplacer;

/**
 * A template which evaluates the text of a TREC to generate matches.
 * 
 * @author Will Beason
 * @version 0.1.0
 * @since 0.1.0
 *
 */
public class TextTemplate extends ATemplate {
  
  private final BooleanHeuristics<String> _bh = new BooleanHeuristics<String>();
  
  private final IStringCleaner _cleaner;
  
  private final StringRegexMatcher _matcher;
  
  private final ISplitter _matchSplitter;
  
  private final IQuestioner _questioner;
  
  private final SequentialReplacer _replacer;
  
  /**
   * 
   * @param aTemplateId the id of the template
   * @param aAnswerSize how large the answer should be
   * @param aMatchSize the size of the text to match against
   * @param aQuestion the question generator
   * @param aRegex the regular expression to search for
   * @param aBadWordsList a set of words which should not appear in certain positions of a question
   * @param words3 a set of words which should be replaced by specified alternatives
   * @param clean whether to clean question strings with the StringCleaner class
   */
  public TextTemplate(final String aTemplateId, final String aAnswerSize, final String aMatchSize,
      final String aQuestion, final String aRegex, final String aBadWordsList, final String words3,
      final String clean) {
    super(aTemplateId, aAnswerSize);
    _matcher = new StringRegexMatcher(aRegex, Pattern.CASE_INSENSITIVE);
    _matchSplitter = SplitterGetter.getSplitter(aMatchSize);
    _questioner = new RegexSplitQuestioner(aQuestion);
    _cleaner = new StringCleaner(clean);
    _replacer = new SequentialReplacer(words3);
    
    if (!aBadWordsList.equals("")) {
      final WordList wl = new WordList("WordLists/" + aBadWordsList);
      _bh.add((s) -> wl.containsFirstWord(s));
      _bh.add((s) -> wl.containsLastWord(s));
      _bh.add((s) -> s.contains(" - "));
    }
  }
  
  /**
   * Generates matches from string
   * @param aString
   * @return
   */
  protected Collection<ITemplateMatch> genMatchesFromSplit(final String aString) {
    final Collection<ITemplateMatch> result = new ArrayList<ITemplateMatch>();
    final String cleanedString = _cleaner.clean(aString);
    if (!goodString(cleanedString)) return result;
    final String[] splits = _matcher.split(cleanedString);
    splits[1] = _replacer.replace(splits[1]);
    
    if (_bh.anyTrue(cleanedString)) return result;
    
    TMF.setQuestionText(_questioner.makeQuestion(splits));
    result.add(TMF.build());
    return result;
    
  }
  
  @Override
  protected Collection<ITemplateMatch> genMatchesFromString(final String aString) {
    TMF.setAnswerText(aString.trim());
    final Collection<String> splits = _matchSplitter.split(aString);
    
    final Collection<ITemplateMatch> result = new ArrayList<ITemplateMatch>();
    splits.forEach((s) -> result.addAll(genMatchesFromSplit(s)));
    
    return result;
  }
  
  @Override
  protected boolean goodString(final String aString) {
    return _matcher.matches(aString);
  }
  
  @Override
  protected boolean goodTrec(final Trec aTrec) {
    return true;
  }
  
}
