package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

import controller.SysData;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
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

	private static int interval =10;

	private static Timer timer;
	public void initialize() {
	mediumQuestionsArray =GlobalFuncations.getQuestionsByLevel("2");
	int index = GlobalFuncations.randomIndex(mediumQuestionsArray.size());
	
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

		
     timer = new Timer(0, new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
	
        	//timerLabel.setText(String.valueOf(interval));
        	System.out.println(interval);
        	
        	if (interval == 0)
    			try {
    			//	OkBtnClicked(new MouseEvent(null, index, index, index, index, null, index, answerIsCorrect, answerIsCorrect, answerIsCorrect, answerIsCorrect, answerIsCorrect, answerIsCorrect, answerIsCorrect, answerIsCorrect, answerIsCorrect, answerIsCorrect, null));

    				timer.stop();
    			} catch (Exception e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    	     --interval;
        	
        }    
    });
    timer.start();
	
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

      ((Node)(event.getSource())).getScene().getWindow().hide();


    }



	private boolean isAnswerCorrect(String id) {
		int correctAnswerIndex = Integer.parseInt(questionToDisplay.getCorrect_ans())-1;
    	int selectedAnswerIndex = Integer.parseInt(id.replace("answer",""));
    	return correctAnswerIndex== selectedAnswerIndex;
		
	}

}
