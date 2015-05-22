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
package com.ibm.watson.catalyst.jumpqa.trec;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.watson.catalyst.jumpqa.util.IReader;

/**
 * A class which reads Trec objects from a JSON file.
 * 
 * @author Will Beason
 * @version 0.1.0
 * @since 0.1.0
 *
 */
public class TrecReader implements IReader {
  
  @Override
  public List<Trec> read(final File aFile) {
    System.out.println("Reading Trecs from " + aFile);
    final List<Trec> result = new ArrayList<Trec>();
    
    JsonNode corpus;
    try {
      corpus = MAPPER.readValue(aFile, JsonNode.class);
    } catch (final JsonParseException e) {
      throw new RuntimeException("Error parsing " + aFile, e);
    } catch (final JsonMappingException e) {
      throw new RuntimeException("Error mapping " + aFile, e);
    } catch (final IOException e) {
      throw new RuntimeException("IOError reading " + aFile, e);
    }
    
    final JsonNode trecs = corpus.get("documents");
    for (final JsonNode trecJson : trecs) {
      result.add(json2trec(trecJson));
    }
    
    System.out.println("Read " + result.size() + " trecs");
    return result;
  }
  
  private static final ObjectMapper MAPPER = new ObjectMapper();
  
  private static List<String> getParagraphs(final JsonNode trecJson) {
    final List<String> result = new ArrayList<String>();
    final JsonNode paragraphs = trecJson.get("paragraphs");
    for (final JsonNode paragraph : paragraphs) {
      result.add(paragraph.asText());
    }
    return result;
  }
  
  private static Trec json2trec(final JsonNode trecJson) {
    final TrecBuilder tb = new TrecBuilder();
    // tb.setFile(trecJson.get("file").asText());
    tb.setPauId(trecJson.get("pauID").asText());
    tb.setPauTitle(trecJson.get("pauTitle").asText());
    tb.setSourceDoc(trecJson.get("sourceDoc").asText());
    tb.setParagraphs(getParagraphs(trecJson));
    return tb.build();
  }
  
}
