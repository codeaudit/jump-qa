/**
 * 
 */
package com.ibm.watson.catalyst.jumpqa.splitter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

import org.apache.commons.lang3.builder.HashCodeBuilder;

/** TODO: Class description
 * 
 * @author Will Beason
 * @version 0.1.0
 * @since 0.1.0
 *
 */
public class Splitter implements ISplitter {
  
  private final Pattern _p;
  
  
  /**
   * @param aRegex the regular expression to split around
   */
  public Splitter(String aRegex) {
    _p = Pattern.compile(aRegex);
  }
  
  /**
   * @param p the pattern to split with
   */
  public Splitter(Pattern p) {
    _p = p;
  }
  
  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    Splitter other = (Splitter) obj;
    if (!Objects.equals(other._p.toString(), this._p.toString())) return false;
    if (!Objects.equals(other._p.flags(), this._p.flags())) return false;
    return true;
  }
  
  @Override
  public int hashCode() {
    return (new HashCodeBuilder(SEED, MULTIPLY))
        .append(_p.toString())
        .append(_p.flags())
        .hashCode();
  }
  
  private static final int SEED = 860127911;
  private static final int MULTIPLY = 505854299;
  
  @Override
  public final List<String> split(final List<String> strings) {
    final List<String> result = new ArrayList<String>();
    strings.forEach((string) -> {
      result.addAll(split(string));
    });
    return result;
  }
  
  /** 
   * Removes empty strings from a list of strings.
   * @param strings
   * @return
   */
  protected List<String> removeEmpty(List<String> strings) {
    final List<String> result = new ArrayList<String>(strings);
    result.removeIf((s) -> s.equals(""));
    return result;
  }
  
  /** 
   * Removes empty strings from an array of strings.
   * @param strings
   * @return
   */
  protected List<String> removeEmpty(String[] strings) {
    return removeEmpty(Arrays.asList(strings));
  }
  
  @Override
  public List<String> split(final String aString) {
    return removeEmpty(_p.split(aString));
  }
  
}
