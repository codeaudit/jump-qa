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
  
  private final List<String> _wordList;
  
  public WordList(final List<String> aStringList) {
    _wordList = aStringList;
  }
  
  public WordList(final String aFile) {
    this((new StringListReader()).read(aFile));
  }
  
  @Override
  public boolean contains(final Object o) {
    return _wordList.contains(o.toString().toLowerCase());
  }
  
  public boolean containsFirstWord(final String s) {
    final String[] words = WordSplitter.split(s);
    return contains(words[0]);
  }
  
  public boolean containsLastWord(final String s) {
    final String[] words = WordSplitter.split(s);
    return contains(words[words.length - 1]);
  }
  
  @Override
  public Iterator<String> iterator() {
    return _wordList.iterator();
  }
  
}
