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
