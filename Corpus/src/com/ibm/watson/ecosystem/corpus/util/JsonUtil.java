package com.ibm.watson.ecosystem.corpus.util;

import java.util.Collection;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

public class JsonUtil {
  private static final ObjectMapper MAPPER = new ObjectMapper();
  
  public static ArrayNode toArrayNodeString(Collection<String> aCollection) {
    ArrayNode result = MAPPER.createArrayNode();
    aCollection.forEach((aString) -> result.add(aString));
    return result;
  }
  
  public static ArrayNode toArrayNodeJsonable(Collection<? extends Jsonable> aCollection) {
    ArrayNode result = MAPPER.createArrayNode();
    aCollection.forEach((aJsonable) -> result.add(aJsonable.toJson()));
    return result;
  }
  
}
