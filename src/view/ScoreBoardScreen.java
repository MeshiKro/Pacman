package view;


import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import misc.GlobalFuncations;
import misc.JsonRead;
import model.ScoreboardRecord;

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
    
    private  Font f;
 
	
    

    //Initialize ScoreBoard//
	public void initialize() {
		
		
		
		JsonRead JR = new JsonRead();

		ArrayList<ScoreboardRecord> records = 	JR.readScoreBoardFromJson();	
		
		 f = Font.loadFont(getClass().getResourceAsStream("/resources/fonts/Alef.ttf"), 20);
		
		for(int i=0;i<records.size();i++)
		{
			ScoreboardRecord record = records.get(i);
			
			setLabel("NickName" + String.valueOf(i+1),record.getNickname());
			
			setLabel("Score" + String.valueOf(i+1),String.valueOf( record.getScore()));
			
			setLabel("Date" + String.valueOf(i+1),record.getDate());

		}
		
		
		
		
	}
    private void setLabel(String id, String text) {
		Label label = findLabel(id);		
		if(label == null)
			return;
		(label).setText(text);	
		label.setFont(f);

	}
    
	private Label findLabel(String id) {
    	
    	for( int i =0;i<pane.getChildren().size();i++)
    	{
    		 Node current = pane.getChildren().get(i);
    		 if(current.getId() != null && current.getId().equals(id))
    		 {
    			 return (Label) current;
    		 }
    	}
    	return null;
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
