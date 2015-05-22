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

public class TrecBuilder {
  
  private String _file;
  private List<String> _paragraphs;
  private String _pauId;
  private String _pauTitle;
  private String _sourceDoc;
  
  public Trec build() {
    return new Trec(_file, _pauId, _pauTitle, _sourceDoc, _paragraphs);
  }
  
  public void setFile(final String aFile) {
    _file = aFile;
  }
  
  public void setParagraphs(final List<String> aParagraphs) {
    _paragraphs = aParagraphs;
  }
  
  public void setPauId(final String aPauId) {
    _pauId = aPauId;
  }
  
  public void setPauTitle(final String aPauTitle) {
    _pauTitle = aPauTitle;
  }
  
  public void setSourceDoc(final String aSourceDoc) {
    _sourceDoc = aSourceDoc;
  }
  
}
