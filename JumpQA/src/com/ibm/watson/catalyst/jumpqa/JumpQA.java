package com.ibm.watson.catalyst.jumpqa;

import java.io.File;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.ibm.watson.catalyst.jumpqa.match.ITemplateMatch;
import com.ibm.watson.catalyst.jumpqa.template.ITemplate;
import com.ibm.watson.catalyst.jumpqa.template.TemplateReader;
import com.ibm.watson.catalyst.jumpqa.trec.Trec;
import com.ibm.watson.catalyst.jumpqa.trec.TrecReader;
import com.ibm.watson.catalyst.jumpqa.util.CSVOutputWriter;
import com.ibm.watson.catalyst.util.baseproperties.BaseProperties;

public final class JumpQA {
  
  private static BaseProperties PROPERTIES;
  
  // Get files from the properties object.
  private static File getFile(String key) { return PROPERTIES.getFile(key); }
  private static File getTemplatesFile() { return getFile("templates"); }
  private static File getCorpusFile() { return getFile("corpus"); }
  private static File getOutputFile() { return getFile("output"); }
  
  private static Iterable<ITemplate> readTemplates() {
    File file = getTemplatesFile();
    TemplateReader tr = new TemplateReader();
    return tr.read(file);
  }
  
  private static List<Trec> readTrecs() {
    File file = getCorpusFile();
    TrecReader tr = new TrecReader();
    return tr.read(file);
  }
  
  private static Iterable<ITemplateMatch> genMatches(Iterable<ITemplate> templates, List<Trec> trecs) {
    System.out.println("Generating matches");
    Set<ITemplateMatch> result = Collections.synchronizedSet(new HashSet<ITemplateMatch>());
    templates.forEach((template) -> result.addAll(template.genMatchesFromTrecs(trecs)));
    System.out.println("Generated " + result.size() + " matches");
    return result;
  }
  
  private static void writeMatches(Iterable<ITemplateMatch> matches) {
    System.out.println("Writing matches to " + getOutputFile());
    CSVOutputWriter w = new CSVOutputWriter(getOutputFile());
    w.write(matches);
  }
  
  public static void main(String[] args) {
    if (args.length == 0) args = new String[] { "sample/test.properties" };
    System.out.println("Loading properties from " + args[0]);
    BaseProperties.setInstance(new File(args[0]));
    PROPERTIES = BaseProperties.getInstance();
    
    Iterable<ITemplate> templates = readTemplates();
    List<Trec> trecs = readTrecs();
    Iterable<ITemplateMatch> matches = genMatches(templates, trecs);
    writeMatches(matches);
    System.out.println("Done");
  }
  
}
