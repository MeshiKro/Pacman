package view;

import java.io.InputStream;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import misc.GlobalFuncations;

public class QuestionsListScreen {


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
    private AnchorPane pane;

    @FXML
    private ImageView startButtonClicked;

    @FXML
    private Label title;

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

	// OnClick Section

	@FXML
	void HomeBtnClicked(MouseEvent event) {
    	GlobalFuncations.switchScreen(pane,"MainScreen",(getClass().getResource("/view/" + "MainScreen" + ".fxml")),"");

	}

    @FXML
    void BackBtnClicked(MouseEvent event) {
    	GlobalFuncations.switchScreen(pane,"QuestionScreen",(getClass().getResource("/view/" + "QuestionScreen" + ".fxml")),"");

    }
	
	// End OnClick Section

}
