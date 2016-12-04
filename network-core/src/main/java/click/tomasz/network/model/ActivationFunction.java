package click.tomasz.network.model;

import java.io.Serializable;

public interface ActivationFunction extends Serializable {

	double function(double sum);

	double derivative(double output);
}
