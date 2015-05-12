package com.ibm.watson.catalyst.jumpqa.wordlist;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import com.ibm.watson.catalyst.jumpqa.util.IReader;

public class StringListReader implements IReader<String> {
  
  @Override
  public List<String> read(File aFile) {
    List<String> result = new ArrayList<String>();
    try (BufferedReader br = new BufferedReader(
        new InputStreamReader(
            new FileInputStream(aFile), ENCODING))) {
      while (br.ready()) {
        String line = br.readLine();
        if (line.equals("") || line.startsWith("#")) {
          continue;
        } else {
          result.add(line);
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
  
  public List<String> read(String aString) {
    return read(new File(aString));
  }
  
  private static final String ENCODING = "UTF8";
  
}
