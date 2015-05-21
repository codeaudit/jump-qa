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
