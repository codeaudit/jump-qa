package com.ibm.watson.catalyst.corpus.tfidf.conjugate;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.ibm.watson.catalyst.corpus.tfidf.conjugate.Verb.Regular;

public class Conjugator {
  
  public static void main(String[] args) {
    
    try (BufferedReader br = new BufferedReader(new FileReader("verbs.words"))) {
      
      BufferedWriter bw = new BufferedWriter(new FileWriter("regularverbs.words"));
      int i = 0;
      while (br.ready() && i++ < 1000) {
        Verb verb = new Verb(br.readLine(), Regular.YES);
        bw.write(verb.getConjugations() + "\n");
      }
      
      bw.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    
  }
  
}
