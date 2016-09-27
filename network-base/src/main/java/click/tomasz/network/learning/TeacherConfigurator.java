package click.tomasz.network.learning;

public class TeacherConfigurator {

	private double acceptableError = 0.01;
	private double randomWeightRange = 2.0;
	private double alpha = 0.9;
	private double eta = 0.2;
	private int teachLoopLimit = 1000;

	public double getAcceptableError() {
		return acceptableError;
	}

	public void setAcceptableError(double acceptableError) {
		this.acceptableError = acceptableError;
	}

	public double getRandomWeightMin() {
		return -randomWeightRange;
	}

	public double getRandomWeightMax() {
		return randomWeightRange;
	}

	public void setRandomWeightRange(double randomWeightRange) {
		this.randomWeightRange = randomWeightRange;
	}

	public double getAlpha() {
		return alpha;
	}

	public void setAlpha(double alpha) {
		this.alpha = alpha;
	}

	public double getEta() {
		return eta;
	}

	public void setEta(double eta) {
		this.eta = eta;
	}

	public int getTeachLoopLimit() {
		return teachLoopLimit;
	}

	public void setTeachLoopLimit(int teachLoopLimit) {
		this.teachLoopLimit = teachLoopLimit;
	}
}
