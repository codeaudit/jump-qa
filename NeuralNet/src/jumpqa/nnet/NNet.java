package jumpqa.nnet;

import java.util.List;

public final class NNet {
  
  public NNet (NLayer... aLayers) {
    _layers = aLayers;
  }
  
  public NNet (List<NLayer> aLayers) {
    this(aLayers.toArray(new NLayer[aLayers.size()]));
  }
  
  public double[] compute(double... features) {
    double[] result = features;
    
    for (NLayer aLayer : _layers) {
      result = aLayer.compute(result);
    }
    
    return result;
  }
  
  public double[] compute(int layerNum, double... features) {
    return _layers[layerNum].compute(features);
  }
  
  public double compute(int layerNum, int nodeNum, double... features) {
    return _layers[layerNum].compute(nodeNum, features);
  }
  
  private final NLayer[] _layers;
  
}
