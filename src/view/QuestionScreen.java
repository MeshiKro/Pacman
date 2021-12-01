package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

import misc.GlobalFuncations;
import javafx.scene.input.MouseEvent;

public class QuestionScreen {

	@FXML
	private AnchorPane pane;
	
    @FXML
    private Label label;

    @FXML
    private Label title;
	@FXML
	private AnchorPane homePane;

	@FXML
	private ImageView homeBtn;

	@FXML
	private ImageView startButtonClicked;

	@FXML
	private ImageView editDeleteBtn;

	@FXML
	private ImageView addBtn;

	public void initialize() {
	Font f = Font.loadFont(getClass().getResourceAsStream("/resources/fonts/OCRExtendedV1.ttf"), 24);
	label.setFont(f);
	f = Font.loadFont(getClass().getResourceAsStream("/resources/fonts/OCRExtendedV1.ttf"), 60);
	title.setFont(f);
	}
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

	
	@FXML
	void HoverEnd(MouseEvent event) {
		setHover((ImageView) event.getSource(),false);

	}
	
	@FXML
	void hoverStart(MouseEvent event) {
		setHover((ImageView) event.getSource(),true);
	   



	}

	private void setHover(ImageView imageView, boolean b) {
		    String img =getImage(imageView.idProperty().toString(),b);
		    GlobalFuncations.hoverButton(getClass().getResourceAsStream("/images/" + img + ".png"), imageView);		
	}
	private String getImage(String id, boolean b) {
		 String img ="";
		 String clicked = "Clicked";
		 if(!b)
			 clicked ="";
		  if(id.contains("add"))
		    	img ="addQuesiotnBtn" + clicked;
		    else
			     img ="editDeleteBtn"+ clicked;    	
		  return img;
	}

	

	// End Hover Section

	// OnClick Section


    @FXML
    void HomeBtnClicked(MouseEvent event) {
    	GlobalFuncations.switchScreen(pane,"MainScreen",(getClass().getResource("/view/" + "MainScreen" + ".fxml")),"");

    }
	
	@FXML
	void AddQuestionScreen(MouseEvent event) {
		GlobalFuncations.switchScreen(pane,"EditQuestionScreen",(getClass().getResource("/view/" + "EditQuestionScreen" + ".fxml")),"Add");

	}

	@FXML
	void updateQuestionScreen(MouseEvent event) {
		GlobalFuncations.switchScreen(pane,"QuestionsListScreen",(getClass().getResource("/view/" + "QuestionsListScreen" + ".fxml")),"");

	}
	// End OnClick Section

}
