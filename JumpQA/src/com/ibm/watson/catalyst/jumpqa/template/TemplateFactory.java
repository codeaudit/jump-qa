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

public class TemplateFactory implements ITemplateRecordReader<ITemplate> {
  
  public TemplateFactory() { }
  
  public ITemplate readRecord(CSVRecord aRecord) {
    ITemplateRecordReader<? extends ITemplate> trr;
    
    String templateClass = aRecord.get("Template Class");
    switch(templateClass) {
      case "TextTemplate":
        trr = new TextTemplateRecordReader();
        break;
      default:
        throw new RuntimeException("Unknown template class: " + templateClass);
    }
    
    return trr.readRecord(aRecord);
  }
  
}
