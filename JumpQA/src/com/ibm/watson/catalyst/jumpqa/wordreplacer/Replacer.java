package com.ibm.watson.catalyst.jumpqa.wordreplacer;

import java.util.Objects;
import java.util.regex.Pattern;

import org.apache.commons.lang3.builder.HashCodeBuilder;

/** 
 * A class which takes strings and makes a set replacement.
 * 
 * @author Will Beason
 * @version 0.1.0
 * @since 0.1.0
 *
 */
public class Replacer {
  
  /**
   * Instantiates a new Replacer
   * @param aPattern the pattern to search for
   * @param aReplacement what to replace the pattern with
   */
  public Replacer(Pattern aPattern, String aReplacement) {
    _pattern = aPattern;
    _replacement = aReplacement;
  }
  
  /**
   * Instantiates a new Replacer with the pattern as a string
   * @param aPattern the pattern to search for
   * @param aReplacement what to replace the pattern with
   */
  public Replacer(String aPattern, String aReplacement) {
    this(Pattern.compile(aPattern), aReplacement);
  }
  
  /**
   * Takes a string and 
   * @param aString the string to match and make replacements
   * @return the string with replacements made
   */
  public String replaceAll(String aString) {
    return _pattern.matcher(aString).replaceAll(_replacement);
  }
  
  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    Replacer other = (Replacer) obj;
    if (!Objects.equals(other._replacement, this._replacement)) return false;
    if (!Objects.equals(other._pattern.toString(), this._pattern.toString())) return false;
   return true;
  }
  
  @Override
  public int hashCode() {
    return (new HashCodeBuilder(SEED, MULTIPLY))
        .append(_pattern.toString())
        .append(_replacement)
        .hashCode();
  }
  
  private final Pattern _pattern;
  private final String _replacement;
  
  private static final int SEED = 465630923;
  private static final int MULTIPLY = 730394177;
  
}
