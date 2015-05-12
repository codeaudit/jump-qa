package com.ibm.watson.ecosystem.corpus.util;

import com.fasterxml.jackson.databind.JsonNode;

public interface Jsonable {
  
  public JsonNode toJson();
  
}
