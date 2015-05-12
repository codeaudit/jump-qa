package com.ibm.watson.catalyst.jumpqa.template;

import java.util.ArrayList;
import java.util.List;

import com.ibm.watson.catalyst.jumpqa.match.ITemplateMatch;
import com.ibm.watson.catalyst.jumpqa.match.TemplateMatchFactory;
import com.ibm.watson.catalyst.jumpqa.splitter.ISplitter;
import com.ibm.watson.catalyst.jumpqa.splitter.SplitterFactory;
import com.ibm.watson.catalyst.jumpqa.trec.Trec;

public abstract class ATemplate implements ITemplate {
  
  public ATemplate(String aTemplateId, String aAnswerSize) {
    _templateId = aTemplateId;
    _answerSplitter = SplitterFactory.createSplitter(aAnswerSize);
  }
  
  @Override
  public final List<ITemplateMatch> genMatchesFromTrecs(List<Trec> trecs) {
    TMF.setTemplateId(_templateId);
    TMF.setState("APPROVED");
    
    List<Trec> goodTrecs = new ArrayList<Trec>(trecs);
    goodTrecs.removeIf((trec) -> !goodTrec(trec));
    
    List<ITemplateMatch> result = new ArrayList<ITemplateMatch>();
    goodTrecs.forEach((trec) -> result.addAll(genMatchesFromTrec(trec)));
    return result;
  }
  
  public final boolean idMatches(Object anObject) {
    return _templateId.equals(anObject);
  }
  
  protected static final TemplateMatchFactory TMF = TemplateMatchFactory.getInstance();
  
  protected abstract boolean goodTrec(Trec aTrec);
  protected abstract boolean goodString(String aString);
  protected abstract List<ITemplateMatch> genMatchesFromString(String aString);
  
  protected final List<ITemplateMatch> genMatchesFromStrings(List<String> strings) {
    List<ITemplateMatch> result = new ArrayList<ITemplateMatch>();
    strings.forEach((s) -> result.addAll(genMatchesFromString(s)));
    return result;
  }
  
  protected final List<ITemplateMatch> genMatchesFromTrec(Trec aTrec) {
    TMF.setPauTitle(aTrec.getPauTitle());
    TMF.setPauId(aTrec.getPauId());
    List<String> strings = _answerSplitter.split(aTrec.getParagraphs());
    strings.removeIf((s) -> !goodString(s));
    return genMatchesFromStrings(strings);
  }
  
  private final String _templateId;
  private final ISplitter _answerSplitter;
  
}
