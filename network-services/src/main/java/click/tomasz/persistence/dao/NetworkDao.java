package click.tomasz.persistence.dao;

import click.tomasz.persistence.entity.Network;

public class NetworkDao extends Dao {

	public Network create(Network o) {
		return (Network) super.create(o);
	}

	public Network update(Network o) {
		return (Network) super.update(o);
	}

	public void delete(Network o) {
		super.delete(o);
	}

	protected Object findByPk(String id) {
		return super.findByPk(Network.class, id);
	}
}
