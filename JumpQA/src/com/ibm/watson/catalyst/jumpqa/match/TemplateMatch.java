package com.ibm.watson.catalyst.jumpqa.match;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

public class TemplateMatch implements ITemplateMatch {
  
  public TemplateMatch(String questionId, String questionText, String answerText,
      String pauTitle, String pauId, String state, String clusterId,
      String templateId) {
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
  public Iterator<String> iterator() {
    List<String> result = new ArrayList<String>();
    result.add(_questionId);
//    String[] words = WordSplitter.split(_questionText);
//    result.add(words.length > 2 ? words[2] : "");
//    result.add(words[words.length - 1]);
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
  
  public String getTemplateId() {
    return _templateId;
  }
  
  @Override
  public boolean equals(Object o) {
    if (o instanceof TemplateMatch) {
      TemplateMatch tm = (TemplateMatch) o;
      if (!tm._questionText.equals(_questionText)) return false;
      if (!tm._answerText.equals(_answerText)) return false;
      return true;
    } else {
      return false;
    }
  }
  
  @Override
  public int hashCode() {
    return (_questionText + _answerText).hashCode();
  }
  
  private final String _questionId;
  private final String _questionText;
  private final String _answerText;
  private final String _pauTitle;
  private final String _pauId;
  private final String _clusterId;
  private final String _state;
  
  private final String _templateId;
  
}
