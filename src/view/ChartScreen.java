package view;

import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
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
	private PieChart pieChart;

	@FXML
	private AnchorPane homePane;

	@FXML
	private Button chartBtn;

	@FXML
	private AnchorPane pane;

	@FXML
	private Label title;

	private ArrayList<QuestionWithData> questionData;

	QuestionWithData question;

	ArrayList<String> colors;

	ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

	public void initialize(String questionString) {

		initializeColorsArray();

		findQuestion(questionString);

		initializeChart();

		title.setText(questionString);

	}

	private void initializeChart() {
		addDataToChart();

		setStyleToChart();
	}

	private void addDataToPieChart() {
		ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

		HashMap<String, Integer> list = (HashMap<String, Integer>) question.getAnswerData().clone();

		for (int i = 0; i < 4; i++) {
			Iterator it = (list).entrySet().iterator();

			while (it.hasNext()) {

				Map.Entry pair = (Map.Entry) it.next();
				String ans = covertToTwoLine(pair.getKey().toString());

				int num = Integer.parseInt(pair.getValue().toString().replace(".0", ""));

				pieChartData.add(new PieChart.Data(ans, num));
				it.remove();
			}

		}

		pieChart.setData(pieChartData);
	}

	private void initializeColorsArray() {
		colors = new ArrayList<String>();
		colors.add("blue");
		colors.add("red");
		colors.add("orange");

	}

	private void findQuestion(String questionString) {
		questionData = JsonRead.questionWithData;

		for (int i = 0; i < questionData.size(); i++) {
			if (questionData.get(i).getQuestion().equals(questionString))
				question = questionData.get(i);
		}
	}

	private void addDataToChart() {

		XYChart.Series<String, Integer> answer = new XYChart.Series();

		HashMap<String, Integer> list = (HashMap<String, Integer>) question.getAnswerData().clone();

		for (int i = 0; i < 4; i++) {
			Iterator it = (list).entrySet().iterator();

			while (it.hasNext()) {

				Map.Entry pair = (Map.Entry) it.next();
				String ans = covertToTwoLine(pair.getKey().toString());

				int num = Integer.parseInt(pair.getValue().toString().replace(".0", ""));

				answer.getData().add(new XYChart.Data(ans, num));
				pieChartData.add(new PieChart.Data(ans, num));
				
				
				it.remove();
			}

		}

		barChart.getData().add(answer);
		barChart.setLegendVisible(false);

		
		pieChart.setData(pieChartData);

		pieChart.getData().stream().forEach(data -> {
		    Tooltip tooltip = new Tooltip();
		    tooltip.setText(data.getPieValue() + "%");
		    Tooltip.install(data.getNode(), tooltip);
		    data.pieValueProperty().addListener((observable, oldValue, newValue) -> 
		        tooltip.setText(newValue + "%"));
		});
		
		

	}

	private void setStyleToChart() {

		int indexOfCorrectAnwer = getIndexOfCorrectAnwer() - 1;

		Node n = barChart.lookup(".data" + indexOfCorrectAnwer + ".chart-bar");
		if (n != null)
			n.setStyle("-fx-bar-fill: green");

		PieChart.Data data = pieChartData.get(indexOfCorrectAnwer);

		if (data != null)
			data.getNode().setStyle("-fx-pie-color: green;");

		for (int i = 0; i < 4; i++) {
			n = barChart.lookup(".data" + i + ".chart-bar");
			data = pieChartData.get(i);
			if (i != indexOfCorrectAnwer && n != null) {
				String color = getRandomColor();
				n.setStyle("-fx-bar-fill: " + color);
				data.getNode().setStyle("-fx-pie-color: " + color + ";");
			}

		}

		initializeColorsArray();

	}

	private String getRandomColor() {
		Random r = new Random();
		int index = r.nextInt(colors.size());
		String color = colors.get(index);
		colors.remove(index);
		return color;

	}

	private int getIndexOfCorrectAnwer() {
		String ans = question.getCorrect_ans();
		int index = 0;
		System.out.print(" siuze " + question.getAnswerData().size());

		Iterator it = (question.getAnswerData()).entrySet().iterator();
		while (it.hasNext()) {

			Map.Entry pair = (Map.Entry) it.next();

			if (pair.getKey().equals(ans))
				return index;
			else
				index++;

			it.remove();
		}

		return index;
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
	void changeChartType(MouseEvent event) {
		if (chartBtn.getText().equals("Pie Chart")) {
			pieChart.setVisible(true);
			barChart.setVisible(false);
			chartBtn.setText("Bar Chart");
			pieChart.setData(pieChartData);

			pieChart.getData().stream().forEach(data -> {
			    Tooltip tooltip = new Tooltip();
			    tooltip.setText(data.getPieValue() + "%");
			    Tooltip.install(data.getNode(), tooltip);
			    data.pieValueProperty().addListener((observable, oldValue, newValue) -> 
			        tooltip.setText(newValue + "%"));
			});
			
			

		} else {
			pieChart.setVisible(false);
			barChart.setVisible(true);
			chartBtn.setText("Pie Chart");

		}

	}

	@FXML
	void hoverEndSideButton(MouseEvent event) {

	}

	@FXML
	void hoverStartSideButton(MouseEvent event) {

	}
}
