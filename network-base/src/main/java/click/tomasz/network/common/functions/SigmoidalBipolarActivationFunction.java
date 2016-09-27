package click.tomasz.network.common.functions;

import click.tomasz.network.model.ActivationFunction;
import click.tomasz.network.util.MathUtil;

public class SigmoidalBipolarActivationFunction implements ActivationFunction {

	private double beta;

	public SigmoidalBipolarActivationFunction(double beta) {
		this.beta = beta;
	}

	public double function(double sum) {
		return MathUtil.round(Math.tanh(beta * sum));
	}

	public double derivative(double output) {
		return MathUtil.round(beta * (1.0 - Math.pow(output, 2)));
	}
}
