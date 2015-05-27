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
package com.ibm.watson.catalyst.jumpqa.wordreplacer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import com.ibm.watson.catalyst.jumpqa.util.AReader;
import com.ibm.watson.catalyst.jumpqa.util.IReader;

/**
 * Reads files to creates hashtables of search and replacement strings
 * 
 * @author Will Beason
 * @version 0.1.0
 * @since 0.1.0
 *
 */
public class ReplacerReader extends AReader<Replacer> implements IReader {
  
  /**
   * @param aType the type of replacers to create
   */
  public ReplacerReader(ReplacerType aType) {
    _type = aType;
  }
  
  /**
   * 
   */
  public ReplacerReader() {
    this(ReplacerType.NORMAL);
  }
  
  /** TODO: Class description
   * 
   * @author Will Beason
   * @version 0.1.0
   * @since 0.1.0
   *
   */
  protected enum ReplacerType {
    /**
     * No changes are made to the passed patterns
     */
    NORMAL,
    /**
     * Word boundaries are appended and prepended to passed patterns.
     */
    WORD }
  
  @Override
  public List<Replacer> read(final InputStream is) throws IOException {
    final List<Replacer> result = new ArrayList<Replacer>();
    
    try (BufferedReader br = new BufferedReader(new InputStreamReader(is, ENCODING))) {
      while (br.ready()) {
        final String line = br.readLine();
        if (isCommentOrEmpty(line)) continue;
        result.add(line2Replacer(line));
      }
    } catch (final UnsupportedEncodingException e) {
      throw new RuntimeException("Unsupported encoding: " + ENCODING, e);
    }
    
    return result;
  }
  
  private Replacer line2Replacer(String line) {
    final String[] entry = line.split("=", 2);
    final String replacement = entry[1];
    String pattern;
    switch(_type) {
      case NORMAL:
        pattern = entry[0];
        break;
      case WORD:
        pattern = "\\b" + entry[0] + "\\b";
        break;
      default:
        throw new IllegalArgumentException();
    }
    return new Replacer(pattern, replacement);
  }
  
  private final ReplacerType _type;
  
  private static final String ENCODING = "UTF8";
  
}
