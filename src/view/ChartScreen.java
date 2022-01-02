package view;

import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import misc.GlobalFuncations;
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
	    private ImageView backBtn;

	    @FXML
	    private AnchorPane backBtnPane;

	    @FXML
	    private ImageView backicon;


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

		System.out.println("questionString "  + questionString);

		initializeColorsArray();

		findQuestion(questionString);

		initializeChart();

		title.setText(questionString.replace("'", ""));

	}

	private void initializeChart() {
		addDataToChart();

		initializeColorsArray();
		
	//	setStyleToChart();
	}

	/*private void addDataToPieChart() {
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
		for (int i=0;i<pieChart.getData().size();i++)
		{
			System.out.print(pieChart.getData().get(i).getPieValue());
			System.out.print(pieChart.getData().get(i).getName());

		}
		pieChart.getData().get(0).setName("dsfgsdfg");
		pieChart.getData().get(0).setPieValue(222);

	}*/

	private void initializeColorsArray() {
		colors = new ArrayList<String>();
		colors.add("blue");
		colors.add("red");
		colors.add("orange");

	}

	private void findQuestion(String questionString) {
		JsonRead JR = new JsonRead();
		questionData = JR.readQuestionsDataFromJson();

		for (int i = 0; i < questionData.size(); i++) {
			if (questionData.get(i).getQuestion().equals(questionString))
				question = questionData.get(i);
		}
	}

	private void addDataToChart() {

		XYChart.Series<String, Integer> answer = new XYChart.Series();

		HashMap<String, Integer> list = (HashMap<String, Integer>) question.getAnswerData().clone();
		System.out.println("list size"  + list.size());

		System.out.println("list "  + list.toString());

		for (int i = 0; i < 4; i++) {
			Iterator it = (list).entrySet().iterator();

			while (it.hasNext()) {

				Map.Entry pair = (Map.Entry) it.next();
				String ans = covertToTwoLine(pair.getKey().toString());

				int num = Integer.parseInt(pair.getValue().toString().replace(".0", ""));

				System.out.println("add anser "  + ans);
				System.out.println("add anser "  + ans.length());
				
				answer.getData().add(new XYChart.Data(ans, num));
				System.out.println("answer: "  + answer.getData().toString());

			pieChartData.add(new PieChart.Data(ans, num));
				
				
				it.remove();
			}

		}
try {
		barChart.getData().add(answer);
}
catch(Exception e)
{
	e.getStackTrace();
}
System.out.println("barChart: "  + barChart.getData().size());

		barChart.setLegendVisible(false);

		
		pieChart.setData(pieChartData);
	

		/*for (int i=0;i<pieChart.getData().size();i++)
		{
			System.out.print(pieChart.getData().get(i).getPieValue());
			System.out.print(pieChart.getData().get(i).getName());

		}*/


        final Label caption = new Label("");
        caption.setTextFill(Color.DARKORANGE);
        caption.setStyle("-fx-font: 24 arial;");

        barChart.setVisible(true);
	}
	
	private void setStyleToChart() {

		int indexOfCorrectAnwer = getIndexOfCorrectAnwer() ;
		if(indexOfCorrectAnwer >0)
			indexOfCorrectAnwer +=1;
		
System.out.print( " indexOfCorrectAnwer " + indexOfCorrectAnwer);
		Node n = barChart.lookup(".data" + indexOfCorrectAnwer + ".chart-bar");
		if (n != null)
			n.setStyle("-fx-bar-fill: green");

		PieChart.Data data = pieChartData.get(indexOfCorrectAnwer);

		if (data != null) {
			data.getNode().setStyle("-fx-pie-color: green;");		 
		}
			
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
	  @FXML
	    void prectengesDisaply(MouseEvent event) {
		
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
		if (ans.length() <= 31)
			return ans;
	
			if(ans.length() <50)
				return ans.substring(0, 25) + "\n" + ans.substring(25, ans.length());

			
			String answer = ans.substring(0, 25) + "\n" + ans.substring(25, 50) + "...";
			return answer;

	

	}

	@FXML
	void HomeBtnClicked(MouseEvent event) {
    	GlobalFuncations.switchScreen(pane,"MainScreen",(getClass().getResource("/view/" + "MainScreen" + ".fxml")),"");

	}

	@FXML
	void changeChartType(MouseEvent event) {
		if (chartBtn.getText().equals("Pie Chart")) {
			pieChart.setVisible(true);
			pieChart.toFront();
			barChart.setVisible(false);
			chartBtn.setText("Bar Chart");
			

		} else {
			pieChart.setVisible(false);
			barChart.toFront();
			barChart.setVisible(true);
			chartBtn.setText("Pie Chart");

		}

	}

	private void hoverSideButton(String id) {
		if (id == null)
			return;
		Image image = createImage("buttonconinterClicked");
		id = id.replace("Btn", "").replace("Pane", "");

		switch (id) {

		case "back":
			backBtn.setImage(image);
			break;
		default:
			image = createImage("buttonconinter");
			backBtn.setImage(image);

		}

	}

	private Image createImage(String img) {
		InputStream inStream = getClass().getResourceAsStream("/images/" + img + ".png");
		return new Image(inStream);
	}
	
	@FXML
	void hoverStartSideButton(MouseEvent event) {
		String img ="buttonconinterClicked";
		GlobalFuncations.hoverButton(getClass().getResourceAsStream("/images/" + img + ".png"), backBtn);		

	}
	
	@FXML
	void hoverEndSideButton(MouseEvent event) {
		String img ="buttonconinter";
		GlobalFuncations.hoverButton(getClass().getResourceAsStream("/images/" + img + ".png"), backBtn);		
	}

	// End Hover Section

	// OnClick Section

	

	@FXML
	void BackBtnClicked(MouseEvent event) {
		GlobalFuncations.switchScreen(pane, "QuestionsListScreen",
				(getClass().getResource("/view/" + "QuestionsListScreen" + ".fxml")), "");

	}

    @FXML
    void hoverStartC(MouseEvent event) {
    	chartBtn.setStyle("-fx-background-color: #cba79a; -fx-background-radius:25; -fx-border-width:5; -fx-border-color:white; -fx-border-radius:25");
    	 
    }
    
    @FXML
    void HoverEndC(MouseEvent event) {
    	chartBtn.setStyle("-fx-background-color: #efe4e0; -fx-background-radius:25; -fx-border-width:5; -fx-border-color:white; -fx-border-radius:25");

    }
	

}
