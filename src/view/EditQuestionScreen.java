package view;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import misc.Global;

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
	private ComboBox<?> themeField;

	@FXML
	private Text title;

	public void initialize(String screenType) {
		
		Font f = Font.loadFont(getClass().getResourceAsStream("/resources/fonts/OCRExtendedV1.ttf"), 60);
		title.setFont(f);
		title.setText(screenType + " Question");
		}
	
	// Hover Section


	@FXML
	void hoverStartSideButton(MouseEvent event) {
		String img ="buttonconinterClicked";
		Global.hoverButton(getClass().getResourceAsStream("/images/" + img + ".png"), homeBtn);		

	}
	
	@FXML
	void hoverEndSideButton(MouseEvent event) {
		String img ="buttonconinter";
		Global.hoverButton(getClass().getResourceAsStream("/images/" + img + ".png"), homeBtn);		
	}

	// End Hover Section

	// OnClick Section//

	@FXML
	void HomeBtnClicked(MouseEvent event) {
    	Global.switchScreen(pane,"MainScreen",(getClass().getResource("/view/" + "MainScreen" + ".fxml")),"");

	}
	// End OnClick Section

}
