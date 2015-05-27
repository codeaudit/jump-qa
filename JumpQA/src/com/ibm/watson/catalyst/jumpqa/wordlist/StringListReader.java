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
package com.ibm.watson.catalyst.jumpqa.wordlist;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.ibm.watson.catalyst.jumpqa.util.AReader;
import com.ibm.watson.catalyst.jumpqa.util.IReader;

/**
 * Creates a list of strings where each entry is a line of the supplied file or input stream.
 * 
 * @author Will Beason
 * @version 0.1.0
 * @since 0.1.0
 *
 */
public class StringListReader extends AReader<String> implements IReader {
  
  private static final Logger logger = Logger.getLogger(StringListReader.class.getName());
  
  @Override
  public List<String> read(InputStream is) throws IOException {
    final List<String> result = new ArrayList<String>();
    try (BufferedReader br = new BufferedReader(new InputStreamReader(is, ENCODING))) {
      while (br.ready()) {
        final String line = br.readLine();
        if (isCommentOrEmpty(line)) continue;
        result.add(line);
      }
    } catch (final UnsupportedEncodingException e) {
      throw new RuntimeException("Unsupported encoding: " + ENCODING, e);
    }
    return result;
  }
  
  @Override
  public List<String> read(File aFile) {
    logger.info("Reading string list from " + aFile);
    return super.read(aFile);
  }
  
  private static final String ENCODING = "UTF-8";
  
}
