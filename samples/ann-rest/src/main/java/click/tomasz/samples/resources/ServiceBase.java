package click.tomasz.samples.resources;

import click.tomasz.network.model.Network;

public abstract class ServiceBase {

	protected void updateWeights(Network network, double[][][] weights) {
		for (int i = 0; i < network.getLayers().length; i++) {
			for (int j = 0; j < network.getLayers()[i].getSize(); j++) {
				for (int k = 0; k < network.getLayers()[i].getNeurons()[j].getSize(); k++) {
					network.getLayers()[i].getNeurons()[j].getWeights()[k] = weights[i][j][k];
				}
			}
		}
	}
}
