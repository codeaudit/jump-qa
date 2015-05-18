package com.ibm.watson.catalyst.jumpqa.template;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.ibm.watson.catalyst.jumpqa.heuristics.BooleanHeuristics;
import com.ibm.watson.catalyst.jumpqa.match.ITemplateMatch;
import com.ibm.watson.catalyst.jumpqa.matcher.StringRegexMatcher;
import com.ibm.watson.catalyst.jumpqa.questioner.IQuestioner;
import com.ibm.watson.catalyst.jumpqa.questioner.RegexSplitQuestioner;
import com.ibm.watson.catalyst.jumpqa.splitter.ISplitter;
import com.ibm.watson.catalyst.jumpqa.splitter.SplitterFactory;
import com.ibm.watson.catalyst.jumpqa.stringcleaner.IStringCleaner;
import com.ibm.watson.catalyst.jumpqa.stringcleaner.StringCleaner;
import com.ibm.watson.catalyst.jumpqa.trec.Trec;
import com.ibm.watson.catalyst.jumpqa.wordlist.WordList;
import com.ibm.watson.catalyst.jumpqa.wordreplacer.WordReplacer;

public class TextTemplate extends ATemplate {
  
  public TextTemplate(String aTemplateId, String aAnswerSize,
      String aMatchSize, String aQuestion, String aRegex, String aWordList, String words3, String clean) {
    super(aTemplateId, aAnswerSize);
    _matcher = new StringRegexMatcher(aRegex, Pattern.CASE_INSENSITIVE);
    _matchSplitter = SplitterFactory.createSplitter(aMatchSize);
    _questioner = new RegexSplitQuestioner(aQuestion);
    _cleaner = new StringCleaner(clean);
    _replacer = new WordReplacer(words3);
    
    if (!aWordList.equals("")) {
      WordList wl = new WordList("WordLists/" + aWordList);
      _bh.add((s) -> wl.containsFirstWord(s));
      _bh.add((s) -> wl.containsLastWord(s));
      _bh.add((s) -> s.contains(" - "));
    }
  }
  
  @Override
  protected boolean goodTrec(Trec aTrec) {
    return true;
  }
  
  @Override
  protected boolean goodString(String aString) {
    return _matcher.matches(aString);
  }
  
  @Override
  protected List<ITemplateMatch> genMatchesFromString(String aString) {
    TMF.setAnswerText(aString.trim());
    List<String> splits = _matchSplitter.split(aString);

    List<ITemplateMatch> result = new ArrayList<ITemplateMatch>();
    splits.forEach((s) -> result.addAll(genMatchesFromSplit(s)));
    
    return result;
  }
  
  protected List<ITemplateMatch> genMatchesFromSplit(String aString) {
    System.out.println(aString);
    List<ITemplateMatch> result = new ArrayList<ITemplateMatch>();
    String cleanedString = _cleaner.clean(aString);
    System.out.println(cleanedString);
    if (!goodString(cleanedString)) return result;
    String[] splits = _matcher.split(cleanedString);
    splits[1] = _replacer.replace(splits[1]);
    
    if (_bh.anyTrue(cleanedString)) return result;
    
    TMF.setQuestionText(_questioner.makeQuestion(splits));
    result.add(TMF.build());
    return result;
    
  }
  
  private final StringRegexMatcher _matcher;
  private final ISplitter _matchSplitter;
  private final IStringCleaner _cleaner;
  private final WordReplacer _replacer;
  private final IQuestioner _questioner;
  private final BooleanHeuristics<String> _bh = new BooleanHeuristics<String>();
  
}
