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
