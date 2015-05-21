package com.ibm.watson.catalyst.jumpqa.matcher;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.logging.Logger;

import com.ibm.watson.catalyst.jumpqa.match.ITemplateMatch;
import com.ibm.watson.catalyst.jumpqa.template.ITemplate;
import com.ibm.watson.catalyst.jumpqa.trec.Trec;

public class MatchGenerator {

  private static Logger logger = Logger.getLogger(MatchGenerator.class.getName());
  
  public MatchGenerator() { }
  
  public Collection<ITemplateMatch> genMatches(
      Collection<ITemplate> templates,
      Collection<Trec> trecs) {
    
    logger.info("Generating matches");
    Collection<ITemplateMatch> result = Collections.synchronizedSet(new HashSet<ITemplateMatch>());
    templates.forEach((template) -> result.addAll(template.genMatchesFromTrecs(trecs)));
    logger.info("Generated " + result.size() + " matches");
    return result;
  }
  
}
