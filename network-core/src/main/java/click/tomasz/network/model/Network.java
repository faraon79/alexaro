package click.tomasz.network.model;

import click.tomasz.network.learning.LearnData;

public interface Network {

	double[] run(Data data);

	double[][] train(LearnData learnData);

	Layer[] getLayers();

	ActivationFunction getActivationFunction();
}
