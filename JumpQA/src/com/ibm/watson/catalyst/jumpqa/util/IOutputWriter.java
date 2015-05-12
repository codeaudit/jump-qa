package com.ibm.watson.catalyst.jumpqa.util;

public interface IOutputWriter {
  
  public void write(Iterable<? extends IWritable> writable);
  
}
