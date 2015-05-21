package com.ibm.watson.catalyst.corpus2json;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import com.ibm.watson.catalyst.corpus.BasicCorpus;
import com.ibm.watson.catalyst.corpus.BasicCorpusBuilder;
import com.ibm.watson.catalyst.corpus.Corpus;
import com.ibm.watson.catalyst.util.baseproperties.BaseProperties;

// Converts a directory of PAUs to a single JSON
public final class Corpus2Json {
  
  public static void toJsonFile(Corpus<?> c, String f) {
    try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f), "UTF-8"))) {
      bw.write(c.toObjectNode().toString());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
  
  private static BaseProperties PROPERTIES;
  
  public static void main(String[] args) {
    BaseProperties.setInstance(args, "sample/test.properties");
    PROPERTIES = BaseProperties.getInstance();
    
    String input = PROPERTIES.getProperty("input", "sample/");
    BasicCorpus c = (new BasicCorpusBuilder()).setDirectory(input).build();
    
    String output = PROPERTIES.getProperty("output", "sample/output.json");
    toJsonFile(c, output);
  }
  
}
