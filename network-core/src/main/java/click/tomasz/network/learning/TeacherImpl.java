package click.tomasz.network.learning;

import click.tomasz.network.model.ActivationFunction;
import click.tomasz.network.model.Layer;
import click.tomasz.network.model.Network;
import click.tomasz.network.model.TeachingResult;
import click.tomasz.network.util.MathUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TeacherImpl implements Teacher {

	private double acceptableError;
	private double randomWeightMin;
	private double randomWeightMax;
	private double alpha;
	private double eta;
	private int teachLoopLimit;

	private Network network;
	private LearnSet learnSet;
	private Layer[] layers;
	private ActivationFunction activationFunction;

	protected static final Logger logger = LoggerFactory.getLogger(TeacherImpl.class);

	public TeacherImpl(Network network, LearnSet learnSet, TeacherConfigurator configurator) {
		this.network = network;
		this.learnSet = learnSet;
		layers = network.getLayers();
		activationFunction = network.getActivationFunction();

		this.acceptableError = configurator.getAcceptableError();
		this.randomWeightMin = configurator.getRandomWeightMin();
		this.randomWeightMax = configurator.getRandomWeightMax();
		this.alpha = configurator.getAlpha();
		this.eta = configurator.getEta();
		this.teachLoopLimit = configurator.getTeachLoopLimit();
	}

	public TeachingResult teach() {
		logger.info("Training started");
		logger.info("Alpha: {} eta: {}", alpha, eta);
		logger.info("Random weights min: {}, max: {}", randomWeightMin, randomWeightMax);

		TeachingResult result = new TeachingResult(network);

		double epochError = 1.0;
		int epochCounter = 0;
		MinEpochErrorInfo minEpochErrorInfo = new MinEpochErrorInfo();

		generateRandomWeights(randomWeightMin, randomWeightMax);

		double[][][] oldWeights = copyWeights();

		boolean examResult = false;

		while ((epochError > acceptableError || !examResult) && epochCounter < teachLoopLimit) {
			logger.info("Started epoch: {}", epochCounter);

			double[] vectorsError = new double[learnSet.getSize()];

			for (int vector = 0; vector < learnSet.getSize(); vector++) {
				logger.debug("Learn data: {}", vector);
				double[][] output = network.train(learnSet.getLearnDatas().get(vector));
				double[] outputError = getOutputError(learnSet, vector, output[layers.length - 1]);

				for (int i = 0; i < outputError.length; i++) {
					vectorsError[vector] += Math.pow(outputError[i], 2);
				}

				if (isOutputErrorInLimit(outputError)) {
					continue;
				}

				double[][] delta = getNeuronsDelta(output, outputError);
				updateWeights(learnSet.getLearnDatas().get(vector).getData(), output, delta, oldWeights);
			}
			epochError = calculateEpochError(vectorsError, learnSet.getResultSize());
			if (epochError < minEpochErrorInfo.getValue()) {
				minEpochErrorInfo.setMin(epochError, epochCounter);
			}
			if (epochError < acceptableError) {
				examResult = isExamPassed();
			}
			result.addEpochError(epochCounter, epochError);
			logger.info("Epoch error: {}", epochError);
			epochCounter++;
		}

		result.setExamNote(examResult);
		logger.info("Min epoch error: {} in epoch: {}", minEpochErrorInfo.getValue(), minEpochErrorInfo.getEpoch());
		return result;
	}

	private void generateRandomWeights(double min, double max) {
		logger.debug("Generate random weights");
		for (int i = 0; i < layers.length; i++) {
			logger.debug("Layer {}", i);
			for (int j = 0; j < layers[i].getSize(); j++) {
				for (int k = 0; k < layers[i].getNeurons()[j].getSize(); k++) {
					layers[i].getNeurons()[j].getWeights()[k] = MathUtil.getRandomWeight(min, max);
				}
				logger.debug("Neuron {} weight are {}", j, layers[i].getNeurons()[j].getWeights());
			}
		}
	}

	private double[] getOutputError(LearnSet learnSet, int vector, double[] networkOutput) {
		double[] outputError = new double[learnSet.getResultSize()];
		for (int j = 0; j < layers[layers.length - 1].getSize(); j++) {
			outputError[j] = MathUtil.round(
					learnSet.getLearnDatas().get(vector).getExpectedResult()[j] - networkOutput[j]);
		}
		logger.debug("For learn item {} output is {} when {} expected", vector, networkOutput,
				learnSet.getLearnDatas().get(vector).getExpectedResult());
		return outputError;
	}

	private boolean isOutputErrorInLimit(double[] outputError) {
		boolean isInLimit = true;
		for (double outErr : outputError) {
			isInLimit &= (outErr < acceptableError && outErr > -acceptableError);
		}
		return isInLimit;
	}

	private double[][] getNeuronsDelta(double[][] output, double[] outputError) {
		double[][] delta = new double[layers.length][];
		for (int i = 0; i < layers.length; i++) {
			delta[i] = new double[layers[i].getSize()];
		}

		//for output layer
		int layer = layers.length - 1;
		for (int j = 0; j < layers[layer].getSize(); j++) {
			delta[layer][j] = MathUtil.round(activationFunction.derivative(output[layer][j]) * outputError[j]);
			logger.trace("Derivative for layer {} output {} is: {}", layer, j, activationFunction.derivative(output[layer][j]));
		}
		logger.debug("Delta for layer {}: {}", layer, delta[layer]);

		//for hidden layers
		layer--;
		for (int i = layer; i >= 0; i--) {
			for (int j = 0; j < layers[i].getSize(); j++) {
				for (int m = 0; m < layers[i + 1].getSize(); m++) {
					delta[i][j] += delta[i + 1][m] * layers[i + 1].getNeurons()[m].getWeights()[j];
				}
				delta[i][j] = MathUtil.round(delta[i][j] * activationFunction.derivative(output[i][j]));
				logger.trace("Derivative for layer {} output {} is: {}", i, j, activationFunction.derivative(output[i][j]));
			}
			logger.debug("Delta for layer {}: {}", i, delta[i]);
		}

		return delta;
	}

	private void updateWeights(double[] input, double[][] output, double[][] delta, double[][][] oldWeights) {
		double[][][] temp = copyWeights();

		//for hidden and output layer
		for (int i = 1; i < layers.length; i++) {
			for (int j = 0; j < layers[i].getSize(); j++) {
				for (int k = 0; k < layers[i].getWeightSize(); k++) {
					layers[i].getNeurons()[j].getWeights()[k] +=
							2 * eta * delta[i][j] * output[i - 1][k] +
									alpha * (layers[i].getNeurons()[j].getWeights()[k] - oldWeights[i][j][k]);
				}
			}
		}

		//for first layer
		int layer = 0;
		for (int j = 0; j < layers[layer].getSize(); j++) {
			for (int k = 0; k < layers[layer].getWeightSize(); k++) {
				layers[layer].getNeurons()[j].getWeights()[k] +=
						2 * eta * delta[layer][j] * input[j] +
								alpha * (layers[layer].getNeurons()[j].getWeights()[k] - oldWeights[layer][j][k]);
			}
		}

		for (int i = 0; i < temp.length; i++) {
			oldWeights[i] = new double[temp[i].length][];
			for (int j = 0; j < temp[i].length; j++) {
				oldWeights[i][j] = temp[i][j].clone();
			}
		}
	}

	private double[][][] copyWeights() {
		double[][][] weights = new double[layers.length][][];
		for (int i = 0; i < layers.length; i++) {
			weights[i] = new double[layers[i].getSize()][];
			for (int j = 0; j < layers[i].getNeurons().length; j++) {
				weights[i][j] = layers[i].getNeurons()[j].getWeights().clone();
			}
		}
		return weights;
	}

	private double calculateEpochError(double[] vectorsError, int outpurSize) {
		double error = 0.0;
		for (double vectorErr : vectorsError) {
			error += vectorErr;
		}
		error = (0.5 * (error / (vectorsError.length * outpurSize)));
		return MathUtil.round(error);
	}

	private boolean isExamPassed() {
		boolean examResult = true;
		int[] maxValueTest = new int[learnSet.getSize()];
		int[] maxValueLearnSet = new int[learnSet.getSize()];
		int i = 0;
		for (LearnData learnData : learnSet.getLearnDatas()) {
			maxValueTest[i] = getMaxValueIndex(network.run(learnData));
			maxValueLearnSet[i] = getMaxValueIndex(learnData.getExpectedResult());
			examResult &= maxValueTest[i] == maxValueLearnSet[i];
			i++;
		}
		logger.info("Exam answers: {}", maxValueTest);
		logger.info("Expected    : {}", maxValueLearnSet);
		logger.info("Exam: {}", examResult ? "passed" : "failed");
		return examResult;
	}

	private int getMaxValueIndex(double[] values) {
		double max = 0;
		int index = 0;
		for (int i = 0; i < values.length; i++) {
			if (values[i] > max) {
				max = values[i];
				index = i;
			}
		}
		return index;
	}

	private class MinEpochErrorInfo {
		private double value = 100000.0;
		private int epoch = 0;

		public void setMin(double value, int epoch) {
			this.value = value;
			this.epoch = epoch;
		}

		public double getValue() {
			return value;
		}

		public int getEpoch() {
			return epoch;
		}
	}
}
