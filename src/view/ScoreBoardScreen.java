package view;

import java.sql.Date;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import misc.GlobalFuncations;

public class ScoreBoardScreen {

    @FXML
    private AnchorPane pane;

    @FXML
    private AnchorPane homePane;

    @FXML
    private ImageView homeBtn;

    @FXML
    private Label NickName1;

    @FXML
    private Label NickName2;

    @FXML
    private Label NickName3;

    @FXML
    private Label NickName4;

    @FXML
    private Label NickName5;

    @FXML
    private Label NickName6;

    @FXML
    private Label NickName8;

    @FXML
    private Label NickName7;

    @FXML
    private Label NickName9;

    @FXML
    private Label NickName10;

    @FXML
    private Label Score1;

    @FXML
    private Label Score2;

    @FXML
    private Label Score3;

    @FXML
    private Label Score4;

    @FXML
    private Label Score5;

    @FXML
    private Label Score6;

    @FXML
    private Label Score8;

    @FXML
    private Label Score7;

    @FXML
    private Label Score9;

    @FXML
    private Label Score10;

    @FXML
    private Label Date1;

    @FXML
    private Label Date2;

    @FXML
    private Label Date3;

    @FXML
    private Label Date4;

    @FXML
    private Label Date5;

    @FXML
    private Label Date6;

    @FXML
    private Label Date8;

    @FXML
    private Label Date7;

    @FXML
    private Label Date9;

    @FXML
    private Label Date10;
    
    
 
	
    

    //Initialize ScoreBoard//
	public void initialize() {
		
		

		NickName1.setText("hello Check");
		NickName2.setText("hello Check");
		NickName3.setText("hello Check");
		NickName4.setText("hello Check");
		NickName5.setText("hello Check");
		NickName6.setText("hello Check");
		NickName7.setText("hello Check");
		NickName8.setText("hello Check");
		NickName9.setText("hello Check");
		NickName10.setText("hello Check");

		
		Score1.setText("200");
		Score2.setText("200");
		Score3.setText("200");
		Score4.setText("200");
		Score5.setText("200");
		Score6.setText("200");
		Score7.setText("200");
		Score8.setText("200");
		Score9.setText("200");
		Score10.setText("200");
		
		
		Date1.setText("dd / mm / yy");
		Date2.setText("dd / mm / yy");
		Date3.setText("dd / mm / yy");
		Date4.setText("dd / mm / yy");
		Date5.setText("dd / mm / yy");
		Date6.setText("dd / mm / yy");
		Date7.setText("dd / mm / yy");
		Date8.setText("dd / mm / yy");
		Date9.setText("dd / mm / yy");
		Date10.setText("dd / mm /yy");
		
		
		
	}
    //Initialize ScoreBoard end//

    
	// Hover Section
    @FXML
	void hoverStartSideButton(MouseEvent event) {
		String img ="buttonconinterClicked";
		GlobalFuncations.hoverButton(getClass().getResourceAsStream("/images/" + img + ".png"), homeBtn);		

	}
	
	@FXML
	void hoverEndSideButton(MouseEvent event) {
		String img ="buttonconinter";
		GlobalFuncations.hoverButton(getClass().getResourceAsStream("/images/" + img + ".png"), homeBtn);		
	}


	// End Hover Section

	
	
	
	// OnClick Section//
	 
    @FXML
    void HomeBtnClicked(MouseEvent event) {
    	GlobalFuncations.switchScreen(pane,"MainScreen",(getClass().getResource("/view/" + "MainScreen" + ".fxml")),"");
    }
    
	// End OnClick Section

}
