package click.tomasz.network.model;

public abstract class NetworkBase extends Model implements Network {

	protected Layer[] layers;
	protected ActivationFunction activationFunction;

	protected void createLayers(int[] layersSize) {
		layers = new Layer[layersSize.length];

		for (int layer = 0; layer < layers.length; layer++) {
			int inputSize = layer == 0 ? layersSize[0] : layers[layer - 1].getSize();
			layers[layer] = new Layer(layersSize[layer], inputSize);
		}
	}

	public Layer[] getLayers() {
		return layers;
	}

	public ActivationFunction getActivationFunction() {
		return activationFunction;
	}
}
