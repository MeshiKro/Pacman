package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.Timer;

import controller.SysData;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.css.converter.PaintConverter;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import misc.GlobalFuncations;
import misc.JsonRead;
import model.Ghost;
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
    @FXML
    private ImageView qImg;

    
    private String path;
    private MediaPlayer mediaPlayer;

	private ArrayList<QuestionInJson> levelQuestionsArray;


	final ToggleGroup group = new ToggleGroup();

	private QuestionInJson questionToDisplay;

	private boolean answerIsCorrect;

	private static int interval =10;

	private static Timer timer;
	
	
/*	//timer
	 final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	 @FXML
      final Runnable runnable = new Runnable() {
          int countdownStarter = 15;
          
          public void run() {
        //  	timerLabel.setText(String.valueOf(countdownStarter));
         /*     System.out.println(countdownStarter);
              countdownStarter--;

              if (countdownStarter < 0) {
                  System.out.println("Timer Over!");
                  scheduler.shutdown();
              }*/
   /*       }
      }; */
   
    
	

	
	public void initialize() {
		
		//adjustment to the level of the question
		if(SysData.qLevel.equals("Easy")){
		Image img = createImage("question-shield-512");
		qImg.setImage(img);
		meg.setText("easy question");
		meg.setStyle("-fx-text-fill:  #ffffff;");
		}
		if(SysData.qLevel.equals("Medium")){
			Image img = createImage("question-shield-512 (2)");
			qImg.setImage(img);
			meg.setText("medium question");
			meg.setStyle("-fx-text-fill: #ffa500;");
			
		
		}

		if(SysData.qLevel.equals("Hard")){
		Image img = createImage("question-shield-512 (1)");
		qImg.setImage(img);
		meg.setText("tough question");
		meg.setStyle("-fx-text-fill: #e04006;");
		}
		
		/* timer = new Timer(15000, new ActionListener() {
	            public void actionPerformed(ActionEvent evt) {
	        	//...Update the progress bar...

	                 
	            }    
	        });
	        timer.start();*/
	
		/*	ScheduledExecutorService timer=Executors.newSingleThreadScheduledExecutor();

			int countDown=15;
			timer.schedule(new Runnable() {
	       

		    @Override
		        public void run() {
		        Platform.runLater(new Runnable() {
		           public void run() {
		               countDown--;
		               timerLabel.setText("Time left:" + countDown);

		                if (countDown < 0)
		                    ((TimerTask) timer).cancel();
		          }
		        });
		    }
		    }, 1000, 1000); //Every 1 second*/
		    
		//timer
	 final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	
      final Runnable runnable = new Runnable() {
          int countdownStarter = 15;
          @FXML
          public void run() {
          //	timerLabel.setText(""+countdownStarter);
              System.out.println(countdownStarter);
              countdownStarter--;

              if (countdownStarter < 0) {
                  System.out.println("Timer Over!");
                  scheduler.shutdown();
              }
          }
      }; 
	
		 scheduler.scheduleAtFixedRate(runnable, 0, 1, TimeUnit.SECONDS);
	//	  	timerLabel.setText(String.valueOf(countdownStarter));
	
	levelQuestionsArray =GlobalFuncations.getQuestionsByLevel("2");
	int index = GlobalFuncations.randomIndex(levelQuestionsArray.size());
	
	 questionToDisplay =levelQuestionsArray.get(index);
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
    	if(answer1.isDisable())
    		return;
    	if(answerIsCorrect) {
    		answerFeedback.setText("answer coorect");
    		SysData.userSelectedCorrectAnswer = true;
    	}
    	else {
    		answerFeedback.setText("answer not correct!!");
    		SysData.userSelectedCorrectAnswer = false;

    	}
    	answer1.setDisable(true);
    	answer2.setDisable(true);
    	answer3.setDisable(true);
    	answer4.setDisable(true);

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
	
	private Image createImage(String img) {
		InputStream inStream = getClass().getResourceAsStream("/images/" + img + ".png");
		return new Image(inStream);
	}

}
