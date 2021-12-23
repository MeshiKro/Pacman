package view;


import java.io.InputStream;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import controller.SysData;
import javafx.application.Platform;

import javafx.fxml.FXML;

import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;


import misc.GlobalFuncations;

import model.QuestionInJson;

public class yellowQuestScreen {

	@FXML
	private RadioButton answer1;

	@FXML
	private RadioButton answer2;

	@FXML
	private RadioButton answer3;

	@FXML
	private RadioButton answer4;
	@FXML
	private Label pointsLabalMinus;

	@FXML
	private Label pointsLabalPlus;
	@FXML
	private Pane questionPane;

	@FXML
	private AnchorPane wrongPane;

	@FXML
	private Pane correctPane;

	@FXML
	private Label label;

	@FXML
	private Label meg;

	@FXML
	private ImageView okBtn;

	@FXML
	private AnchorPane pane;

	@FXML
	private ImageView startButtonClicked;
	@FXML
	private Label answerFeedback;
	@FXML
	private Label timerLabel;
	@FXML
	private ImageView qImg;

	@FXML
	private Label timeC;

	@FXML
	private Label timeW;

	private static int countdownStarter;

	private ArrayList<QuestionInJson> levelQuestionsArray;

	final ToggleGroup group = new ToggleGroup();

	private QuestionInJson questionToDisplay;

	private boolean answerIsCorrect;


	public void initialize() {

		okBtn.setVisible(true);
		questionPane.setVisible(true);
		correctPane.setVisible(false);
		wrongPane.setVisible(false);

		// adjustment to the level of the question
		if (SysData.qLevel.equals("Easy")) {
			Image img = createImage("question-shield-512");
			qImg.setImage(img);
			meg.setText("easy question");
			meg.setStyle("-fx-text-fill:  #ffffff;");
		}

		if (SysData.qLevel.equals("Medium")) {
			Image img = createImage("question-shield-512 (2)");
			qImg.setImage(img);
			meg.setText("medium question");
			meg.setStyle("-fx-text-fill: #ffa500;");
		}

		if (SysData.qLevel.equals("Hard")) {
			Image img = createImage("question-shield-512 (1)");
			qImg.setImage(img);
			meg.setText("tough question");
			meg.setStyle("-fx-text-fill: #e04006;");
		}
		countdownStarter = 15;

		
		// timer
		final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

		final Runnable runnable = new Runnable() {

			@FXML
			public void run() {
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						timerLabel.setText("" + countdownStarter);
						timeW.setText("" + countdownStarter);
						timeC.setText("" + countdownStarter);

					}
				});

				countdownStarter--;

				if (countdownStarter < 0) {
					scheduler.shutdown();

				}
			}
		};

		scheduler.scheduleAtFixedRate(runnable, 0, 1, TimeUnit.SECONDS);


		if (SysData.qLevel.equals("Easy")) {
			levelQuestionsArray = GlobalFuncations.getQuestionsByLevel("1");		
		}
		if (SysData.qLevel.equals("Medium")) {
			levelQuestionsArray = GlobalFuncations.getQuestionsByLevel("2");		
		}
		if (SysData.qLevel.equals("Hard")) {
			levelQuestionsArray = GlobalFuncations.getQuestionsByLevel("3");			
		}
		

		int index = GlobalFuncations.randomIndex(levelQuestionsArray.size());
		questionToDisplay = levelQuestionsArray.get(index);
		String question = questionToDisplay.getQuestion();
		label.setText(question);
		
		answer1.setText(questionToDisplay.getAnswers()[0]);
		answer2.setText(questionToDisplay.getAnswers()[1]);
		answer3.setText(questionToDisplay.getAnswers()[2]);
		answer4.setText(questionToDisplay.getAnswers()[3]);

		// Single choice question
		answer1.setToggleGroup(group);
		answer2.setToggleGroup(group);
		answer3.setToggleGroup(group);
		answer4.setToggleGroup(group);

	}

	@FXML
	void HoverEnd(MouseEvent event) {

	}

	@FXML
	void OkBtnClicked(MouseEvent event) {
		okBtn.setVisible(false);
		if (answer1.isDisable())
			return;
		if (answerIsCorrect) {
			timeC.setText("" + countdownStarter);
			answerFeedback.setText("");
			SysData.userSelectedCorrectAnswer = true;
			correctPane.setVisible(true);
			if (SysData.qLevel.equals("Easy"))
				pointsLabalPlus.setText("+1 points");
			if (SysData.qLevel.equals("Medium"))
				pointsLabalPlus.setText("+2 points");
			if (SysData.qLevel.equals("Hard"))
				pointsLabalPlus.setText("+3 points");
			wrongPane.setVisible(false);
			questionPane.setVisible(false);
		} else {
			answerFeedback.setText("");
			timeC.setText("" + countdownStarter);
			SysData.userSelectedCorrectAnswer = false;
			wrongPane.setVisible(true);
			if (SysData.qLevel.equals("Easy"))
				pointsLabalMinus.setText("-10 points");
			if (SysData.qLevel.equals("Medium"))
				pointsLabalMinus.setText("-20 points");
			if (SysData.qLevel.equals("Hard"))
				pointsLabalMinus.setText("-30 points");
			correctPane.setVisible(false);
			questionPane.setVisible(false);

		}
	

	}

	@FXML
	void hoverStart(MouseEvent event) {

	}

	@FXML
	void answerClicked(MouseEvent event) {
		Boolean answer1Selected = answer1.isSelected();
		Boolean answer2Selected = answer2.isSelected();
		Boolean answer3Selected = answer3.isSelected();
		Boolean answer4Selected = answer4.isSelected();
		String id ="1";
		if (answer1Selected)
			id = "1";
		else if (answer2Selected)
			id = "2";
		else if (answer3Selected)
			id = "3";
		else if (answer4Selected)
			id = "4";
		answerIsCorrect = isAnswerCorrect(id);

	}

	private boolean isAnswerCorrect(String id) {
		int correctAnswerIndex = Integer.parseInt(questionToDisplay.getCorrect_ans());
		int selectedAnswerIndex = Integer.parseInt(id);
	
		if (correctAnswerIndex == selectedAnswerIndex)
			return true;
		else
			return false;

	}

	private Image createImage(String img) {
		InputStream inStream = getClass().getResourceAsStream("/images/" + img + ".png");
		return new Image(inStream);
	}

}
