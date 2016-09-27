package click.tomasz.network.model;

import java.util.ArrayList;
import java.util.List;

public class NetworkParameters {
	private int inputSize;
	private int resultSize;
	private List<Integer> innerLayersSize;
	private ActivationFunction activationFunction;

	public NetworkParameters() {
		this.innerLayersSize = new ArrayList<>();
	}

	public void setInputSize(int inputSize) {
		this.inputSize = inputSize;
	}

	public void setResultSize(int resultSize) {
		this.resultSize = resultSize;
	}

	public List<Integer> getInnerLayersSize() {
		return innerLayersSize;
	}

	public void setActivationFunction(ActivationFunction activationFunction) {
		this.activationFunction = activationFunction;
	}

	public void setInnerLayersSize(List<Integer> innerLayersSize) {
		this.innerLayersSize = innerLayersSize;
	}

	public ActivationFunction getActivationFunction() {
		return activationFunction;
	}

	public int[] getLayersSize() {
		int[] layersSize = new int[2 + innerLayersSize.size()];
		layersSize[0]=inputSize;
		for (int i = 0; i < innerLayersSize.size(); i++) {
			layersSize[i+1]=innerLayersSize.get(i);
		}
		layersSize[innerLayersSize.size()+1] = resultSize;
		return layersSize;
	}
}
