package com.ibm.watson.catalyst.jumpqa.template;

import java.util.List;

import com.ibm.watson.catalyst.jumpqa.match.ITemplateMatch;
import com.ibm.watson.catalyst.jumpqa.trec.Trec;

public interface ITemplate {
  
  public List<ITemplateMatch> genMatchesFromTrecs(List<Trec> trecs);
  
}
