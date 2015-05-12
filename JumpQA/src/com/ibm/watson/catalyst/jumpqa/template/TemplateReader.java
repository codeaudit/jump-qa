package com.ibm.watson.catalyst.jumpqa.template;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import com.ibm.watson.catalyst.jumpqa.util.IReader;

public class TemplateReader implements IReader<ITemplate> {
  
  @Override
  public List<ITemplate> read(File aFile) {
    System.out.println("Reading templates from " + aFile);
    List<ITemplate> result = new ArrayList<ITemplate>();
    
    CSVParser parser;
    try {
      parser = CSVParser.parse(aFile, UTF_8, FORMAT);
    } catch (IOException e) {
      throw new RuntimeException("Could not read templates file " + aFile, e);
    }
    parser.forEach((record) -> result.add(readRecord(record)));
    
    System.out.println("Read " + result.size() + " templates");
    return result;
  }
  
  public ITemplate readRecord(CSVRecord aRecord) {
    String templateClass = aRecord.get("Template Class");
    TemplateFactory tf = new TemplateFactory(templateClass);
    return tf.readRecord(aRecord);
  }
  
  private static final CSVFormat FORMAT = CSVFormat.RFC4180.withHeader().withDelimiter('\t');
  private static final Charset UTF_8 = StandardCharsets.UTF_8;
  
}
