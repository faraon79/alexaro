package click.tomasz.network.model;

public abstract class LayerBase extends Model {

	protected Neuron[] neurons;
	protected int size;

	public int getSize() {
		return size;
	}

	public Neuron[] getNeurons() {
		return neurons;
	}

	public int getWeightSize(){
		return neurons[0].getWeights().length;
	}
}
