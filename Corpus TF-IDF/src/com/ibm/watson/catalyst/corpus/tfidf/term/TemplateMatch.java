package com.ibm.watson.catalyst.corpus.tfidf.term;

import java.util.ArrayList;
import java.util.List;

public final class TemplateMatch {
  
  public TemplateMatch(String aMatch, String aSentence, String qStart, String qEnd, String aAnswer) {
    _match = aMatch;
    _sentence = aSentence;
    _question = qStart + _match + qEnd;
    _answers.add(aAnswer);
  }
  
  public boolean addAnswer(String aAnswer) {
    if (_answers.contains(aAnswer)) {
      return false;
    } else {
      _answers.add(aAnswer);
      return true;
    }
  }
  
  public boolean sameQuestion(TemplateMatch tm) {
    return tm.getQuestion().equals(_question);
  }
  
  public void addAnswers(TemplateMatch tm) {
    for (String answer : tm.getAnswers()) {
      if (!hasAnswer(answer)) addAnswer(answer);
    }
  }
  
  public String toString() {
    StringBuffer result = new StringBuffer();
    
    for (String answer : _answers) {
      result.append("\"")
            .append(_question)
            .append("\",\"")
            .append(answer)
            .append("\"\n");
    }
    
    return result.toString();
  }
  
  public String getMatch() { return _match; }
  public String getSentence() { return _sentence; }
  public String getQuestion() { return _question; }
  public List<String> getAnswers() { return _answers; }
  
  // Private

  private final String _match;
  private final String _sentence;
  private final String _question;
  private final List<String> _answers = new ArrayList<String>();
  
  private boolean hasAnswer(String answer) {
    return _answers.contains(answer);
  }
  
}
