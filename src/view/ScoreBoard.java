package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.TextFlow;
import misc.Global;

public class ScoreBoard {

    @FXML
    private AnchorPane pane;

    @FXML
    private AnchorPane homePane;

    @FXML
    private ImageView homeBtn;

    @FXML
    private TextFlow NickName1;

    @FXML
    private TextFlow NickName2;

    @FXML
    private TextFlow NickName3;

    @FXML
    private TextFlow NickName4;

    @FXML
    private TextFlow NickName5;
    

    
    
	public void initialize() {

		//Here the scoreBoard ArrayList Will Be injected to the ScoreBoard Components//
	}
    
    
    @FXML
    void HomeBtnClicked(MouseEvent event) {
    	Global.switchScreen(pane,"MainScreen",(getClass().getResource("/view/" + "MainScreen" + ".fxml")));
    }
    
    
    @FXML
    void hoverEndSideButton(MouseEvent event) {

    }

    @FXML
    void hoverStartSideButton(MouseEvent event) {

    }

}
