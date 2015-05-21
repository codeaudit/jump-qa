package com.ibm.watson.catalyst.jumpqa.template;

import java.util.Collection;

import com.ibm.watson.catalyst.jumpqa.match.ITemplateMatch;
import com.ibm.watson.catalyst.jumpqa.trec.Trec;

public interface ITemplate {
  
  public Collection<ITemplateMatch> genMatchesFromTrecs(Iterable<Trec> trecs);
  
}
