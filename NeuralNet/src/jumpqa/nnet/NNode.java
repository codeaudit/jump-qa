package jumpqa.nnet;

public final class NNode {
  
  public NNode(double... aWeights ) {
    _weights = aWeights;
  }
  
  public double compute(double... features) {
    double result = _weights[0];
    
    for (int i = 1; i < _weights.length; i++) {
      result += _weights[i] * features[i - 1];
    }
    
    return logistic(result);
  }
  
  private final double[] _weights;
  
  private static final double logistic(double x) {
    return 1.0 / (1.0 + Math.exp(-x));
  }
  
}
