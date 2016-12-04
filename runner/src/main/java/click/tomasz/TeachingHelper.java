package click.tomasz;

import java.io.IOException;

import click.tomasz.network.common.ActivationFunctionFactory;
import click.tomasz.network.common.ActivationFunctionType;
import click.tomasz.network.learning.LearnSet;
import click.tomasz.network.learning.Teacher;
import click.tomasz.network.learning.TeacherConfigurator;
import click.tomasz.network.learning.TeacherImpl;
import click.tomasz.network.model.ActivationFunction;
import click.tomasz.network.model.Network;
import click.tomasz.network.model.NetworkImpl;
import click.tomasz.network.model.NetworkParameters;
import click.tomasz.network.model.TeachingResult;
import click.tomasz.network.persistence.FileUtil;

public class TeachingHelper {

	private Network network;
	private NetworkParameters networkParameters;
	private ActivationFunction activationFunction;
	private TeachingResult teachingResult;

	public TeachingHelper() {
		networkParameters = new NetworkParameters();
		activationFunction = ActivationFunctionFactory.getFunction(ActivationFunctionType.SIGMOIDAL_UNIPOLAR);
	}

	public void setInnerLayersSize(int... innerLayerSize) {
		networkParameters.getInnerLayersSize().clear();
		for (int layerSize : innerLayerSize) {
			networkParameters.getInnerLayersSize().add(layerSize);
		}
	}

	public void setActivationFunction(ActivationFunctionType functionType) {
		activationFunction = ActivationFunctionFactory.getFunction(functionType);
	}

	public TeachingResult execute(TeacherConfigurator configurator, LearnSet learnSet) {
		networkParameters.setInputSize(learnSet.getInputSize());
		networkParameters.setResultSize(learnSet.getResultSize());
		networkParameters.setActivationFunction(activationFunction);

		network = new NetworkImpl(networkParameters);

		Teacher teacher = new TeacherImpl(network, learnSet, configurator);
		teachingResult = teacher.teach();
		return teachingResult;
	}

	public TeachingResult execute(LearnSet learnSet) {
		TeacherConfigurator configurator = new TeacherConfigurator();
		return execute(configurator, learnSet);
	}

	public void printFinalWeightsIfSuccess() {
		if (teachingResult.getExamNote()) {
			System.out.println("");
			System.out.println("*********** Weights to copy ***********\n");

			for (int i = 0; i < network.getLayers().length; i++) {
				System.out.print("{");
				for (int j = 0; j < network.getLayers()[i].getSize(); j++) {
					System.out.print("{");
					for (int k = 0; k < network.getLayers()[i].getNeurons()[j].getSize(); k++) {
						System.out.print("" + network.getLayers()[i].getNeurons()[j].getWeights()[k] +
								(k < network.getLayers()[i].getNeurons()[j].getSize() - 1 ? "," : ""));
					}
					System.out.println("}" + (j < network.getLayers()[i].getSize() - 1 ? "," : ""));
				}
				System.out.println("}" + (i < network.getLayers().length - 1 ? "," : ""));
			}

			System.out.println("\n*************     End     *************");
		}
	}

	public void saveNetwork(String path) throws IOException {
		FileUtil.save(network, path);
	}
}