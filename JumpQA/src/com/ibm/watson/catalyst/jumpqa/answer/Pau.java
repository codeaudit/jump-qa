/*******************************************************************************
 * Copyright 2015 IBM Corporation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
/**
 * 
 */
package com.ibm.watson.catalyst.jumpqa.answer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.ibm.watson.catalyst.jumpqa.trec.Trec;
import com.ibm.watson.catalyst.jumpqa.util.IPrintable;
import com.ibm.watson.catalyst.jumpqa.util.IWritable;

/**
 * TODO: Class description
 * 
 * @author Will Beason
 * @version 0.1.1
 * @since 0.1.1
 *
 */
public class Pau implements IWritable, IPrintable {
  
  /**
   * 
   */
  public Pau() {
    this("", "");
  }
  
  /**
   * @param aPauTitle the title of the PAU
   * @param aPauId the ID of the PAU
   */
  public Pau(String aPauTitle, String aPauId) {
    _pauTitle = aPauTitle;
    _pauId = aPauId;
  }
  
  /**
   * @param aTrec the trec
   */
  public Pau(Trec aTrec) {
    _pauTitle = aTrec.getPauTitle();
    _pauId = aTrec.getPauId();
  }
  
  @Override
  public List<String> toList() {
    List<String> result = new ArrayList<String>();
    result.add(_pauTitle);
    result.add(_pauId);
    return result;
  }
  
  @Override
  public Iterator<String> iterator() {
    return this.toList().iterator();
  }
  
  @Override
  public StringBuilder toStringBuilder() {
    StringBuilder sb = new StringBuilder();
    sb.append(_pauTitle).append(",").append(_pauId);
    return sb;
  }
  
  @Override
  public String toString() {
    return toStringBuilder().toString();
  }
  
  private final String _pauTitle;
  private final String _pauId;
  
}
