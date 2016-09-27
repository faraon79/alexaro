package click.tomasz.network.learning;

import click.tomasz.network.model.Data;

public class LearnData extends Data {

	private double[] expectedResult;

	public LearnData(double[] data, double[] expectedResult) {
		super(data);
		this.expectedResult = expectedResult;
	}

	public double[] getExpectedResult() {
		return expectedResult;
	}

	public int getExpectedResultSize(){
		return expectedResult.length;
	}
}
