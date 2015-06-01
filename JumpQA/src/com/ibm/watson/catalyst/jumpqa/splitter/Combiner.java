/**
 * 
 */
package com.ibm.watson.catalyst.jumpqa.splitter;

import java.util.ArrayList;
import java.util.List;

/** TODO: Class description
 * 
 * @author Will Beason
 * @version 0.1.1
 * @since 0.1.1
 *
 */
public class Combiner implements ISplitter {
  
  @Override
  public List<String> split(List<String> strings) {
    StringBuilder sb = new StringBuilder();
    strings.stream().forEachOrdered((s) -> sb.append(s).append(System.lineSeparator()));
    List<String> result = new ArrayList<String>();
    result.add(sb.toString());
    return result;
  }
  
  @Override
  public List<String> split(String aString) {
    List<String> result = new ArrayList<String>();
    result.add(aString);
    return result;
  }
  
}
