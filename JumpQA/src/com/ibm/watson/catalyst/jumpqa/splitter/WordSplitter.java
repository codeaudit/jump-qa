package com.ibm.watson.catalyst.jumpqa.splitter;

import java.util.regex.Pattern;

public class WordSplitter {
  
  private static final Pattern WORDSPLIT = Pattern.compile("[?!,;:\\s]+");
  public static String[] split(String aString) {
    return WORDSPLIT.split(aString);
  }
  
}
