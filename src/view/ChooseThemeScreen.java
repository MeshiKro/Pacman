package view;
import java.io.InputStream;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import misc.GlobalFuncations;
public class ChooseThemeScreen {


	    @FXML
	    private ImageView backBtn;

	    @FXML
	    private AnchorPane backBtnPane;

	    @FXML
	    private ImageView backicon;

	    @FXML
	    private Pane basicPane;

	    @FXML
	    private Pane candyPane;

	    @FXML
	    private ImageView left;

	    @FXML
	    private AnchorPane pane;

	    @FXML
	    private ImageView right;

	    @FXML
	    private ImageView startButtonClicked;

	    @FXML
	    private ImageView startGameBtn;

	    @FXML
	    private Pane zombiePane;
	    

		private int themeNum;
	    
		public void initialize() {
			if(MainScreen.theme.equals("Basic"))
				themeNum = 1;
			if(MainScreen.theme.equals("Candy Land"))
				themeNum = 2;
			if(MainScreen.theme.equals("Zombie Land"))
				themeNum = 3;
			changePane(themeNum);
				
		}

	    private void changePane(int themeNum2) {
			if(themeNum2==1) 
				basicPane.toFront();
			if(themeNum2==2) 
				candyPane.toFront();
			if(themeNum2==3) 
				zombiePane.toFront();
		}

		@FXML
	    void BackBtnClicked(MouseEvent event) {
	    	GlobalFuncations.switchScreen(pane, "MainScreen", (getClass().getResource("/view/" + "MainScreen" + ".fxml")),
					"");
	    }

	    @FXML
	    void HoverEnd(MouseEvent event) {
	    	String img = "startButton";
			GlobalFuncations.hoverButton(getClass().getResourceAsStream("/images/" + img + ".png"), startGameBtn);

	    }

	    @FXML
	    void hoverEnd1(MouseEvent event) {
	    	hover1(event.getPickResult().getIntersectedNode().getId());
	    }

	    @FXML
	    void hoverEnd2(MouseEvent event) {
	    	hover2(event.getPickResult().getIntersectedNode().getId());
	    }

	    @FXML
	    void hoverEndSideButton(MouseEvent event) {
	    	hoverSideButton(event.getPickResult().getIntersectedNode().getId());

	    }

	    @FXML
	    void hoverStart(MouseEvent event) {
	    	String img = "startButtonClicked";
			GlobalFuncations.hoverButton(getClass().getResourceAsStream("/images/" + img + ".png"), startGameBtn);

	    }

	    @FXML
	    void hoverStart1(MouseEvent event) {
	    	hover11(event.getPickResult().getIntersectedNode().getId());
	    }

	    @FXML
	    void hoverStart2(MouseEvent event) {
	    	hover22(event.getPickResult().getIntersectedNode().getId());
	    }

	    @FXML
	    void hoverStartSideButton(MouseEvent event) {
	    	hoverSideButton(event.getPickResult().getIntersectedNode().getId());
	    }

	    @FXML
	    void leftClicked(MouseEvent event) {
	    	if (themeNum > 1) {
				themeNum--;
				changePane(themeNum);
			} else {
				themeNum = 3;
				changePane(themeNum);
			}
	    }

	    @FXML
	    void rightClicked(MouseEvent event) {
	    	if (themeNum < 3) {
				themeNum++;
				changePane(themeNum);

			} else {
				themeNum = 1;
				changePane(themeNum);

			}
	    }

	    @FXML
	    void startGame(MouseEvent event) {
	    	if(themeNum == 1)
	    		MainScreen.theme="Basic";
	    	if(themeNum == 2)
	    		MainScreen.theme="Candy Land";
	    	if(themeNum == 3)
	    		MainScreen.theme="Zombie Land";
			GlobalFuncations.switchScreen(pane, "MainScreen", (getClass().getResource("/view/" + "MainScreen" + ".fxml")),
					"");

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
		
		private void hover1(String id) {
			if (id == null) {
				Image image = createImage("1");
				right.setImage(image);
				return;
			}
			Image image = createImage("11");
			switch (id) {
			case "right":
				right.setImage(image);
				break;

			default:
				image = createImage("1");
				right.setImage(image);

			}
		}

		private void hover2(String id) {
			if (id == null) {
				Image image = createImage("23");
				left.setImage(image);
				return;
			}
			Image image = createImage("22");

			switch (id) {

			case "left":
				left.setImage(image);
				break;

			default:
				image = createImage("23");
				left.setImage(image);

			}

		}
		private void hover11(String id) {
			if (id == null) {
				Image image = createImage("1");
				right.setImage(image);
				return;
			}
			Image image = createImage("11");
			switch (id) {
			case "right":
				right.setImage(image);
				break;

			default:
				image = createImage("1");
				right.setImage(image);

			}
		}

		private void hover22(String id) {
			if (id == null) {
				Image image = createImage("23");
				left.setImage(image);
				return;
			}

			Image image = createImage("22");

			switch (id) {

			case "left":
				left.setImage(image);
				break;

			default:
				image = createImage("23");
				left.setImage(image);

			}

		}

}
