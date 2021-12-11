package view;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.json.JSONArray;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import misc.GlobalFuncations;
import misc.JsonRead;
import misc.JsonWriterEx;
import model.QuestionInJson;

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
	private ComboBox<String> levelField;

	@FXML
	private TextField questionField;

	@FXML
	private TextField correctAnsField;

	@FXML
	private TextField wrongAns1;
	@FXML
	private Label errorLabel;
	@FXML
	private TextField wrongAns2;

	@FXML
	private TextField wrongAns3;
	@FXML
	private ImageView saveBtn;
	@FXML
	private ImageView addBtn;
	@FXML
	private ImageView backBtn;

	@FXML
	private ComboBox<String> themeField;

	@FXML
	private Text title;

	private String type;

	public void initialize(String screenType) {

		Font f = Font.loadFont(getClass().getResourceAsStream("/resources/fonts/OCRExtendedV1.ttf"), 60);
		title.setFont(f);
		title.setText(screenType + " Question");

		if (screenType.equals("Add")) {
			addBtn.setVisible(true);
			saveBtn.setVisible(false);

		} else {
			addBtn.setVisible(false);
			saveBtn.setVisible(true);
			addQuestionToScreen();
		}
		type = screenType;

		levelField.setItems(FXCollections.observableArrayList(new String[] { "1", "2", "3" }));
		levelField.setValue("1");
	}

	private void addQuestionToScreen() {
		JsonRead jr = new JsonRead();
		QuestionInJson q = jr.getQuestionFromJson();

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

	// OnClick Section//

	@FXML
	void HomeBtnClicked(MouseEvent event) {
		GlobalFuncations.switchScreen(pane, "MainScreen", (getClass().getResource("/view/" + "MainScreen" + ".fxml")),
				"");

	}

	@FXML
	void BackBtnClicked(MouseEvent event) {
		GlobalFuncations.switchScreen(pane, "QuestionsListScreen",
				(getClass().getResource("/view/" + "QuestionsListScreen" + ".fxml")), "");

	}

	@FXML
	void writeQuestion(MouseEvent event) {

		boolean fieldsAreEmpty = validateAllFieldsAreFilled();
		if (fieldsAreEmpty) {
			errorLabel.setText("*you must fill all fields");
			return;
		}
		errorLabel.setText("");

		if (type.equals("Add")) {

			QuestionInJson q = createJsonQuestion();
			JsonWriterEx jw = new JsonWriterEx();
			boolean res = jw.writeQuestions(q);
			System.out.println(res);
			if (res)
				GlobalFuncations.switchScreen(pane, "ConfirmPopUp",
						(getClass().getResource("/view/" + "ConfirmPopUp" + ".fxml")), "");
		}
	}

	private QuestionInJson createJsonQuestion() {
		JsonWriterEx jw = new JsonWriterEx();

		String question = questionField.getText();
		String correctAns = correctAnsField.getText();
		String level = levelField.getSelectionModel().getSelectedItem();
		String[] answerList = createAnswerList(correctAnsField.getText(), wrongAns1.getText(), wrongAns2.getText(),
				wrongAns3.getText());
		String CorrectAnsIndex = getCorrectAnsIndex(answerList, correctAns);
		QuestionInJson q = jw.createNewQuestion(question, answerList, CorrectAnsIndex, level);
		return q;
	}

	private boolean validateAllFieldsAreFilled() {

		if (checkIfFieldsIsEmpty(questionField) || checkIfFieldsIsEmpty(correctAnsField)
				|| checkIfFieldsIsEmpty(wrongAns1) || checkIfFieldsIsEmpty(wrongAns2)
				|| checkIfFieldsIsEmpty(wrongAns3))
			return true;
		return false;

	}

	private boolean checkIfFieldsIsEmpty(TextField textField) {
		return (textField.getText().length() == 0);
	}

	private String getCorrectAnsIndex(String[] answerList, String correctAns) {

		for (int i = 0; i < 4; i++) {
			if (answerList[i].toString().trim().equals(correctAns.toString().trim()))
				return String.valueOf(i + 1);
		}
		return null;
	}

	private String[] createAnswerList(String correctAns, String wrongAnswer1, String wrongAnswer2,
			String wrongAnswer3) {
		String[] a = new String[4];
		a[0] = (correctAns);
		a[1] = (wrongAnswer1);
		a[2] = (wrongAnswer2);
		a[3] = (wrongAnswer3);

		// Shuffle Array:
		List<String> strList = Arrays.asList(a);
		Collections.shuffle(strList);
		a = strList.toArray(new String[strList.size()]);
		return a;
	}

	// End OnClick Section

}
