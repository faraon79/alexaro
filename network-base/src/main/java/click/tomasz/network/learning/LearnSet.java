package click.tomasz.network.learning;

import java.util.ArrayList;
import java.util.List;

public class LearnSet {

	private List<LearnData> learnDatas;

	public LearnSet() {
		learnDatas = new ArrayList<LearnData>();
	}

	public void addItem(double[] data, double[] expectedResult){
		learnDatas.add(new LearnData(data, expectedResult));
	}

	public List<LearnData> getLearnDatas() {
		return learnDatas;
	}

	public int getSize() {
		return learnDatas.size();
	}

	public int getResultSize() {
		return learnDatas.get(0).getExpectedResultSize();
	}

	public int getInputSize() {
		return learnDatas.get(0).getData().length;
	}
}
