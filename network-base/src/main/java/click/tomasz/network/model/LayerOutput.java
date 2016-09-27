package click.tomasz.network.model;

public class LayerOutput {

	private double[] output;

	public LayerOutput(int size) {
		output = new double[size];
	}

	public void setOutput(int position, double value){
		output[position] = value;
	}

	public double[] getOutput() {
		return output;
	}
}
