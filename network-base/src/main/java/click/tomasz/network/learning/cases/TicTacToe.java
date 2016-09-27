package click.tomasz.network.learning.cases;

import click.tomasz.network.learning.LearnSet;

public class TicTacToe extends LearnSet {

	public TicTacToe() {
		addItem(DATA_00, RESULT_00);
		addItem(DATA_01, RESULT_01);
		addItem(DATA_0200, RESULT_0200);
		addItem(DATA_0201, RESULT_0201);
		addItem(DATA_0202, RESULT_0202);
		addItem(DATA_0203, RESULT_0203);
		addItem(DATA_0204, RESULT_0204);
		addItem(DATA_03, RESULT_03);
	}

	private static final double[] DATA_00 = new double[]{
			2, 0, 0,
			0, 0, 0,
			0, 0, 0};
	private static final double[] RESULT_00 = new double[]{
			0, 0, 0,
			0, 1, 0,
			0, 0, 0};

	private static final double[] DATA_01 = new double[]{
			2, 0, 0,
			2, 1, 0,
			0, 0, 0};
	private static final double[] RESULT_01 = new double[]{
			0, 0, 0,
			0, 0, 0,
			1, 0, 0};

	private static final double[] DATA_0200 = new double[]{
			2, 2, 0,
			2, 1, 0,
			1, 0, 0};
	private static final double[] RESULT_0200 = new double[]{
			0, 0, 1,
			0, 0, 0,
			0, 0, 0};

	private static final double[] DATA_0201 = new double[]{
			2, 0, 2,
			2, 1, 0,
			1, 0, 0};
	private static final double[] RESULT_0201 = new double[]{
			0, 1, 0,
			0, 0, 0,
			0, 0, 0};

	private static final double[] DATA_0202 = new double[]{
			2, 0, 0,
			2, 1, 2,
			1, 0, 0};
	private static final double[] RESULT_0202 = new double[]{
			0, 0, 1,
			0, 0, 0,
			0, 0, 0};

	private static final double[] DATA_0203 = new double[]{
			2, 0, 0,
			2, 1, 0,
			1, 0, 2};
	private static final double[] RESULT_0203 = new double[]{
			0, 0, 1,
			0, 0, 0,
			0, 0, 0};

	private static final double[] DATA_0204 = new double[]{
			2, 0, 0,
			2, 1, 0,
			1, 2, 0};
	private static final double[] RESULT_0204 = new double[]{
			0, 0, 1,
			0, 0, 0,
			0, 0, 0};

	private static final double[] DATA_03 = new double[]{
			2, 1, 2,
			2, 1, 0,
			1, 2, 0};
	private static final double[] RESULT_03 = new double[]{
			0, 0, 0,
			0, 0, 1,
			0, 0, 0};
}