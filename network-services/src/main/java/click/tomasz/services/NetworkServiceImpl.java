package click.tomasz.services;

import click.tomasz.network.model.Data;
import click.tomasz.persistence.common.DaoFactory;
import click.tomasz.persistence.dao.NetworkDao;
import click.tomasz.persistence.entity.Network;
import click.tomasz.resources.transfer.CreateNetwork;
import click.tomasz.resources.transfer.CreateNetworkResponse;
import click.tomasz.resources.transfer.ExecutionResult;
import click.tomasz.resources.transfer.LearnData;
import click.tomasz.resources.transfer.LearnResult;

public class NetworkServiceImpl implements NetworkService {

	private NetworkDao networkDao;

	public NetworkServiceImpl() {
		DaoFactory daoFactory = DaoFactory.getDaoFactory();
		networkDao = (NetworkDao) daoFactory.getDao(NetworkDao.class);
	}

	@Override
	public CreateNetworkResponse createNetwork(CreateNetwork request) {
		Network network = new Network();
		network.setName(request.getName());
		network = networkDao.create(network);
		return new CreateNetworkResponse(network);
	}

	@Override
	public ExecutionResult run(String id, Data data) {
		return null;
	}

	@Override
	public LearnResult learnNetwork(String id, LearnData learnData) {
		return null;
	}

}
