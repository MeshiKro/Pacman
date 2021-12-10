package view;

import java.io.InputStream;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import misc.GlobalFuncations;
import misc.JsonRead;
import model.QuestionInJson;

public class QuestionsListScreen {

	@FXML
	private ImageView backBtn;

	@FXML
	private AnchorPane backBtnPane;

	@FXML
	private ImageView backicon;

	@FXML
	private ImageView deleteBtn1;

	@FXML
	private ImageView deleteBtn2;

	@FXML
	private ImageView deleteBtn3;

	@FXML
	private ImageView deleteBtn4;

	@FXML
	private ImageView deleteBtn5;

	@FXML
	private ImageView deleteBtn6;

	@FXML
	private ImageView editBtn1;

	@FXML
	private ImageView editBtn2;

	@FXML
	private ImageView editBtn3;

	@FXML
	private ImageView editBtn4;

	@FXML
	private ImageView editBtn5;

	@FXML
	private ImageView editBtn6;

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
	private Text levleField1;

	@FXML
	private Text levleField2;

	@FXML
	private Text levleField3;

	@FXML
	private Text levleField4;

	@FXML
	private Text levleField5;

	@FXML
	private Text levleField6;

	@FXML
	private AnchorPane nextBtnPane;

	@FXML
	private ImageView nextIcon1;

	@FXML
	private ImageView nextIcon2;

	@FXML
	private AnchorPane pane;

	@FXML
	private Text quesionField1;

	@FXML
	private Text quesionField2;

	@FXML
	private Text quesionField3;

	@FXML
	private Text quesionField4;

	@FXML
	private Text quesionField5;

	@FXML
	private Text quesionField6;


    @FXML
    private ImageView backiquestioncon1;

    @FXML
    private ImageView backquestionBtn1;

    @FXML
    private AnchorPane backquestionBtnPane1;
	
	private int quesiotnIndex;

	public void initialize() {
		JsonRead jr = new JsonRead();
		ArrayList<QuestionInJson> array = jr.readQuestionsFromJson();
		quesiotnIndex = 6;
		System.out.println(" array " + array.size());
		if(array.size() >6)
		{
			nextBtnPane.setVisible(true);
			backquestionBtnPane1.setVisible(true);
		}else
		{
			nextBtnPane.setVisible(false);
			backquestionBtnPane1.setVisible(false);
		}
		
		for (int i = 0; i < quesiotnIndex; i++) {

			addQuestion(array, i);
		}

	}

	void addQuestion(ArrayList<QuestionInJson> array, int index) {
		System.out.println(array.get(index).getQuestion());

		
		Text field = findText("quesionField" + String.valueOf(index + 1));
		field.setText(array.get(index).getQuestion());
		field.setVisible(true);
		
		
		field = findText("levleField" + String.valueOf(index + 1));
		field.setText(getLevel(array.get(index).getLevel()));
		field.setVisible(true);

		
		ImageView img = findImageView("editBtn" + String.valueOf(index + 1));
		img.setVisible(true);

		img = findImageView("deleteBtn" + String.valueOf(index + 1));
		img.setVisible(true);

	}

	private ImageView findImageView(String id) {

		for (int i = 0; i < pane.getChildren().size(); i++) {
			Node current = pane.getChildren().get(i);
			if (current.getId() != null && current.getId().equals(id)) {
				return (ImageView) current;
			}
		}
		return null;
	}

	private Text findText(String id) {

		for (int i = 0; i < pane.getChildren().size(); i++) {
			Node current = pane.getChildren().get(i);
			if (current.getId() != null && current.getId().equals(id)) {
				return (Text) current;
			}
		}
		return null;
	}

	private String getLevel(String level) {
		String lvl = "";

		switch (level) {
		case "1":
			lvl = "Easy";
			break;
		case "2":
			lvl = "Meduim";
			break;
		case "3":
			lvl = "Hard";
			break;
		}
		return lvl;

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
		if (id == null)
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
		GlobalFuncations.switchScreen(pane, "MainScreen", (getClass().getResource("/view/" + "MainScreen" + ".fxml")),
				"");

	}

	@FXML
	void BackBtnClicked(MouseEvent event) {
		GlobalFuncations.switchScreen(pane, "QuestionScreen",
				(getClass().getResource("/view/" + "QuestionScreen" + ".fxml")), "");

	}

	@FXML
	void nextQuesiotnBtnClicked(MouseEvent event) {

	}

	@FXML
	void BackQuestionBtnClicked(MouseEvent event) {

	}

	// End OnClick Section

}
