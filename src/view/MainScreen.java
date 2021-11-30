package view;

import java.io.InputStream;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import misc.Global;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class MainScreen {

	@FXML
	private AnchorPane pane;

	@FXML
	private ImageView startGameBtn;

	@FXML
	private ComboBox<String> themeField;

	@FXML
	private ImageView startButtonClicked;

	@FXML
	private AnchorPane rulesPane;

	@FXML
	private AnchorPane qaPane;

	@FXML
	private AnchorPane mutePane;

	@FXML
	private ImageView rulesBtn;

	@FXML
	private ImageView QABtn;

	@FXML
	private AnchorPane boardPane;

	@FXML
	private ImageView muteBtn;

	@FXML
	private ImageView boardBtn;

	@FXML
	private TextField nameFeild;

	public void initialize() {
		themeField.getItems().addAll("Basic", "Candy Land", "Zombie Land");
		themeField.getSelectionModel().select(0);
	}

	// Hover Section
	@FXML
	void HoverEnd(MouseEvent event) {
		String img ="startButton";
		Global.hoverButton(getClass().getResourceAsStream("/images/" + img + ".png"), startGameBtn);		

	}

	@FXML
	void hoverStart(MouseEvent event) {
		String img ="startButtonClicked";
		Global.hoverButton(getClass().getResourceAsStream("/images/" + img + ".png"), startGameBtn);		
	}

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
		case "rules":
			rulesBtn.setImage(image);
			break;
		case "qa":
			QABtn.setImage(image);
			break;
		case "mute":
			muteBtn.setImage(image);
			break;
		case "board":
			boardBtn.setImage(image);
			break;
		default:
			image = createImage("buttonconinter");
			rulesBtn.setImage(image);
			QABtn.setImage(image);
			muteBtn.setImage(image);
			boardBtn.setImage(image);
		}

	}

	private void hoverStartButton(String img) {
		startGameBtn.setImage(createImage(img));
	}

	private Image createImage(String img) {
		InputStream inStream = getClass().getResourceAsStream("/images/" + img + ".png");
		return new Image(inStream);
	}

	// End Hover Section

	
	
	
	// OnClick Section//

	@FXML
	void startGame(MouseEvent event) {

		PacWindow pw = new PacWindow();
		Stage stage = (Stage) startGameBtn.getScene().getWindow();
		stage.close();
	}
	

    @FXML
    void QuestionWizardBtnClicked(MouseEvent event) {
    	Global.switchScreen(pane,"QuestionScreen",(getClass().getResource("/view/" + "QuestionScreen" + ".fxml")));
    }
    
    
    
    @FXML
    void ScoreBoardButtonClicked(MouseEvent event) {
    	Global.switchScreen(pane,"ScoreBoardScreen",(getClass().getResource("/view/" + "ScoreBoardScreen" + ".fxml")));
    	
    }


	
	// End OnClick Section

}
