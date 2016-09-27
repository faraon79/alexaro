package click.tomasz.network.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Layer extends LayerBase {

	private static final Logger logger = LoggerFactory.getLogger(Layer.class);

	public Layer(int size, int inputSize) {
		this.size = size;
		neurons = new Neuron[size];
		logger.debug(String.format("New layer created with id: %s and size: %s", getId(), size));

		for (int i = 0; i < neurons.length; i++) {
			neurons[i] = new NeuronImpl(inputSize);
		}
	}

	public double[] run(double[] data, ActivationFunction activationFunction){
		LayerOutput output = new LayerOutput(size);
		for (int i = 0; i < neurons.length; i++) {
			output.setOutput(i, neurons[i].run(data, activationFunction));
		}
		return output.getOutput();
	}
}
