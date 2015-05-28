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
import java.util.Objects;

import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * A class for holding information about TRECs
 * 
 * @author Will Beason
 * @version 0.1.0
 * @since 0.1.0
 *
 */
public class Trec {
  
  private final String _file;
  private final String _pauId;
  private final String _pauTitle;
  private final String _sourceDoc;
  private final List<String> _paragraphs;
  
  /**
   * Instantiates a Trec object, a representation of a TREC document
   * @param aFile the original file which held the TREC in xml form
   * @param aPauId the PAU ID of the TREC
   * @param aPauTitle the PAU Title of the TREC
   * @param aSourceDoc the document from which the TREC was generated
   * @param paragraphs a list of paragraphs in the TREC
   */
  public Trec(final String aFile, final String aPauId, final String aPauTitle,
      final String aSourceDoc, final List<String> paragraphs) {
    _file = aFile;
    _pauId = aPauId;
    _pauTitle = aPauTitle;
    _sourceDoc = aSourceDoc;
    _paragraphs = paragraphs;
  }
  
  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    Trec other = (Trec) obj;
    if (!Objects.equals(other._file, this._file)) return false;
    if (!Objects.equals(other._pauId, this._pauId)) return false;
    if (!Objects.equals(other._pauTitle, this._pauTitle)) return false;
    if (!Objects.equals(other._sourceDoc, this._sourceDoc)) return false;
    if (!Objects.equals(other._paragraphs, this._paragraphs)) return false;
    return true;
  }
  
  /**
   * Gets the file which held the TREC
   * @return the file
   */
  public String getFile() {
    return _file;
  }
  
  /**
   * Gets the list of paragraphs in the TREC
   * @return the paragraphs
   */
  public List<String> getParagraphs() {
    return _paragraphs;
  }
  
  /**
   * Gets the PAU ID of the TREC
   * @return the PAU ID
   */
  public String getPauId() {
    return _pauId;
  }
  
  /**
   * Gets the PAU Title of the TREC
   * @return the PAU Title
   */
  public String getPauTitle() {
    return _pauTitle;
  }
  
  /**
   * Gets the document from which the TREC was generated
   * @return the document from which the TREC was generated
   */
  public String getSourceDoc() {
    return _sourceDoc;
  }
  
  @Override
  public int hashCode() {
    return (new HashCodeBuilder(SEED, MULTIPLY))
        .append(_file)
        .append(_pauId)
        .append(_pauTitle)
        .append(_sourceDoc)
        .append(_paragraphs)
        .hashCode();
  }
  
  private static final int SEED = 1559724181;
  private static final int MULTIPLY = 400986757;
  
}
