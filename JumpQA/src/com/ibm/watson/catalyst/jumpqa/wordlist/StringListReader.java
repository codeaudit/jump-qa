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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import com.ibm.watson.catalyst.jumpqa.util.IReader;

/**
 * A class for reading a file and creating a list where each entry is a line from the file.
 * 
 * @author Will Beason
 * @version 0.1.0
 * @since 0.1.0
 *
 */
public class StringListReader implements IReader {
  
  @Override
  public List<String> read(final File aFile) {
    final List<String> result = new ArrayList<String>();
    try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(aFile),
        ENCODING))) {
      while (br.ready()) {
        final String line = br.readLine();
        if (line.equals("") || line.startsWith("#")) {
          continue;
        } else {
          result.add(line);
        }
      }
    } catch (final UnsupportedEncodingException e) {
      throw new RuntimeException("Unsupported encoding: " + ENCODING, e);
    } catch (final FileNotFoundException e) {
      throw new RuntimeException("Unable to find " + aFile, e);
    } catch (final IOException e) {
      throw new RuntimeException("IOError reading " + aFile, e);
    }
    return result;
  }
  
  /**
   * Reads a file to generate a list of strings
   * @param aString the file to read
   * @return the list of strings read from the file
   */
  public List<String> read(final String aString) {
    return read(new File(aString));
  }
  
  private static final String ENCODING = "UTF8";
  
}
