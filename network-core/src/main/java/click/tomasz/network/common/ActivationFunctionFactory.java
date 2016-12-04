package click.tomasz.network.common;

import click.tomasz.network.common.functions.SigmoidalBipolarActivationFunction;
import click.tomasz.network.common.functions.SigmoidalUnipolarActivationFunction;
import click.tomasz.network.model.ActivationFunction;

public class ActivationFunctionFactory {

	public static final double BETA = 1.0;

	public static ActivationFunction getFunction(ActivationFunctionType activationFunctionType) {

		switch (activationFunctionType) {
			case BINARY:
				break;
			case BIPOLAR:
				break;
			case SIGMOIDAL_UNIPOLAR:
				return new SigmoidalUnipolarActivationFunction(BETA);
			case SIGMOIDAL_BIPOLAR:
				return new SigmoidalBipolarActivationFunction(BETA);
			case GAUSS:
				break;
			default:
				break;
		}
		throw new RuntimeException("Activation function type not implemented yet. Available type(s): SIGMOIDAL_UNIPOLAR.");
	}
}
