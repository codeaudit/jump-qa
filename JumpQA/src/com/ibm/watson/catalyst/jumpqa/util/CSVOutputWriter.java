package com.ibm.watson.catalyst.jumpqa.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

public class CSVOutputWriter implements IOutputWriter {
  
  public CSVOutputWriter(File aOutFile, String aEncoding) {
    _outFile = aOutFile;
    _encoding = aEncoding;
  }
  
  public CSVOutputWriter(File aOutFile) {
    this(aOutFile, "UTF-8");
  }
  
  public void write(Iterable<? extends IWritable> writables) {
    try (OutputStreamWriter writer = new OutputStreamWriter(
        new FileOutputStream(_outFile), _encoding)) {
      try (CSVPrinter printer = new CSVPrinter(writer, FORMAT);) {
        
        printer.printRecord(header);
        printer.printRecords(writables);
      } catch (IOException e) {
        throw new RuntimeException("IOError writing to " + _outFile, e);
      }
    } catch (IOException e) {
      throw new RuntimeException("IOError opening " + _outFile + " for writing.");
    }
    
  }
  
  private final File _outFile;
  private final String _encoding;
  
  private static final CSVFormat FORMAT = CSVFormat.RFC4180.withDelimiter('\t');
  private static final List<String> header = new ArrayList<String>();
  
  static {
    header.add("Question ID");
    header.add("Question Text");
    header.add("Answer Text");
    header.add("PAU Title");
    header.add("PAU ID");
    header.add("Cluster ID");
    header.add("State");
  }
  
}
