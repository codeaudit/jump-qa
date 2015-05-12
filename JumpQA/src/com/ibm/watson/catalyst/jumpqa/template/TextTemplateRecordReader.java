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
