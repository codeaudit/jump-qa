package com.ibm.watson.catalyst.jumpqa;

import java.io.File;
import java.util.Collection;
import java.util.logging.Logger;

import com.ibm.watson.catalyst.jumpqa.match.ITemplateMatch;
import com.ibm.watson.catalyst.jumpqa.matcher.MatchGenerator;
import com.ibm.watson.catalyst.jumpqa.template.ITemplate;
import com.ibm.watson.catalyst.jumpqa.template.TemplateReader;
import com.ibm.watson.catalyst.jumpqa.trec.Trec;
import com.ibm.watson.catalyst.jumpqa.trec.TrecReader;
import com.ibm.watson.catalyst.jumpqa.util.CSVOutputWriter;
import com.ibm.watson.catalyst.util.baseproperties.BaseProperties;

public final class JumpQA {
  
  /**
   * Private
   */
  private static BaseProperties PROPERTIES;
  private final static Logger logger = Logger.getLogger(JumpQA.class.getName());
  
  private static File getFile(String key) { return PROPERTIES.getFile(key); }
  
  /**
   * Main
   */
  public static void main(String[] args) {
    logger.info("JumpQA start");
    PROPERTIES = BaseProperties.setInstance(args, "sample/test.properties");
    
    Collection<ITemplate> templates = (new TemplateReader()).read(getFile("templates"));
    Collection<Trec> trecs = (new TrecReader()).read(getFile("corpus"));
    Collection<ITemplateMatch> matches = (new MatchGenerator()).genMatches(templates, trecs);
    
    (new CSVOutputWriter(getFile("output"))).write(matches);
    logger.info("JumpQA end");
  }
  
}
