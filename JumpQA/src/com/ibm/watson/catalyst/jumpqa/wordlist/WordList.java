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

import java.util.Iterator;
import java.util.List;

import com.ibm.watson.catalyst.jumpqa.splitter.WordSplitter;

public class WordList implements IWordList, Iterable<String> {
  
  public WordList(String aFile) {
    this((new StringListReader()).read(aFile));
  }
  
  public WordList(List<String> aStringList) {
    _wordList = aStringList;
  }
  
  @Override
  public boolean contains(Object o) {
    return _wordList.contains(o.toString().toLowerCase());
  }
  
  @Override
  public Iterator<String> iterator() {
    return _wordList.iterator();
  }
  
  public boolean containsFirstWord(String s) {
    String[] words = WordSplitter.split(s);
    return contains(words[0]);
  }
  
  public boolean containsLastWord(String s) {
    String[] words = WordSplitter.split(s);
    return contains(words[words.length - 1]);
  }
  
  private final List<String> _wordList;
  
}
