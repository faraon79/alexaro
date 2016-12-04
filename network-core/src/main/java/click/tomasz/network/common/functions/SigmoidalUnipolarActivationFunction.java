package click.tomasz.network.common.functions;

import click.tomasz.network.model.ActivationFunction;
import click.tomasz.network.util.MathUtil;

public class SigmoidalUnipolarActivationFunction implements ActivationFunction {

	private double beta;

	public SigmoidalUnipolarActivationFunction(double beta) {
		this.beta = beta;
	}

	public double function(double sum) {
		return MathUtil.round(1.0 / (1.0 + Math.exp(-beta * sum)));
	}

	public double derivative(double output) {
		return MathUtil.round(beta * (1.0 - output) * output);
	}
}
