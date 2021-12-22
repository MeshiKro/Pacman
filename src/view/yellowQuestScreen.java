package view;

import java.util.ArrayList;

import controller.SysData;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import misc.GlobalFuncations;
import misc.JsonRead;
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

	private ArrayList<QuestionInJson> mediumQuestionsArray;


	final ToggleGroup group = new ToggleGroup();

	private QuestionInJson questionToDisplay;

	private boolean answerIsCorrect;

    
	public void initialize() {
	mediumQuestionsArray =GlobalFuncations.getQuestionsByLevel("2");
	int index = GlobalFuncations.randomIndex(mediumQuestionsArray.size());
	System.out.println(mediumQuestionsArray.size());
	System.out.println(index);
	 questionToDisplay =mediumQuestionsArray.get(index);
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
    	if(answerIsCorrect) {
    		answerFeedback.setText("answer coorect");
    		SysData.userSelectedCorrectAnswer = true;
    	}
    	else {
    		answerFeedback.setText("answer not correct!!");
    		SysData.userSelectedCorrectAnswer = false;

    	}
    }

    @FXML
    void hoverStart(MouseEvent event) {

    }
    @FXML
    void answerClicked(MouseEvent event) {
    	if(event == null)
    		return;
    	if(event.getPickResult().getIntersectedNode().getId() == null)
    		return;
      answerIsCorrect = isAnswerCorrect(event.getPickResult().getIntersectedNode().getId());
    	

    }



	private boolean isAnswerCorrect(String id) {
		int correctAnswerIndex = Integer.parseInt(questionToDisplay.getCorrect_ans())-1;
    	int selectedAnswerIndex = Integer.parseInt(id.replace("answer",""));
    	return correctAnswerIndex== selectedAnswerIndex;
		
	}

}
