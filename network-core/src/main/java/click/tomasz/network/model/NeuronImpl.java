package click.tomasz.network.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NeuronImpl extends NeuronBase {

	private static final Logger logger = LoggerFactory.getLogger(NeuronImpl.class);

	public NeuronImpl(int inputSize) {
		createWeights(inputSize);
		logger.trace("New neuron created with id: {} and input size: {}", getId(), inputSize);
	}

	public double run(double[] input, ActivationFunction activationFunction) {
		double sum = getSum(input);
		double output = activationFunction.function(sum);
		logger.trace("Neuron {} output is: {}", getId(), output);
		return output;
	}
}
