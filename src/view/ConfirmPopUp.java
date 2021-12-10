package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import misc.GlobalFuncations;

public class ConfirmPopUp {

	@FXML
	private AnchorPane pane;

	@FXML
	private ImageView startButtonClicked;

	@FXML
	private ImageView okBtn;

	@FXML
	private Label meg;

	// Hover Section
	
	@FXML
	void hoverStart(MouseEvent event) {
		GlobalFuncations.hoverButton(getClass().getResourceAsStream("/images/" + "okBtnClicked" + ".png"), okBtn);

	}

	@FXML
	void HoverEnd(MouseEvent event) {
		GlobalFuncations.hoverButton(getClass().getResourceAsStream("/images/" + "okBtn" + ".png"), okBtn);

	}

	// End Hover Section

	
	// OnClick Section
	@FXML
	void OkBtnClicked(MouseEvent event) {
    	GlobalFuncations.switchScreen(pane,"QuestionScreen",(getClass().getResource("/view/" + "QuestionScreen" + ".fxml")),"");

	}

	// End OnClick Section

}
