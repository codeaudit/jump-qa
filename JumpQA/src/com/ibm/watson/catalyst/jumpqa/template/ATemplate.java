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
import java.util.List;

import org.apache.commons.collections4.IteratorUtils;

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
  public final List<ITemplateMatch> genMatchesFromTrecs(Iterable<Trec> trecs) {
    TMF.setTemplateId(_templateId);
    TMF.setState("APPROVED");
    
    Collection<Trec> goodTrecs = IteratorUtils.toList(trecs.iterator());
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
