package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import misc.GlobalFuncations;

public class Rules1Screen {

    @FXML
    private ImageView backBtn;

    @FXML
    private AnchorPane backBtnPane;

    @FXML
    private ImageView backicon;

    @FXML
    private ImageView homeBtn;

    @FXML
    private AnchorPane homePane;

    @FXML
    private Label label;

    @FXML
    private Label label1;

    @FXML
    private Label label11;

    @FXML
    private Label label111;

    @FXML
    private Label label1111;

    @FXML
    private Label label11111;

    @FXML
    private Label label111111;

    @FXML
    private AnchorPane pane;

    @FXML
    private ImageView startButtonClicked;

    @FXML
    private Label title;

    public void initialize() {
    	Font f = Font.loadFont(getClass().getResourceAsStream("/resources/fonts/OCRExtendedV1.ttf"), 60);
    	title.setFont(f);
    	}
    @FXML
    void HomeBtnClicked(MouseEvent event) {
    	GlobalFuncations.switchScreen(pane,"MainScreen",(getClass().getResource("/view/" + "MainScreen" + ".fxml")),"");
    }

    @FXML
    void hoverEndSideButton(MouseEvent event) {
    	String img ="buttonconinter";
		GlobalFuncations.hoverButton(getClass().getResourceAsStream("/images/" + img + ".png"), homeBtn);		
    }

    @FXML
    void hoverStartSideButton(MouseEvent event) {
    	String img ="buttonconinterClicked";
		GlobalFuncations.hoverButton(getClass().getResourceAsStream("/images/" + img + ".png"), homeBtn);	
    }
    

    @FXML
    void BackBtnClicked(MouseEvent event) {
    	GlobalFuncations.switchScreen(pane,"Rules2Screen",(getClass().getResource("/view/" + "Rules2Screen" + ".fxml")),"");
    }
    
    
    
    @FXML
    void hoverStartSideButton2(MouseEvent event) {
    	String img ="buttonconinterClicked";
		GlobalFuncations.hoverButton(getClass().getResourceAsStream("/images/" + img + ".png"), backBtn);	
    }
    

    @FXML
    void hoverEndSideButton2(MouseEvent event) {
    	String img ="buttonconinter";
		GlobalFuncations.hoverButton(getClass().getResourceAsStream("/images/" + img + ".png"), backBtn);		
    }
    
}
