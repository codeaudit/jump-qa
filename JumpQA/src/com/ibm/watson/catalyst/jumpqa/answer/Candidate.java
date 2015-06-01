/**
 * 
 */
package com.ibm.watson.catalyst.jumpqa.answer;

/** TODO: Class description
 * 
 * @author Will Beason
 * @version 0.1.1
 * @since 0.1.1
 *
 */
public class Candidate {
  
  private final Answer _answer;
  private final String _matchText;
  
  /**
   * @param matchText the text to test for matches
   * @param aAnswer the answer
   */
  public Candidate(String matchText, Answer aAnswer) {
    _answer = new Answer(aAnswer);
    _matchText = matchText;
  }
  
  /** 
   * TODO: Method description
   * @return the match text
   */
  public String getMatchText() {
    return _matchText;
  }
  
  /** 
   * TODO: Method description
   * @return the answer
   */
  public Answer getAnswer() {
    return new Answer(_answer);
  }
  
}
