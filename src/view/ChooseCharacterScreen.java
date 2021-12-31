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
	private ImageView Clicked;

	@FXML
	private ImageView startGameBtn;

	@FXML
	private ImageView left;

	@FXML
	private ImageView right;

	@FXML
	private ImageView pacImg;

	@FXML
	private Label pacName;

	private int characterNum;

	public void initialize() {
		characterNum = MainScreen.characterFlag;
		changeImg(characterNum);

	}

	@FXML
	void HoverEnd(MouseEvent event) {
		String img = "apply";
		GlobalFuncations.hoverButton(getClass().getResourceAsStream("/images/" + img + ".png"), startGameBtn);

	}

	@FXML
	void hoverStart(MouseEvent event) {
		String img = "applyHover";
		GlobalFuncations.hoverButton(getClass().getResourceAsStream("/images/" + img + ".png"), startGameBtn);
	}

	@FXML
	void startGame(MouseEvent event) {
		MainScreen.characterFlag = characterNum;
		GlobalFuncations.switchScreen(pane, "MainScreen", (getClass().getResource("/view/" + "MainScreen" + ".fxml")),
				"");
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

	@FXML
	void hoverStart1(MouseEvent event) {
		hover11(event.getPickResult().getIntersectedNode().getId());
	}

	@FXML
	void hoverStart2(MouseEvent event) {
		hover22(event.getPickResult().getIntersectedNode().getId());
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
	@FXML
	void leftClicked(MouseEvent event) {
		if (characterNum > 1) {
			characterNum--;
			changeImg(characterNum);
		} else {
			characterNum = 8;
			changeImg(characterNum);
		}
	}

	@FXML
	void rightClicked(MouseEvent event) {
		if (characterNum < 8) {
			characterNum++;
			changeImg(characterNum);

		} else {
			characterNum = 1;
			changeImg(characterNum);

		}
	}

	private void changeImg(int characterNum) {

		if (characterNum == 1) {
			Image Image = createImage("/pac_orig/basic");
			pacImg.setImage(Image);
			pacName.setText("pacman");
		}
		if (characterNum == 2) {
			Image Image = createImage("/pac_orig/pacWoman");
			pacImg.setImage(Image);
			pacName.setText("pacwoman");
		}
		if (characterNum == 3) {
			Image Image = createImage("/pac_orig/viking");
			pacImg.setImage(Image);
			pacName.setText("viking");
		}
		if (characterNum == 4) {
			Image Image = createImage("/pac_orig/pacPolice");
			pacImg.setImage(Image);
			pacName.setText("officer");
		}
		if (characterNum == 5) {
			Image Image = createImage("/pac_orig/santa");
			pacImg.setImage(Image);
			pacName.setText("santa");
		}
		if (characterNum == 6) {
			Image Image = createImage("/pac_orig/alien");
			pacImg.setImage(Image);
			pacName.setText("alien");
		}
		if (characterNum == 7) {
			Image Image = createImage("/pac_orig/angel");
			pacImg.setImage(Image);
			pacName.setText("angel");
		}
		if (characterNum == 8) {
			Image Image = createImage("/pac_orig/devil");
			pacImg.setImage(Image);
			pacName.setText("devil");
		}
	}

}
