package com.ibm.watson.catalyst.jumpqa.wordlist;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

@SuppressWarnings("javadoc")
public class StringListReaderTest {
  
  StringBuffer _testString1 = new StringBuffer("a line of text");
  StringBuffer _testString2 = new StringBuffer("another line of text");
  
  StringBuffer _sb;
  List<String> _multiLine;
  
  
  @Before
  public void setUp() {
    _multiLine = new ArrayList<String>();
    _multiLine.add(_testString1.toString());
    _multiLine.add(_testString2.toString());
    
    _sb = new StringBuffer();
    _sb.append(_testString1)
            .append(System.lineSeparator())
            .append(_testString2);
  }
  
  @Test
  public void testReadInputStreamSingleLine() {
    List<String> singleLine = _multiLine.subList(0, 1);
    InputStream is = sb2InputStream(_testString1);
    List<String> stringList = new ArrayList<String>();
    try {
      stringList = (new StringListReader()).read(is);
    } catch (IOException e) {
      fail(e.getMessage());
    }
    assertEquals(singleLine, stringList);
  }

  @Test
  public void testReadInputStreamMultiLine() {
    InputStream is = sb2InputStream(_sb);
    List<String> stringList = null;
    try {
      stringList = (new StringListReader()).read(is);
    } catch (IOException e) {
      fail();
    }
    assertEquals(_multiLine, stringList);
  }
  
  @Test
  public void testCommentAtBeginning() {
    _sb.insert(0, System.lineSeparator()).insert(0, "#comment");
    List<String> stringList = sb2StringList(_sb);
    assertEquals(_multiLine, stringList);
  }
  
  @Test
  public void testCommentInMiddle() {
    int insertPoint = _sb.indexOf(System.lineSeparator());
    _sb.insert(insertPoint, "#comment")
       .insert(insertPoint, System.lineSeparator());
    List<String> stringList = sb2StringList(_sb);
    assertEquals(_multiLine, stringList);
  }
  
  @Test
  public void testCommentAtEnd() {
    _sb.append(System.lineSeparator()).append("#comment");
    List<String> stringList = sb2StringList(_sb);
    assertEquals(_multiLine, stringList);
  }
  
  @Test
  public void testEmptyAtBeginning() {
    _sb.insert(0, System.lineSeparator());
    List<String> stringList = sb2StringList(_sb);
    assertEquals(_multiLine, stringList);
  }
  
  @Test
  public void testEmptyInMiddle() {
    int insertPoint = _sb.indexOf(System.lineSeparator());
    _sb.insert(insertPoint, System.lineSeparator());
    List<String> stringList = sb2StringList(_sb);
    assertEquals(_multiLine, stringList);
  }
  
  @Test
  public void testEmptyAtEnd() {
    _sb.append(System.lineSeparator());
    List<String> stringList = sb2StringList(_sb);
    assertEquals(_multiLine, stringList);
  }
  
  private List<String> sb2StringList(StringBuffer sb) {
    try {
      return (new StringListReader()).read(sb2InputStream(sb));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
  
  private InputStream sb2InputStream(StringBuffer sb) {
    try {
      return new ByteArrayInputStream(sb.toString().getBytes("UTF-8"));
    } catch (UnsupportedEncodingException e) {
      throw new RuntimeException(e);
    }
  }
  
}
