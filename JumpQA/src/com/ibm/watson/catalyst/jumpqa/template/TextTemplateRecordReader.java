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
package com.ibm.watson.catalyst.jumpqa.template;

import org.apache.commons.csv.CSVRecord;

public class TextTemplateRecordReader implements ITemplateRecordReader<TextTemplate> {

  @Override
  public TextTemplate readRecord(CSVRecord aRecord) {
    String templateId = aRecord.get("Template ID");
    String matchSize = aRecord.get("Match Size");
    String answerSize = aRecord.get("Answer Size");
    String question = aRecord.get("Question");
    String search1 = aRecord.get("Search 1");
    String words1 = aRecord.get("Words 1");
    String words3 = aRecord.get("Words 3");
    String clean = aRecord.get("Clean");
    return new TextTemplate(templateId, answerSize, matchSize, question, search1, words1, words3, clean);
  }
  
}
