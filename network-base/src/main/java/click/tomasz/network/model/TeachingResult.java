package click.tomasz.network.model;

import java.util.LinkedHashMap;
import java.util.Map;

public class TeachingResult {

	private Network network;
	private Map<Integer, Double> epochErrors;
	private boolean examNote;

	public TeachingResult(Network network) {
		this.network = network;
		epochErrors = new LinkedHashMap<>();
		examNote = false;
	}

	public Network getNetwork() {
		return network;
	}

	public Map<Integer, Double> getEpochErrors() {
		return epochErrors;
	}

	public void addEpochError(int epoch, double epochError) {
		epochErrors.put(epoch, epochError);
	}

	public void setExamNote(boolean examNote) {
		this.examNote = examNote;
	}

	public boolean getExamNote() {
		return examNote;
	}
}
