package com.ibm.watson.catalyst.corpus.util;

import com.fasterxml.jackson.databind.JsonNode;

public interface Jsonable {
  
  public JsonNode toJson();
  
}
