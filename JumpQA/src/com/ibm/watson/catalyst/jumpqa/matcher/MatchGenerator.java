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

/**
 * A class for handling the logic of iterating through templates on the collection of TRECs
 * 
 * @author Will Beason
 * @version 0.1.0
 * @since 0.1.0
 *
 */
public class MatchGenerator {
  
  /**
   * Instantiates a MatchGenerator
   */
  public MatchGenerator() {}
  
  /**
   * Generates matches for a collection of templates on a collection of TRECs
   * @param templates the templates to generate matches with
   * @param trecs the TRECs to search through and generate matches
   * @return the found and generated matches
   */
  public Collection<ITemplateMatch> genMatches(final Collection<ITemplate> templates,
      final Collection<Trec> trecs) {
    
    logger.info("Generating matches");
    final Collection<ITemplateMatch> result =
        Collections.synchronizedSet(new HashSet<ITemplateMatch>());
    templates.forEach((template) -> result.addAll(template.genMatchesFromTrecs(trecs)));
    logger.info("Generated " + result.size() + " matches");
    return result;
  }
  
  private static Logger logger = Logger.getLogger(MatchGenerator.class.getName());
  
}
