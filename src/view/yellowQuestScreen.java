package view;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
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
import misc.JsonWriterEx;
import model.QuestionInJson;

public class yellowQuestScreen {

	@FXML
	private RadioButton answer1;

	@FXML
	private RadioButton answer2;
	@FXML
	private ImageView fiftyIcon;

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
	private ImageView helpIcon1;

	@FXML
	private ImageView helpIcon2;
	@FXML
	private Label timeW;

	private static int countdownStarter;

	private ArrayList<QuestionInJson> levelQuestionsArray;

	final ToggleGroup group = new ToggleGroup();

	private QuestionInJson questionToDisplay;

	private boolean answerIsCorrect;

	@FXML
	private ImageView fiftyIcon12;

	public void initialize() {

		okBtn.setVisible(true);
		questionPane.setVisible(true);
		correctPane.setVisible(false);
		wrongPane.setVisible(false);

		if (SysData.numberOfHelpIcon == 1) {
			disableIcon(helpIcon2);
		} else if (SysData.numberOfHelpIcon <= 0) {
			disableIcon(helpIcon1);
			disableIcon(helpIcon2);

		}

		
		if (SysData.numberOfFiftyIcon == 1) {
			disableIcon(fiftyIcon12);
		} else if (SysData.numberOfFiftyIcon <= 0) {
			disableIcon(fiftyIcon12);
			disableIcon(fiftyIcon);

		}

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
		// if (answer1.isDisable())
		// return;
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

		JsonWriterEx jw = new JsonWriterEx();

		String id = "1";
		if (answer1Selected) {
			id = "1";
			jw.updateQuestionDataArray(questionToDisplay.getQuestion(), answer1.getText());

		} else if (answer2Selected) {
			id = "2";
			jw.updateQuestionDataArray(questionToDisplay.getQuestion(), answer2.getText());

		} else if (answer3Selected) {
			id = "3";
			jw.updateQuestionDataArray(questionToDisplay.getQuestion(), answer3.getText());

		} else if (answer4Selected) {
			id = "4";
			jw.updateQuestionDataArray(questionToDisplay.getQuestion(), answer4.getText());

		}
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

	@FXML
	void helpIconClicked(MouseEvent event) {
		if (SysData.numberOfHelpIcon <= 0)
			return;
		SysData.numberOfHelpIcon--;

		ArrayList<Integer> wrongAnswer = getWrongAnswersArray(Integer.parseInt(questionToDisplay.getCorrect_ans()));
		for (int i = 0; i < wrongAnswer.size(); i++) {
			disableAnswer(wrongAnswer.get(i), true);
		}

		disableAllHelpIcon();
	

	}

	private void disableAllHelpIcon() {
		disableIcon(helpIcon1);
		disableIcon(helpIcon2);	
		disableIcon(fiftyIcon12);
		disableIcon(fiftyIcon);
	}

	@FXML
	void fiftyIconClicked(MouseEvent event) {

		if (SysData.numberOfFiftyIcon <= 0)
			return;
		SysData.numberOfFiftyIcon--;

		ArrayList<Integer> wrongAnswer = getWrongAnswersArray(Integer.parseInt(questionToDisplay.getCorrect_ans()));

		int randomAnswer1 = getRandomElement(wrongAnswer);
		wrongAnswer = removeElement(randomAnswer1, wrongAnswer);
		int randomAnswer2 = getRandomElement(wrongAnswer);

		// System.out.print(" wrongAnswer "+ wrongAnswer);
		// System.out.print(" randomAnswer1 "+ randomAnswer1);

		// disable 2 wrongs answer
		disableAnswer(randomAnswer1, true);
		disableAnswer(randomAnswer2, true);

	

		disableAllHelpIcon();
	}

	private ArrayList<Integer> getWrongAnswersArray(int correctAnswerIndex) {
		ArrayList<Integer> wrongAnswer = new ArrayList<Integer>();
		for (int i = 1; i <= 4; i++) {
			if (i != correctAnswerIndex)
				wrongAnswer.add(i);

		}
		return wrongAnswer;
	}

	private void disableIcon(ImageView iconToDisable) {
		iconToDisable.setDisable(true);
		iconToDisable.setOpacity(0.4);

	}

	private ArrayList<Integer> removeElement(int num, ArrayList<Integer> wrongAnswer) {
		for (int i = 0; i < wrongAnswer.size(); i++) {
			if (wrongAnswer.get(i).equals(num)) {
				wrongAnswer.remove(i);
				break;

			}
		}
		return wrongAnswer;
	}

	private void disableAnswer(int randomAnswer, boolean status) {

		switch (randomAnswer) {
		case 1:
			answer1.setDisable(true);
			break;
		case 2:
			answer2.setDisable(true);
			break;
		case 3:
			answer3.setDisable(true);
			break;
		case 4:
			answer4.setDisable(true);
			break;
		}

	}

	public int getRandomElement(ArrayList<Integer> arr) {
		return arr.get(ThreadLocalRandom.current().nextInt(arr.size()));
	}

	private Image createImage(String img) {
		InputStream inStream = getClass().getResourceAsStream("/images/" + img + ".png");
		return new Image(inStream);
	}

}
