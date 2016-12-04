package click.tomasz.network.model;

import click.tomasz.network.learning.LearnData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NetworkImpl extends NetworkBase {

	private static final Logger logger = LoggerFactory.getLogger(NetworkImpl.class);

	public NetworkImpl(NetworkParameters networkParameters) {
		this.activationFunction = networkParameters.getActivationFunction();
		int[] layersSize = networkParameters.getLayersSize();
		createLayers(layersSize);
		logger.info("New network created with size: {}", layersSize);
	}

	public double[] run(Data data) {
		double[] currentData = data.getData().clone();
		for (Layer layer : layers) {
			currentData = layer.run(currentData, activationFunction);
		}
		logger.info("Network answer: {}", currentData);
		return currentData;
	}

	public double[][] train(LearnData learnData) {
		double[] currentData = learnData.getData().clone();
		double[][] output = new double[layers.length][];
		for (int i = 0; i < layers.length; i++) {
			output[i] = layers[i].run(currentData, activationFunction);
			currentData = output[i].clone();
			logger.debug("Layer {} output is: {}", i, output[i]);
		}
		return output;
	}
}
