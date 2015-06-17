/**
 * 
 */
package com.ibm.watson.catalyst.jumpqa.template;

import java.util.List;

import com.ibm.watson.catalyst.jumpqa.answer.Candidate;
import com.ibm.watson.catalyst.jumpqa.answer.Pau;
import com.ibm.watson.catalyst.jumpqa.entry.IGroundTruthEntry;
import com.ibm.watson.catalyst.jumpqa.matcher.EntryPatterns;
import com.ibm.watson.catalyst.jumpqa.questioner.IQuestioner;
import com.ibm.watson.catalyst.jumpqa.replacer.SequentialReplacer;
import com.ibm.watson.catalyst.jumpqa.splitter.SplitterFactory.Size;

/** TODO: Class description
 * 
 * @author Will Beason
 * @version 0.1.2
 * @since 0.1.2
 *
 */
public class TitleTemplate extends ATemplate {
  
  private final IQuestioner _questioner;
  private final EntryPatterns _matcher;
  
  /**
   * @param aTemplateId
   * @param aAnswerSize
   * @param aCandidateSize
   * @param aQuestioner
   * @param aMatcher
   */
  public TitleTemplate(
      final String aTemplateId,
      final Size aAnswerSize,
      final Size aCandidateSize,
      final IQuestioner aQuestioner,
      final EntryPatterns aMatcher) {
    super(
        aTemplateId,
        aAnswerSize,
        aCandidateSize,
        (trec) -> aMatcher.matches(trec),
        (answer) -> aMatcher.matches(answer),
        (candidate) -> aMatcher.matches(candidate));
    _questioner = aQuestioner;
    _matcher = aMatcher;
  }
  
  @Override
  public List<IGroundTruthEntry> candidate2entries(Candidate aCandidate) {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public List<IGroundTruthEntry> genEntriesFromString(String aAnswerText, Pau aPau) {
    // TODO Auto-generated method stub
    return null;
  }
  
}
