package com.ibm.watson.catalyst.jumpqa.template;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

import com.ibm.watson.catalyst.jumpqa.util.IReader;

public class TemplateReader implements IReader<ITemplate> {

  private final static Logger logger = Logger.getLogger(TemplateReader.class.getName());
  
  @Override
  public Collection<ITemplate> read(File aFile) {
    logger.config("Reading templates from " + aFile);
    Collection<ITemplate> result = new ArrayList<ITemplate>();
    
    try (CSVParser parser = CSVParser.parse(aFile, UTF_8, FORMAT)) {
      TemplateFactory tf = new TemplateFactory();
      parser.forEach((record) -> result.add(tf.readRecord(record)));
    } catch (IOException e) {
      throw new RuntimeException("Could not read templates file " + aFile, e);
    }
    
    logger.info("Read " + result.size() + " templates");
    return result;
  }
  
  private static final CSVFormat FORMAT = CSVFormat.RFC4180.withHeader().withDelimiter('\t');
  private static final Charset UTF_8 = StandardCharsets.UTF_8;
  
}
