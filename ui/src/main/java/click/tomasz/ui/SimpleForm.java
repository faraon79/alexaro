package click.tomasz.ui;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.plaf.basic.BasicSplitPaneDivider;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Map;

import click.tomasz.network.TeachingHelper;
import click.tomasz.network.learning.LearnSet;
import click.tomasz.network.learning.TeacherConfigurator;
import click.tomasz.network.learning.cases.TicTacToe;
import click.tomasz.network.model.TeachingResult;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.chart.Chart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class SimpleForm {

	public static final String FRAME_NAME = "Chart";

	private static JPanel top;
	private static JPanel left;
	private static JFXPanel chartFxPanel;
	private static XYChart.Series<Number, Number> chartSeries;
	private static JFrame frame;
	private static Dimension frameSize;
	private static JButton startButton;
	private static JLabel alphaLabel;
	private static JLabel etaLabel;
	private static JLabel errorLabel;
	private static JLabel epochLimitLabel;
	private static JLabel innerSizeLabel;
	private static JTextField alphaText;
	private static JTextField etaText;
	private static JTextField errorText;
	private static JTextField epochLimitText;
	private static JTextField innerSizeText;
	private static JSplitPane split;

	private static void createUIComponents() {
		frame = new JFrame(FRAME_NAME);
		frameSize = new Dimension(1280, 720);
		top = new JPanel();
		split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		left = new JPanel();
		chartSeries = getChartSeries();

		alphaLabel = new JLabel("alpha:");
		etaLabel = new JLabel("eta:");
		errorLabel = new JLabel("error:");
		epochLimitLabel = new JLabel("epoch loop:");
		innerSizeLabel = new JLabel("inner size: ");

		alphaText = new JTextField("0.9");
		etaText = new JTextField("0.2");
		errorText = new JTextField("0.01");
		epochLimitText = new JTextField("400");
		innerSizeText = new JTextField("4");

		startButton = getStartButton();
	}

	private static void setLayout() {
		top.setLayout(new BorderLayout());

		startButton.setBounds(10, 10, 100, 25);

		alphaLabel.setBounds(10, startButton.getY() + startButton.getSize().height + 10, 50, 25);
		etaLabel.setBounds(10, alphaLabel.getY() + alphaLabel.getSize().height + 10, 50, 25);
		errorLabel.setBounds(10, etaLabel.getY() + etaLabel.getSize().height + 10, 50, 25);
		innerSizeLabel.setBounds(10, errorLabel.getY() + errorLabel.getHeight() + 10, 65, 25);
		epochLimitLabel.setBounds(startButton.getX() + startButton.getSize().width + 30, 10, 70, 25);

		alphaText.setBounds(alphaLabel.getX() + alphaLabel.getSize().width + 10, alphaLabel.getY(), 50, 25);
		etaText.setBounds(etaLabel.getX() + etaLabel.getSize().width + 10, etaLabel.getY(), 50, 25);
		errorText.setBounds(errorLabel.getX() + errorLabel.getSize().width + 10, errorLabel.getY(), 50, 25);
		innerSizeText.setBounds(innerSizeLabel.getX() + innerSizeLabel.getWidth(), innerSizeLabel.getY(), 45, 25);
		epochLimitText.setBounds(epochLimitLabel.getX() + epochLimitLabel.getSize().width + 10, 10, 50, 25);

		left.setLayout(null);
		left.add(alphaLabel);
		left.add(alphaText);
		left.add(etaLabel);
		left.add(etaText);
		left.add(errorLabel);
		left.add(errorText);
		left.add(innerSizeLabel);
		left.add(innerSizeText);
		left.add(startButton);
		left.add(epochLimitLabel);
		left.add(epochLimitText);

		split.setLeftComponent(left);
		split.setRightComponent(chartFxPanel);

		split.setDividerLocation(epochLimitText.getX() + epochLimitText.getSize().width + 10);
		split.setEnabled(false);
		split.setDividerSize(1);

		BasicSplitPaneDivider divider = (BasicSplitPaneDivider) split.getComponent(0);
		divider.setBorder(null);

		top.add(split);
	}

	public static void main(String[] args) {
		createUIComponents();
		frame.setLayout(null);
		top.setLocation(0, 0);

		JApplet applet = new JApplet() {
			@Override
			public void init() {
				chartFxPanel = new JFXPanel();
			}
		};
		applet.init();

		setLayout();

		frame.setContentPane(top);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		frame.setSize(frameSize);

		Platform.runLater(() -> createScene());
	}

	private static XYChart.Series<Number, Number> getChartSeries() {
		XYChart.Series<Number, Number> chartSeries = new XYChart.Series<>();
		chartSeries.setName("Epoch error");
		return chartSeries;
	}

	private static JButton getStartButton() {
		JButton jButton = new JButton("Start");
		jButton.addActionListener((event) -> {
			TeachingResult result = getTeachingResult();
			updateChartData(result);
			createScene();
		});
		return jButton;
	}

	private static void createScene() {
		Chart chart = createLineChart();
		chartFxPanel.setScene(new Scene(chart));
	}

	private static LineChart createLineChart() {
		final NumberAxis xAxis = new NumberAxis();
		final NumberAxis yAxis = new NumberAxis();
		xAxis.setLabel("epoch");

		final LineChart<Number, Number> lineChart =
				new LineChart<>(xAxis, yAxis);

		lineChart.setTitle("Epoch errors");
		lineChart.setCreateSymbols(false);
		lineChart.getData().add(chartSeries);
		return lineChart;
	}

	private static void updateChartData(TeachingResult teachingResult) {
		updateChartSeries(teachingResult);
	}

	private static TeachingResult getTeachingResult() {
		TeachingHelper teachingHelper = new TeachingHelper();
		teachingHelper.setInnerLayersSize(Integer.valueOf(innerSizeText.getText()));

//		LearnSet learnSet = new Letters();
		LearnSet learnSet = new TicTacToe();

		TeacherConfigurator configuration = new TeacherConfigurator();
		configuration.setAlpha(Double.valueOf(alphaText.getText()));
		configuration.setEta(Double.valueOf(etaText.getText()));
		configuration.setTeachLoopLimit(Integer.valueOf(epochLimitText.getText()));
		configuration.setAcceptableError(Double.valueOf(errorText.getText()));

		return teachingHelper.execute(configuration, learnSet);
	}

	private static void updateChartSeries(TeachingResult teachingResult) {
		chartSeries = getChartSeries();
		for (Map.Entry entry : teachingResult.getEpochErrors().entrySet()) {
			chartSeries.getData().add(new XYChart.Data<>((Number) entry.getKey(), (Number) entry.getValue()));
		}
	}
}
