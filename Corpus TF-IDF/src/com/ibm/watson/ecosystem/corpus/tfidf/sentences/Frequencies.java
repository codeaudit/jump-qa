package com.ibm.watson.ecosystem.corpus.tfidf.sentences;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.ibm.watson.ecosystem.corpus.tfidf.util.SortedArrayList;

@SuppressWarnings("serial")
public final class Frequencies extends Hashtable<String, Integer> implements Iterable<String> {
  
  public int get(String aKey) {
    String key = normalizeKey(aKey);
    Integer result = super.get(key);
    return (result == null) ? 0 : result;
  }
  
  public Integer put(String aKey, Integer value) {
    String key = normalizeKey(aKey);
    return super.put(key, get(key) + value);
  }
  
  public void putAll(Frequencies f) {
    for (String key : f) {
      this.put(key, f.get(key));
    }
  }
  
  public int getMaxFrequency() {
    int result = 0;
    for (String key : this) result = Math.max(result, get(key));
    return result;
  }
  
  public Iterator<String> iterator() {
    return new FrequencyIterator<String>(this);
  }
  
  public Iterator<String> sortedIterator(Integer min) {
    SortedArrayList<String> result = new SortedArrayList<String>(min);
    Enumeration<String> keys = keys();
    while(keys.hasMoreElements()) {
      String key = keys.nextElement();
      result.sortedAdd(key, get(key));
    }
    return result.iterator();
  }
  
  public Iterator<String> sortedIterator() {
    return sortedIterator(0);
  }
  
  private String normalizeKey(String aKey) {
    return aKey.toLowerCase();
    //return aKey.substring(0, 1).toUpperCase() + aKey.substring(1);
  }
  
  public JsonNode toJsonNode(int min) {
    ObjectNode result = MAPPER.createObjectNode();
    
    Iterator<String> strings = sortedIterator(min);
    
    ArrayNode stringsArrayNode = MAPPER.createArrayNode();
    while(strings.hasNext()) {
      ObjectNode aStringNode = MAPPER.createObjectNode();
      String s = strings.next();
      aStringNode.put("string", s);
      aStringNode.put("frequency", get(s));
      stringsArrayNode.add(aStringNode);
    }
    result.set("strings", stringsArrayNode);
    
    return result;
  }

  private static final ObjectMapper MAPPER = new ObjectMapper();

@Override
public void forEach(Consumer<? super String> action) {
	// TODO Auto-generated method stub
	
}

@Override
public Spliterator<String> spliterator() {
	// TODO Auto-generated method stub
	return null;
}

}
