package com.ibm.watson.catalyst.jumpqa.util;

import com.fasterxml.jackson.databind.JsonNode;

public interface IWritable extends Iterable<String> {
  
  public JsonNode toJsonNode();
  
}
