package com.ibm.watson.catalyst.jumpqa.splitter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class ParagraphSplitter implements ISplitter {

  @Override
  public List<String> split(List<String> strings) {
    List<String> result = new ArrayList<String>();
    strings.forEach((string) -> { result.addAll(split(string)); });
    return result;
  }

  @Override
  public List<String> split(String aString) {
    return Arrays.asList(PARAGRAPHSPLIT.split(aString));
  }
  
  private static final Pattern PARAGRAPHSPLIT = 
      Pattern.compile("\n");
  
}
