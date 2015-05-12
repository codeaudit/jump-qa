package com.ibm.watson.catalyst.jumpqa.wordreplacer;

import java.util.Hashtable;
import java.util.Map.Entry;
import java.util.regex.Pattern;

public class WordReplacer implements IWordReplacer {
  
  public WordReplacer(Hashtable<Pattern, String> replacements) {
    _replacements = replacements;
  }
  
  public WordReplacer(String aFile) {
    this((new ReplacementHashtableReader()).read(aFile));
  }
  
  @Override
  public String replace(String aString) {
    String result = aString;
    for (Entry<Pattern, String> replacement : _replacements.entrySet()) {
      Pattern p = replacement.getKey();
      String r = replacement.getValue();
      result = p.matcher(result).replaceAll(r);
    }
    return result;
  }
  
  private final Hashtable<Pattern, String> _replacements;
  
}
