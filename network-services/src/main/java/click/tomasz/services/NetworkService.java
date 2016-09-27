package click.tomasz.services;

import click.tomasz.network.model.Data;
import click.tomasz.resources.transfer.CreateNetwork;
import click.tomasz.resources.transfer.CreateNetworkResponse;
import click.tomasz.resources.transfer.ExecutionResult;
import click.tomasz.resources.transfer.LearnData;
import click.tomasz.resources.transfer.LearnResult;

public interface NetworkService {

	CreateNetworkResponse createNetwork(CreateNetwork request);

	ExecutionResult run(String id, Data data);

	LearnResult learnNetwork(String id, LearnData learnData);
}
