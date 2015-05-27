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

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

import com.ibm.watson.catalyst.jumpqa.util.AReader;
import com.ibm.watson.catalyst.jumpqa.util.IReader;

/**
 * Reads templates from a file and returns a collection containing the templates. 
 * 
 * @author Will Beason
 * @version 0.1.0
 * @since 0.1.0
 *
 */
public class TemplateReader extends AReader<ITemplate> implements IReader {
  
  @Override
  public List<ITemplate> read(final InputStream is) throws IOException {
    List<ITemplate> result = new ArrayList<ITemplate>();
    try (CSVParser parser = new CSVParser(new InputStreamReader(is), format)) {
      final TemplateFactory tf = new TemplateFactory();
      parser.forEach((record) -> result.add(tf.readRecord(record)));
    }
    
    logger.info("Read " + result.size() + " templates");
    return result;
  }
  
  private static final CSVFormat format = CSVFormat.RFC4180.withHeader().withDelimiter('\t');
  
  private static final Logger logger = Logger.getLogger(TemplateReader.class.getName());
  
}
