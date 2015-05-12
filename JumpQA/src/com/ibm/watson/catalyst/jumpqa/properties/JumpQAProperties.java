package com.ibm.watson.catalyst.jumpqa.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class JumpQAProperties {
  
  private JumpQAProperties(File aFile) {
    _properties = new Properties();
    
    try (FileInputStream is = new FileInputStream(aFile);) {
      _properties.load(is);
    } catch (FileNotFoundException e) {
      throw new RuntimeException("Unable to find properties file: " + aFile.toString(), e);
    } catch (IOException e) {
      throw new RuntimeException("IOError reading properties file: " + aFile.toString(), e);
    }
  }
  
  public String getProperty(String key) { return _properties.getProperty(key); }
  public File getFile(String key) { return new File(getProperty(key)); }
  
  public static boolean setInstance(File aFile) {
    if (instance == null) {
      instance = new JumpQAProperties(aFile);
      return true;
    } else {
      return false;
    }
  }
  
  public static JumpQAProperties getInstance() {
    return instance;
  }
  
  private final Properties _properties;
  
  private static JumpQAProperties instance = null;
  
}
