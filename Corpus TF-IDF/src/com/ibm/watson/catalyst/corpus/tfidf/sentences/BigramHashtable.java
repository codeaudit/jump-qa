package com.ibm.watson.catalyst.corpus.tfidf.sentences;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.ibm.watson.catalyst.corpus.tfidf.util.SortedArrayList;

@SuppressWarnings("serial")
public class BigramHashtable extends FrequencyHashtable<Bigram> {
  
  public Integer put(Bigram b, int aInt) {
    _frequencies.addAll(b.getWords());
    return super.put(b, aInt);
  }
  
  public Integer put(String aWord1, String aWord2, int aInt) {
    return(put(bigram(aWord1, aWord2), aInt));
  }
  
  public Integer get(String aWord1, String aWord2) {
    return get(bigram(aWord1, aWord2));
  }
  
  public Integer add(String aWord1, String aWord2) {
    return add(bigram(aWord1, aWord2));
  }
  
  // Bigram-frequency inverse-proximity-frequency
  public double bfipf(Bigram b) {
    double result = 0;
    result = ((double) get(b)) / frequency(b.getWords());
    return result;
  }
  
  public JsonNode toJson() {
    ObjectNode result = MAPPER.createObjectNode();
    ArrayNode bigramsArray = MAPPER.createArrayNode();
    
    SortedArrayList<Bigram> bigrams = new SortedArrayList<Bigram>();
    for (Bigram b : this) {
      bigrams.sortedAdd(b, bfipf(b));
    }
    
    for (Bigram b : bigrams) {
      ObjectNode bigramNode = b.toJson();
      bigramNode.put("frequency", get(b));
      bigramNode.put("bfipf", bfipf(b));
      bigramsArray.add(bigramNode);
    }
    result.set("bigrams", bigramsArray);
    
    return result;
  }
  
  // ------------------------------------------------------------------------------------------ //
  // Private
  // ------------------------------------------------------------------------------------------ //
  private Bigram bigram(String aWord1, String aWord2) {
    return new Bigram(aWord1, aWord2);
  }
  
  private final int frequency(List<String> aList) {
    return frequency(aList.get(0)) * frequency(aList.get(1));
  }
  
  private final int frequency(String aWord) {
    return _frequencies.get(aWord);
  }
  
  private final FrequencyHashtable<String> _frequencies = new FrequencyHashtable<String>();
  private static final ObjectMapper MAPPER = new ObjectMapper();
  
}
