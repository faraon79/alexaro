package click.tomasz.network.learning.cases;

import click.tomasz.network.learning.LearnSet;

public class Letters extends LearnSet {

	public Letters() {
		addItem(A_DATA, A_RESULT);
		addItem(B_DATA, B_RESULT);
		addItem(C_DATA, C_RESULT);
	}

	private static final double[] A_DATA = new double[]{
			0, 1, 1, 1, 0,
			1, 0, 0, 0, 1,
			1, 0, 0, 0, 1,
			1, 1, 1, 1, 1,
			1, 0, 0, 0, 1,
			1, 0, 0, 0, 1,
			1, 0, 0, 0, 1};
	private static final double[] A_RESULT = new double[]{1, 0, 0};

	private static final double[] B_DATA = new double[]{
			1, 1, 1, 1, 0,
			1, 0, 0, 0, 1,
			1, 0, 0, 0, 1,
			1, 1, 1, 1, 0,
			1, 0, 0, 0, 1,
			1, 0, 0, 0, 1,
			1, 1, 1, 1, 0};
	private static final double[] B_RESULT = new double[]{0, 1, 0};

	private static final double[] C_DATA = new double[]{
			0, 1, 1, 1, 0,
			1, 0, 0, 0, 1,
			1, 0, 0, 0, 0,
			1, 0, 0, 0, 0,
			1, 0, 0, 0, 0,
			1, 0, 0, 0, 1,
			0, 1, 1, 1, 0};
	private static final double[] C_RESULT = new double[]{0, 0, 1};
}

