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
  
  public void setFile(String aFile) { _file = aFile; }
  public void setPauId(String aPauId) { _pauId = aPauId; }
  public void setPauTitle(String aPauTitle) { _pauTitle = aPauTitle; }
  public void setSourceDoc(String aSourceDoc) { _sourceDoc = aSourceDoc; }
  public void setParagraphs(List<String> aParagraphs) { _paragraphs = aParagraphs; }
  
  public Trec build() {
    return new Trec(_file, _pauId, _pauTitle, _sourceDoc, _paragraphs);
  }
  
  private String _file;
  private String _pauId;
  private String _pauTitle;
  private String _sourceDoc;
  private List<String> _paragraphs;
  
}
