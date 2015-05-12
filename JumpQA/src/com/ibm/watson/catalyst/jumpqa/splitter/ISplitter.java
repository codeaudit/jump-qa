package com.ibm.watson.catalyst.jumpqa.splitter;

import java.util.List;

public interface ISplitter {
  
  public List<String> split(List<String> strings);
  public List<String> split(String aString);
  
}
