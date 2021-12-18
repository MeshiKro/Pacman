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
    

    @FXML
    private ImageView trophy1;

    @FXML
    private ImageView trophy10;

    @FXML
    private ImageView trophy2;

    @FXML
    private ImageView trophy3;

    @FXML
    private ImageView trophy4;

    @FXML
    private ImageView trophy5;

    @FXML
    private ImageView trophy6;

    @FXML
    private ImageView trophy7;

    @FXML
    private ImageView trophy8;

    @FXML
    private ImageView trophy9;
    
    private  Font f;
 
	
    

    //Initialize ScoreBoard//
	public void initialize() {
		
		trophy1.setVisible(false);
		trophy2.setVisible(false);
		trophy3.setVisible(false);
		trophy4.setVisible(false);
		trophy5.setVisible(false);
		trophy6.setVisible(false);
		trophy7.setVisible(false);
		trophy8.setVisible(false);
		trophy9.setVisible(false);
		trophy10.setVisible(false);
		
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
		
		if(Integer.parseInt(Score1.getText())>=200) {
			trophy1.setVisible(true);
		}
		if(Integer.parseInt(Score2.getText())>=200) {
			trophy2.setVisible(true);
		}
		if(Integer.parseInt(Score3.getText())>=200) {
			trophy3.setVisible(true);
		}
		if(Integer.parseInt(Score4.getText())>=200) {
			trophy4.setVisible(true);
		}
		if(Integer.parseInt(Score5.getText())>=200) {
			trophy5.setVisible(true);
		}
		if(Integer.parseInt(Score6.getText())>=200) {
			trophy6.setVisible(true);
		}
		if(Integer.parseInt(Score7.getText())>=200) {
			trophy7.setVisible(true);
		}
		if(Integer.parseInt(Score8.getText())>=200) {
			trophy8.setVisible(true);
		}
		if(Integer.parseInt(Score9.getText())>=200) {
			trophy9.setVisible(true);
		}
		if(Integer.parseInt(Score10.getText())>=200) {
			trophy10.setVisible(true);
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
