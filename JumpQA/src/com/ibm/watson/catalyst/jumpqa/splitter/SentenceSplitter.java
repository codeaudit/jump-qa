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

public class SentenceSplitter implements ISplitter {
  
  public List<String> split(List<String> strings) {
    List<String> result = new ArrayList<String>();
    strings.forEach((string) -> { result.addAll(split(string)); });
    return result;
  }
  
  public List<String> split(String aString) {
    return Arrays.asList(SENTENCESPLIT.split(aString));
  }
  
  private static final Pattern SENTENCESPLIT =
      Pattern.compile("[\\.!?]([\"']|\\s*\\[[^\\]^\\[]*\\])*(\\s+|$)");
  
}
