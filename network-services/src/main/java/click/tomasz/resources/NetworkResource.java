package click.tomasz.resources;

import static click.tomasz.validation.ValidationUtil.validateDataBasic;
import static click.tomasz.validation.ValidationUtil.validateLearnDataBasic;
import static click.tomasz.validation.ValidationUtil.validateNetworkName;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.Properties;

import click.tomasz.network.model.Data;
import click.tomasz.persistence.entity.Network;
import click.tomasz.resources.transfer.CreateNetwork;
import click.tomasz.resources.transfer.CreateNetworkResponse;
import click.tomasz.resources.transfer.ExecutionResult;
import click.tomasz.resources.transfer.LearnData;
import click.tomasz.resources.transfer.LearnResult;
import click.tomasz.services.NetworkService;

@Path("/network")
public class NetworkResource {

	private NetworkService networkService;

	@POST
	@Produces({"application/json"})
	@Consumes({"application/json"})
	public CreateNetworkResponse create(CreateNetwork request) {
		validateNetworkName(request.getName());
		return networkService.createNetwork(request);
	}

	@POST
	@Path("/learn/{id}")
	public LearnResult learn(@PathParam("id") String id, LearnData learnData) {
		validateLearnDataBasic(learnData);
		return new LearnResult(networkService.learnNetwork(id, learnData));
	}

	@POST
	@Produces({"application/json"})
	@Consumes({"application/json"})
	@Path("/{id}")
	public ExecutionResult execute(@PathParam("id") String networkId, Data data) {
		validateDataBasic(data);
		return networkService.run(networkId, data);
	}

	@GET
	@Path("/status")
	@Produces({"application/json"})
	public Properties healthCheck() {
		return System.getProperties();
	}

	public void setNetworkService(NetworkService networkService) {
		this.networkService = networkService;
	}
}

