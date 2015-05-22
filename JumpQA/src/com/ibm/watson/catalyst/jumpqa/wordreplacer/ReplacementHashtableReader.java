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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Hashtable;
import java.util.regex.Pattern;

public class ReplacementHashtableReader {
  
  public Hashtable<Pattern, String> read(final String aFile) {
    final Hashtable<Pattern, String> result = new Hashtable<Pattern, String>();
    if (aFile.equals("")) return result;
    
    try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(
        "WordLists/" + aFile), ENCODING))) {
      while (br.ready()) {
        final String line = br.readLine();
        if (line.equals("") || line.startsWith("#")) {
          continue;
        } else {
          final String[] entry = line.split("=", 2);
          final Pattern p = Pattern.compile("\\b" + entry[0] + "\\b");
          final String r = entry[1];
          result.put(p, r);
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
  
  private static final String ENCODING = "UTF8";
  
}
