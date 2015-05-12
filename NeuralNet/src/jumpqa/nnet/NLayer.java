package jumpqa.nnet;

import java.util.List;

public final class NLayer {
  
  public NLayer(NNode... aNodes) {
    _nodes = aNodes;
  }
  
  public NLayer(List<NNode> aNodes) {
    this(aNodes.toArray(new NNode[aNodes.size()]));
  }
  
  public double[] compute(double... features) {
    double[] result = new double[_nodes.length];
    for (int i = 0; i < _nodes.length; i++) {
      result[i] = _nodes[i].compute(features);
    }
    return result;
  }
  
  public double compute(int nodeNum, double... features) {
    return _nodes[nodeNum].compute(features);
  }
  
  private final NNode[] _nodes;
  
}
