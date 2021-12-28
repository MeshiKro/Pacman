package view;
import java.io.InputStream;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import misc.GlobalFuncations;

public class ChooseCharacterScreen {

	    @FXML
	    private AnchorPane pane;

	    @FXML
	    private ImageView backBtn;

	    @FXML
	    private AnchorPane backBtnPane;

	    @FXML
	    private ImageView backicon;

	    @FXML
	    private ImageView startButtonClicked;

	    @FXML
	    private ImageView startGameBtn;
	    
	    @FXML
	    private ImageView left;

	    @FXML
	    private ImageView right;

	    @FXML
	    void HoverEnd(MouseEvent event) {
	    	String img = "startButton";
			GlobalFuncations.hoverButton(getClass().getResourceAsStream("/images/" + img + ".png"), startGameBtn);

	    }

	    @FXML
	    void hoverStart(MouseEvent event) {
	    	String img = "startButtonClicked";
			GlobalFuncations.hoverButton(getClass().getResourceAsStream("/images/" + img + ".png"), startGameBtn);
		}


	    @FXML
	    void startGame(MouseEvent event) {
	    	GlobalFuncations.switchScreen(pane, "MainScreen",
					(getClass().getResource("/view/" + "MainScreen" + ".fxml")), "");
	    }
	    
	    @FXML
	    void hoverEndSideButton(MouseEvent event) {
	    	hoverSideButton(event.getPickResult().getIntersectedNode().getId());
	    }
	    

	    @FXML
	    void hoverStartSideButton(MouseEvent event) {
	    	hoverSideButton(event.getPickResult().getIntersectedNode().getId());
	    }

	    @FXML
	    void BackBtnClicked(MouseEvent event) {
	    	GlobalFuncations.switchScreen(pane, "MainScreen",
					(getClass().getResource("/view/" + "MainScreen" + ".fxml")), "");
	    }

	    
		private void hoverSideButton(String id) {
			if (id == null)
				return;
			Image image = createImage("buttonconinterClicked");
			id = id.replace("Btn", "").replace("Pane", "");

			switch (id) {
			case "back":
				backBtn.setImage(image);
				break;
			default:
				image = createImage("buttonconinter");
				backBtn.setImage(image);

			}

		}
		
		private Image createImage(String img) {
			InputStream inStream = getClass().getResourceAsStream("/images/" + img + ".png");
			return new Image(inStream);
		}
		
		

	    @FXML
	    void hoverStart1(MouseEvent event) {
	    	hover1(event.getPickResult().getIntersectedNode().getId());
	    }

	    @FXML
	    void hoverStart2(MouseEvent event) {
	    	hover2(event.getPickResult().getIntersectedNode().getId());
	    }


	    @FXML
	    void hoverEnd1(MouseEvent event) {
	    	hover1(event.getPickResult().getIntersectedNode().getId());
	    }

	    @FXML
	    void hoverEnd2(MouseEvent event) {
	    	hover2(event.getPickResult().getIntersectedNode().getId());
	    }
	    
		private void hover1(String id) {
			if (id == null)
				return;
			Image image = createImage("11");

			switch (id) {
			case "nextIcon2":
				right.setImage(image);
				break;

			default:
				image = createImage("1");
				right.setImage(image);

			}
		}

		private void hover2(String id) {
			if (id == null)
				return;
			Image image = createImage("22");

			switch (id) {

			case "backquestionBtn1":
				left.setImage(image);
				break;

			default:
				image = createImage("23");
				left.setImage(image);

			}

		}
		
	    @FXML
	    void leftClicked(MouseEvent event) {

	    }

	    @FXML
	    void rightClicked(MouseEvent event) {

	    }

	}



