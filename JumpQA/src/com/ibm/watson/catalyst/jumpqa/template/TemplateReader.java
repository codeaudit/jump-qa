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

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

import com.ibm.watson.catalyst.jumpqa.util.IReader;

/**
 * Reads templates from a file and returns a collection containing the templates. 
 * 
 * @author Will Beason
 * @version 0.1.0
 * @since 0.1.0
 *
 */
public class TemplateReader implements IReader {
  
  @Override
  public Collection<ITemplate> read(final File aFile) {
    logger.info("Reading templates from " + aFile);
    final Collection<ITemplate> result = new ArrayList<ITemplate>();
    
    try (CSVParser parser = CSVParser.parse(aFile, UTF_8, format)) {
      final TemplateFactory tf = new TemplateFactory();
      parser.forEach((record) -> result.add(tf.readRecord(record)));
    } catch (final IOException e) {
      throw new RuntimeException("Could not read templates file " + aFile, e);
    }
    
    logger.info("Read " + result.size() + " templates");
    return result;
  }
  
  private static final CSVFormat format = CSVFormat.RFC4180.withHeader().withDelimiter('\t');
  
  private static final Logger logger = Logger.getLogger(TemplateReader.class.getName());
  private static final Charset UTF_8 = StandardCharsets.UTF_8;
  
}
