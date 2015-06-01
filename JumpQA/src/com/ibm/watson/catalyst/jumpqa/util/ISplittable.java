/**
 * 
 */
package com.ibm.watson.catalyst.jumpqa.util;

import java.util.List;

import com.ibm.watson.catalyst.jumpqa.splitter.SplitterFactory.Size;

/** TODO: Class description
 * 
 * @author Will Beason
 * @version 0.1.1
 * @param <T> What the object is split into
 * @since 0.1.1
 *
 */
public interface ISplittable<T> {
  
  /** 
   * TODO: Method description
   * @param aSize the size to split the object into
   * @return the resulting splits
   */
  public List<T> splitInto(Size aSize);
  
}
