package click.tomasz.resources.transfer;

public class CreateNetwork implements TransferModel {

	private String name;

	public CreateNetwork(){}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
