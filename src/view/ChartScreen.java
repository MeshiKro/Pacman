package view;

import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import javafx.scene.layout.AnchorPane;
import misc.JsonRead;
import model.QuestionWithData;

public class ChartScreen {

	@FXML
	NumberAxis xAxis = new NumberAxis();

	@FXML
	CategoryAxis yAxis = new CategoryAxis();

	@FXML
	private BarChart<String, Integer> barChart;

	@FXML
	private ImageView homeBtn;

	@FXML
	private AnchorPane homePane;

	@FXML
	private AnchorPane pane;

	@FXML
	private Label title;

	private ArrayList<QuestionWithData> questionData;

	QuestionWithData question;

	public void initialize(String questionString) {
		initializeChart();

		findQuestion(questionString);

		addDataToChart();

	}

	private void findQuestion(String questionString) {
		questionData = JsonRead.questionWithData;

		for (int i = 0; i < questionData.size(); i++) {
			if (questionData.get(i).getQuestion().equals(questionString))
				question = questionData.get(i);
		}
	}

	private void initializeChart() {
		xAxis.setLabel("Number of user selcted answer");
		xAxis.setTickLabelRotation(90);
		yAxis.setLabel("Answers");
	}

	private void addDataToChart() {

		XYChart.Series<String, Integer> answer = new XYChart.Series();

		for (int i = 0; i < 4; i++) {

			Iterator it = (question.getAnswerData()).entrySet().iterator();
			while (it.hasNext()) {

				Map.Entry pair = (Map.Entry) it.next();
				String ans = covertToTwoLine(pair.getKey().toString());

				int num = Integer.parseInt(pair.getValue().toString().replace(".0", ""));

				answer.getData().add(new XYChart.Data(ans, num));
				it.remove();
			}

		}

		barChart.getData().add(answer);
		
		Node n = barChart.lookup(".data0.chart-bar");
		n.setStyle("-fx-bar-fill: red");
		n = barChart.lookup(".data1.chart-bar");
		n.setStyle("-fx-bar-fill: blue");
		n = barChart.lookup(".data2.chart-bar");
		n.setStyle("-fx-bar-fill: green");
		n = barChart.lookup(".data3.chart-bar");
		n.setStyle("-fx-bar-fill: orange");
	}

	private String covertToTwoLine(String ans) {
		if (ans.length() <= 25)
			return ans;
		try {
			String answer = ans.substring(0, 25) + "\n" + ans.substring(25, 50) + "...";
			return answer;

		} catch (Exception e) {

			return ans.substring(0, 25) + "\n" + ans.substring(25, ans.length());
		}

	}

	@FXML
	void HomeBtnClicked(MouseEvent event) {

	}

	@FXML
	void hoverEndSideButton(MouseEvent event) {

	}

	@FXML
	void hoverStartSideButton(MouseEvent event) {

	}
}
