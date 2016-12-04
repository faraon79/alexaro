package click.tomasz;

import java.io.IOException;

import click.tomasz.TeachingHelper;
import click.tomasz.network.learning.LearnSet;
import click.tomasz.network.learning.cases.Letters;
import click.tomasz.network.learning.cases.TicTacToe;
import click.tomasz.network.model.TeachingResult;

public class Runner {

	public static void main(String[] agr) throws IOException {

		TeachingHelper teachingHelper = new TeachingHelper();
//		teachingHelper.setInnerLayersSize(9);

//		LearnSet learnSet = new Letters();
		LearnSet learnSet = new TicTacToe();

		TeachingResult result = teachingHelper.execute(learnSet);

		teachingHelper.printFinalWeightsIfSuccess();

//		teachingHelper.saveNetwork("d:/network.ann");
	}
}
