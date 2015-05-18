package com.ibm.watson.catalyst.corpus.tfidf;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

public class CorpusTfidfTest {

  @Test
  public void testMain() {
    CorpusTfidf.main(new String[] {"sample/test.properties"});
    File file1 = new File("sample/test-tfidf-check.json");
    File file2 = new File("sample/test-tfidf-output.json");
    try {
      assertTrue(FileUtils.contentEquals(file1, file2));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

}
