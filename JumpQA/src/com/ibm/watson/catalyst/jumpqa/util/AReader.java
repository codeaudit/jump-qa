package com.ibm.watson.catalyst.jumpqa.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.regex.Pattern;

/**
 * An abstract class for object readers. A class which implements this abstract class only needs to
 *   describe what to do with an input stream.
 * 
 * @author Will Beason
 * @version 0.1.0
 * @param <T> The type of object returned by the reader
 * @since 0.1.0
 *
 */
public abstract class AReader<T> implements IReader {
  
  @Override
  public abstract List<T> read(InputStream is) throws IOException;
  
  @Override
  public List<T> read(File aFile) {
    try {
      return read(new FileInputStream(aFile));
    } catch (final FileNotFoundException e) {
      throw new RuntimeException("Unable to find " + aFile, e);
    } catch (final IOException e) {
      throw new RuntimeException("IOError reading " + aFile, e);
    } catch (final RuntimeException e) {
      throw new RuntimeException("Error reading " + aFile, e);
    }
  }
  
  @Override
  public List<T> readFile(String aFile) {
    return read(new File(aFile));
  }
  
  @Override
  public List<T> read(String aString) {
    try {
      return read(str2InputStream(aString));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
  
  /** 
   * Checks if the line is a comment or empty.
   * @param aLine
   * @return
   */
  protected static boolean isCommentOrEmpty(String aLine) {
    return isComment(aLine) || aLine.equals("");
  }
  
  /** 
   * Checks if a line is a comment.
   * @param aLine
   * @return
   */
  protected static boolean isComment(String aLine) {
    return COMMENT.matcher(aLine).find();
  }
  
  private static InputStream str2InputStream(String aString) {
    try {
      return new ByteArrayInputStream(aString.getBytes("UTF-8"));
    } catch (UnsupportedEncodingException e) {
      throw new RuntimeException(e);
    }
  }
  
  private static final Pattern COMMENT = Pattern.compile("^#");
  
}
