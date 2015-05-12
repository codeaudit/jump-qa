package com.ibm.watson.catalyst.jumpqa.template;

import org.apache.commons.csv.CSVRecord;

public interface ITemplateRecordReader<T extends ITemplate> {
  
  public T readRecord(CSVRecord aRecord);
  
}
