package click.tomasz.network.model;

import java.io.Serializable;

import click.tomasz.network.persistence.IdGenerator;

public class Model implements Serializable {

	private String id;

	public Model(){
		id = IdGenerator.generate();
	}

	public String getId() {
		return id;
	}
}
