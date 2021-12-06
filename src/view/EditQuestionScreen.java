package view;

import java.io.InputStream;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import misc.GlobalFuncations;

public class EditQuestionScreen {

	@FXML
	private ImageView homeBtn;

	@FXML
	private AnchorPane homePane;

	@FXML
	private TextField nameFeild;

	@FXML
	private TextField nameFeild1;

	@FXML
	private TextField nameFeild2;

	@FXML
	private TextField nameFeild3;

	@FXML
	private TextField nameFeild4;

	@FXML
	private AnchorPane pane;
	

	
    @FXML
    private ImageView saveBtn;
    
	@FXML
	private ImageView backBtn;
	
	@FXML
	private ComboBox<String> themeField;

	@FXML
	private Text title;

	public void initialize(String screenType) {

		Font f = Font.loadFont(getClass().getResourceAsStream("/resources/fonts/OCRExtendedV1.ttf"), 60);
		title.setFont(f);
		title.setText(screenType + " Question");
		if(screenType.equals("Add"))
		{
			saveBtn.setVisible(true);
			saveBtn.setVisible(false);

		}else
		{
			saveBtn.setVisible(false);
			saveBtn.setVisible(true);
		}
	}

	// Hover Section

	@FXML
	void hoverStartSideButton(MouseEvent event) {
		hoverSideButton(event.getPickResult().getIntersectedNode().getId());

	}

	@FXML
	void hoverEndSideButton(MouseEvent event) {
		hoverSideButton(event.getPickResult().getIntersectedNode().getId());

	}

	private void hoverSideButton(String id) {
		if(id == null)
			return;
		Image image = createImage("buttonconinterClicked");
		id = id.replace("Btn", "").replace("Pane", "");

		switch (id) {
		case "home":
			homeBtn.setImage(image);
			break;
		case "back":
			backBtn.setImage(image);
			break;		
		default:
			image = createImage("buttonconinter");
			homeBtn.setImage(image);
			backBtn.setImage(image);
			
		}

	}

	private Image createImage(String img) {
		InputStream inStream = getClass().getResourceAsStream("/images/" + img + ".png");
		return new Image(inStream);
	}
	// End Hover Section

	// OnClick Section//

	@FXML
	void HomeBtnClicked(MouseEvent event) {
		GlobalFuncations.switchScreen(pane, "MainScreen", (getClass().getResource("/view/" + "MainScreen" + ".fxml")),
				"");

	}
	 @FXML
	    void BackBtnClicked(MouseEvent event) {
	    	GlobalFuncations.switchScreen(pane,"QuestionScreen",(getClass().getResource("/view/" + "QuestionScreen" + ".fxml")),"");

	    }
	// End OnClick Section

}
