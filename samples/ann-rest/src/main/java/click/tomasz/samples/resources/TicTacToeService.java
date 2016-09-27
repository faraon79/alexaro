package click.tomasz.samples.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import click.tomasz.network.model.Data;
import click.tomasz.network.model.Network;

@Path("/tictactoe")
public class TicTacToeService extends ServiceBase {

	private Network network;

	@POST
	@Consumes({"application/json"})
	@Produces({"application/json"})
	public Data nextMove(Data data) {
		return new Data(network.run(data));
	}

	public void setNetwork(Network network) {
		this.network = network;
		updateWeights(network, Constants.TIC_TAC_TOE_WEIGHTS);
	}
}
