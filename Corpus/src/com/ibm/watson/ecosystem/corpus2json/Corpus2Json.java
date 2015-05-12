package com.ibm.watson.ecosystem.corpus2json;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import com.ibm.watson.ecosystem.corpus.BasicCorpus;
import com.ibm.watson.ecosystem.corpus.BasicCorpusBuilder;
import com.ibm.watson.ecosystem.corpus.Corpus;

// Converts a directory of PAUs to a single JSON
public final class Corpus2Json {
  
  public static void toJsonFile(Corpus<?> c, String f) {
    try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f), "UTF-8"))) {
      bw.write(c.toObjectNode().toString());
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
  public static void main(String[] args) {
    
    args = new String[] {"C:/Users/IBM_ADMIN/git/JumpQA/Corpus TF-IDF/TRECs/Health/xml-splitTrecTrim", "health-corpus.json"};
    
    if (args.length < 2) {
      System.out.println("Input directory of PAUs and output file name");
      return;
    }
    
    BasicCorpus c = (new BasicCorpusBuilder()).setDirectory(args[0]).build();
    
    toJsonFile(c, args[1]);
    
  }

}
