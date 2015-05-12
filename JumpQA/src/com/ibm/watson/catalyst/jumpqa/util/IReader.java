package com.ibm.watson.catalyst.jumpqa.util;

import java.io.File;

public interface IReader<V> {
  
  public Iterable<V> read(File aFile);
  
}
