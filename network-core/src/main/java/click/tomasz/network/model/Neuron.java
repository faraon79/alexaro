package click.tomasz.network.model;

public interface Neuron {

	double run(double[] input, ActivationFunction activationFunction);

	double[] getWeights();

	int getSize();
}
