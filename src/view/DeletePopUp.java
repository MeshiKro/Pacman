package view;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import misc.GlobalFuncations;
import misc.JsonWriterEx;

public class DeletePopUp {

	@FXML
	private AnchorPane pane;

	@FXML
	private ImageView yesBtn;

	@FXML
	private ImageView startButtonClicked;

	@FXML
	private ImageView noBtn;

	// Hover Section

	@FXML
	void hoverStart(MouseEvent event) {
		setHover(event.getPickResult().getIntersectedNode().getId(), "Clicked");

	}

	private void setHover(String id, String isClicked) {
		if (id == null) {
			GlobalFuncations.hoverButton(getClass().getResourceAsStream("/images/" + "yesBtn" + ".png"), yesBtn);
			GlobalFuncations.hoverButton(getClass().getResourceAsStream("/images/" + "noBtn" + ".png"), noBtn);
			return;
		}
		// System.out.println(id);
		String img = "";
		ImageView imgToHover = null;

		if (id.contains("yes")) {
			img = "yesBtn" + isClicked;
			imgToHover = yesBtn;

		} else {
			img = "noBtn" + isClicked;
			imgToHover = noBtn;
		}

		GlobalFuncations.hoverButton(getClass().getResourceAsStream("/images/" + img + ".png"), imgToHover);
	}

	@FXML
	void HoverEnd(MouseEvent event) {
		setHover(event.getPickResult().getIntersectedNode().getId(), "");

	}

	// End Hover Section

	// OnClick Section

	@FXML
	void YesBtnClicked(MouseEvent event) {
		JsonWriterEx jw = new JsonWriterEx();
		jw.deleteQuestion();
		GlobalFuncations.switchScreen(pane, "QuestionsListScreen",
				(getClass().getResource("/view/" + "QuestionsListScreen" + ".fxml")), "");
	}

	@FXML
	void NoBtnClicked(MouseEvent event) {
		GlobalFuncations.switchScreen(pane, "QuestionsListScreen",
				(getClass().getResource("/view/" + "QuestionsListScreen" + ".fxml")), "");

	}

	// End OnClick Section

}
