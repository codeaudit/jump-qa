package com.ibm.watson.catalyst.jumpqa.template;

import org.apache.commons.csv.CSVRecord;

public class TemplateFactory implements ITemplateRecordReader<ITemplate> {
  
  public TemplateFactory(String templateClass) {
    switch(templateClass) {
      case "TextTemplate":
        trr = new TextTemplateRecordReader();
        break;
      default:
        throw new RuntimeException("Unknown template class: " + templateClass);
    }
  }
  
  public ITemplate readRecord(CSVRecord aRecord) {
    return trr.readRecord(aRecord);
  }
  
  private final ITemplateRecordReader<? extends ITemplate> trr;
  
}
