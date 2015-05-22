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
package com.ibm.watson.catalyst.jumpqa.splitter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * A class for splitting strings into words
 * 
 * @author Will Beason
 * @version 0.1.0
 * @since 0.1.0
 *
 */
public class WordSplitter implements ISplitter {
  
  private static final Pattern WORDSPLIT = Pattern.compile("[?!,;:\\s]+");
  
  @Override
  public List<String> split(final String aString) {
    return Arrays.asList(WORDSPLIT.split(aString));
  }
  
  @Override
  public List<String> split(final List<String> strings) {
    final List<String> result = new ArrayList<String>();
    strings.forEach((string) -> {
      result.addAll(split(string));
    });
    return result;
  }
  
}
