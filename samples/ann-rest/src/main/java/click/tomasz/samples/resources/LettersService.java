package click.tomasz.samples.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import java.util.Properties;

import click.tomasz.network.model.Data;
import click.tomasz.network.model.Network;

@Path("/network")
public class LettersService extends ServiceBase {

	private Network network;

	@POST
	@Produces({"application/json"})
	@Consumes({"application/json"})
	public Data recognizeLetter(Data data) {
		return new Data(network.run(data));
	}

	@GET
	@Path("/status")
	@Produces({"application/json"})
	public Properties healthCheck() {
		return System.getProperties();
	}

	public void setNetwork(Network network) {
		this.network = network;
		updateWeights(network, Constants.LETTER_WEIGHTS);
	}
}
