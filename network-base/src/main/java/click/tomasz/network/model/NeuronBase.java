package click.tomasz.network.model;

public abstract class NeuronBase extends Model implements Neuron {

	protected double[] weights;

	protected void createWeights(int inputSize) {
		weights = new double[inputSize];
	}

	public double[] getWeights() {
		return weights;
	}

	public int getSize() {
		return weights.length;
	}

	protected double getSum(double[] input) {
		double sum = 0.0;
		for (int i = 0; i < weights.length; i++) {
			sum += input[i] * weights[i];
		}
		return sum;
	}
}
