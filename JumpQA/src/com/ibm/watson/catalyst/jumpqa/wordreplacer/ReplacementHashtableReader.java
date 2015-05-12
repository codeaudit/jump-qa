package com.ibm.watson.catalyst.jumpqa.wordreplacer;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Hashtable;
import java.util.regex.Pattern;

public class ReplacementHashtableReader {
  
  public Hashtable<Pattern, String> read(String aFile) {
    Hashtable<Pattern, String> result = new Hashtable<Pattern, String>();
    if (aFile.equals("")) return result;

    try (BufferedReader br = new BufferedReader(
        new InputStreamReader(
            new FileInputStream("WordLists/" + aFile), ENCODING))) {
      while (br.ready()) {
        String line = br.readLine();
        if (line.equals("") || line.startsWith("#")) {
          continue;
        } else {
          String[] entry = line.split("=", 2);
          Pattern p = Pattern.compile("\\b" + entry[0] + "\\b");
          String r = entry[1];
          result.put(p, r);
        }
      }
    } catch (UnsupportedEncodingException e) {
      throw new RuntimeException("Unsupported encoding: " + ENCODING, e);
    } catch (FileNotFoundException e) {
      throw new RuntimeException("Unable to find " + aFile, e);
    } catch (IOException e) {
      throw new RuntimeException("IOError reading " + aFile, e);
    }
    
    return result;
  }
  
  private static final String ENCODING = "UTF8";
  
}
