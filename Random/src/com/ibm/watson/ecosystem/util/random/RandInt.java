package com.ibm.watson.ecosystem.util.random;

import java.util.Random;

// An extension of the Random class which makes generating integers easy.
public class RandInt extends Random {
  
  // ------------------------------------------------------------------------------------------ //
  // Public
  // ------------------------------------------------------------------------------------------ //
  
  //Not inclusive of max value.
  public int randInt(int min, int max) {
    return this.nextInt((max - min)) + min;
  }
  
  public int randInt(int max) {
    return randInt(0, max);
  }
  
  // ------------------------------------------------------------------------------------------ //
  // Private
  // ------------------------------------------------------------------------------------------ //
  private static final long serialVersionUID = 2755108680686263216L;
  
}
