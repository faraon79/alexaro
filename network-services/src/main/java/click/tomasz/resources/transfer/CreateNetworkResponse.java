package click.tomasz.resources.transfer;

import click.tomasz.persistence.entity.Network;
import org.codehaus.jackson.annotate.JsonPropertyOrder;

@JsonPropertyOrder({ "id", "name"})
public class CreateNetworkResponse {

	private Network model;

	public CreateNetworkResponse(Network model) {
		this.model = model;
	}

	public int getId() {
		return model.getId();
	}

	public String getName() {
		return model.getName();
	}
}
