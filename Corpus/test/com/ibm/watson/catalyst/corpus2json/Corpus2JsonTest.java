package com.ibm.watson.catalyst.corpus2json;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

public class Corpus2JsonTest {
  
  @Test
  public void testMain() {
    Corpus2Json.main(new String[] { "sample/test.properties"} );
    File file1 = new File("sample/test-check.json");
    File file2 = new File("sample/test-output.json");
    try {
      assertTrue(FileUtils.contentEquals(file1, file2));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
  
}
