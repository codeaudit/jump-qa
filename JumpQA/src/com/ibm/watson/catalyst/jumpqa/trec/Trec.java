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
package com.ibm.watson.catalyst.jumpqa.trec;

import java.util.List;

public class Trec {
  
  public Trec(String aFile, String aPauId,
      String aPauTitle, String aSourceDoc, List<String> paragraphs) {
    _file = aFile;
    _pauId = aPauId;
    _pauTitle = aPauTitle;
    _sourceDoc = aSourceDoc;
    _paragraphs = paragraphs;
  }
  
  public String getFile() {
    return _file;
  }
  public String getPauId() {
    return _pauId;
  }
  public String getPauTitle() {
    return _pauTitle;
  }
  public String getSourceDoc() {
    return _sourceDoc;
  }
  public List<String> getParagraphs() {
    return _paragraphs;
  }

  private final String _file;
  private final String _pauId;
  private final String _pauTitle;
  private final String _sourceDoc;
  private final List<String> _paragraphs;
  
}
