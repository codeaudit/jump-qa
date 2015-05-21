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

/**
 * 
 * @author wabeason
 *
 */
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
