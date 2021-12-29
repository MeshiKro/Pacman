package view;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import misc.GlobalFuncations;
import misc.JsonRead;
import misc.JsonWriterEx;
import model.QuestionInJson;

public class QuestionsListScreen {
	

    @FXML
    private ComboBox<String> filterBy;

    @FXML
    private ComboBox<String> orderBy;

	@FXML
	private ImageView backBtn;

	@FXML
	private AnchorPane backBtnPane;

	@FXML
	private ImageView backicon;

	@FXML
	private ImageView chartBtn1;

	@FXML
	private ImageView chartBtn2;

	@FXML
	private ImageView chartBtn3;

	@FXML
	private ImageView chartBtn4;

	@FXML
	private ImageView chartBtn5;

	@FXML
	private ImageView chartBtn6;

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
	private ImageView questionSection;

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

	ArrayList<QuestionInJson> array;
   public static String questionString;

	public static String questionToDelete;

	public static String questionToEdit;

	int page =1;
	
	int minPage1Index =0;
	
	int maxPage1Index=6;
	

	int minPage2Index =6;
	
	int maxPage2Index=12;
	
	

	int minPage3Index =12;
	
	int maxPage3Index=18;
	
	public int QuestionOnScreenIndex =1;
	public void initialize() {
		JsonRead jr = new JsonRead();
		array = jr.readQuestionsFromJson();

		maxPage3Index = array.size();
		setNextAndBackBtn(array.size());

		for (int i = minPage1Index; i < maxPage1Index; i++) {
			try {
				addQuestion(array, i);

			} catch (IndexOutOfBoundsException e) {

			}
		}
		
		if(QuestionOnScreenIndex != 1)
		{
			while(QuestionOnScreenIndex != 1)
			clearPrevoiusQuestion();
		}
		filterBy.getItems().addAll("Easy", "Medium", "Hard");
		filterBy.getSelectionModel().select(0);
		filterBy.setValue("");
		

	}

	private void clearPrevoiusQuestion() {

		// Find question / level field
				Text field = findText("quesionField" + String.valueOf(QuestionOnScreenIndex));
				if (field != null)
					field.setVisible(false);

				field = findText("levleField" + String.valueOf(QuestionOnScreenIndex));
				if (field != null)
					field.setVisible(false);

				// find edit / delete icon
				ImageView img = findImageView("editBtn" + String.valueOf(QuestionOnScreenIndex));
				if (img != null)
					img.setVisible(false);

				img = findImageView("deleteBtn" + String.valueOf(QuestionOnScreenIndex));
				if (img != null)
					img.setVisible(false);
		
				

				// find Chart icon
				img = findImageView("chartBtn" + String.valueOf(QuestionOnScreenIndex));
				if (img != null)
				img.setVisible(false);
				
				if(QuestionOnScreenIndex ==6)
					QuestionOnScreenIndex=1;
				else
				QuestionOnScreenIndex++;
	}

	private void setNextAndBackBtn(int size) {
		if (size > 6) {
			nextIcon2.setVisible(true);
			backquestionBtn1.setVisible(true);
		} else {
			nextIcon2.setVisible(false);
			backquestionBtn1.setVisible(false);
		}
	}

	// Add question to Screen
	void addQuestion(ArrayList<QuestionInJson> array, int index) {

		if(array.get(index)==null) {
			return;
		}
		// Find question / level field
		Text field = findText("quesionField" + String.valueOf(QuestionOnScreenIndex));
		field.setText(array.get(index).getQuestion());
		field.setVisible(true);

		field = findText("levleField" + String.valueOf(QuestionOnScreenIndex));
		field.setText(getLevel(array.get(index).getLevel()));
		field.setVisible(true);

		// find edit / delete icon
		ImageView img = findImageView("editBtn" + String.valueOf(QuestionOnScreenIndex));
		img.setVisible(true);

		img = findImageView("deleteBtn" + String.valueOf(QuestionOnScreenIndex));
		img.setVisible(true);
		
		// find Chart icon
		img = findImageView("chartBtn" + String.valueOf(QuestionOnScreenIndex));
		img.setVisible(true);
		
		
		if(QuestionOnScreenIndex ==6)
			QuestionOnScreenIndex=1;
		else
		QuestionOnScreenIndex++;
	}

	// Remove question from Screen
	private void removeQuestion(ArrayList<QuestionInJson> array, int index) {


		// Find question / level field
		Text field = findText("quesionField" + String.valueOf(QuestionOnScreenIndex));
		if (field != null)
			field.setVisible(false);

		field = findText("levleField" + String.valueOf(QuestionOnScreenIndex));
		if (field != null)
			field.setVisible(false);

		// find edit / delete icon
		ImageView img = findImageView("editBtn" + String.valueOf(QuestionOnScreenIndex));
		if (img != null)
			img.setVisible(false);

		img = findImageView("deleteBtn" + String.valueOf(QuestionOnScreenIndex));
		if (img != null)
			img.setVisible(false);
		
		// find Chart icon
		img = findImageView("chartBtn" + String.valueOf(QuestionOnScreenIndex));
		if (img != null)
		img.setVisible(false);
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
	
	//Add Question according to it's level
	@FXML
	void filterQByLevel(MouseEvent event) {
		String difficulty = filterBy.getSelectionModel().getSelectedItem();
		JsonRead jr = new JsonRead();
		array = jr.readQuestionsFromJson();
		System.out.println(difficulty);
		if (difficulty.equals("Easy")) {
			for (int i = 0; i <array.size(); i++) {
				if(getLevel(array.get(i).getLevel()).equals("Easy")) {
					System.out.println("................."+getLevel(array.get(i).getLevel()));
				try {
					addQuestion(array, i);

				} catch (IndexOutOfBoundsException e) {

				}
			}

		}
			
		} if (difficulty.equals("Medium")) {
				for (int i = 0; i < array.size(); i++) {
					if(getLevel(array.get(i).getLevel()).equals("Meduim")) {
						System.out.println("................."+getLevel(array.get(i).getLevel()));
					try {
						addQuestion(array, i);

					} catch (IndexOutOfBoundsException e) {

					}
				}
			}
			
		}if (difficulty.equals("Hard")) {
			
				for (int i = 0; i < array.size(); i++) {
					if(getLevel(array.get(i).getLevel()).equals("Hard")) {
					try {
						addQuestion(array, i);

					} catch (IndexOutOfBoundsException e) {

					}
				}
			}
		}	
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

		int min =0;
		int max=0;
		page++;
		System.out.println("page = "  + page);
		if(page ==1)
		{
			QuestionOnScreenIndex=1;

			min=minPage1Index;
			max=maxPage1Index;
		}
		if(page ==2)
		{
			QuestionOnScreenIndex=1;

			min=minPage2Index;
			max=maxPage2Index;
			
		}
		if(page ==3)
		{
			QuestionOnScreenIndex=1;

			min=minPage3Index;
			max=maxPage3Index;
		}
		System.out.print("nextQuesiotnBtnClicked  minIndex " + min);
		System.out.print(" nextQuesiotnBtnClicked  max " + max);
		for (int i=min; i < max; i++) {
		
			try {
				addQuestion(array, i);
			} catch (IndexOutOfBoundsException e) {
				//removeQuestion(array, i);

			}
		}
		
		if(QuestionOnScreenIndex != 1 && page ==3)
		{
			while(QuestionOnScreenIndex != 1)
			clearPrevoiusQuestion();
		}

	}

	@FXML
	void BackQuestionBtnClicked(MouseEvent event) {
		
		int min =0;
		int max=0;
		page--;
		System.out.println("page = "  + page);

		if(page ==1)
		{
			QuestionOnScreenIndex=1;
			min=minPage1Index;
			max=maxPage1Index;
		}
		if(page ==2)
		{
			QuestionOnScreenIndex=1;
			min=minPage2Index;
			max=maxPage2Index;
			
		}
		
		if(page ==3)
		{
			QuestionOnScreenIndex=1;
			min=minPage3Index;
			max=maxPage3Index;
		}
		System.out.print(" minIndex " + min);
		System.out.print(" max " + max);
		for (int i = min; i < max; i++) {
			try {
				addQuestion(array, i);
			} catch (IndexOutOfBoundsException e) {
				//removeQuestion(array, i);

			}
		}
		
		
		if(QuestionOnScreenIndex != 1 && page ==3)
		{
			while(QuestionOnScreenIndex != 1)
			clearPrevoiusQuestion();
		}
		

	}

	@FXML
	void deleteQuestionClicked(MouseEvent event) {
		String id = event.getPickResult().getIntersectedNode().getId().substring(9);

		Text field = findText("quesionField" + id);
		if (field != null) {
			questionToDelete = field.getText();

			GlobalFuncations.switchScreen(pane, "DeletePopUp",
					(getClass().getResource("/view/" + "DeletePopUp" + ".fxml")), "");

		}

	}

	@FXML
	void EditQuestionClicked(MouseEvent event) {
		String id = event.getPickResult().getIntersectedNode().getId().substring(7);
		System.out.print(" id " + id);
		Text field = findText("quesionField" + id);
		if (field != null) {
			questionToEdit = field.getText();
			questionString = questionToEdit;

			GlobalFuncations.switchScreen(pane, "EditQuestionScreen",
					(getClass().getResource("/view/" + "EditQuestionScreen" + ".fxml")), "Edit");

		}
	}

	@FXML
	void goToChartScreem(MouseEvent event) {
		if( event.getPickResult().getIntersectedNode().getId().equals(""))
			return;
		
		try {
		String id = event.getPickResult().getIntersectedNode().getId().substring(8);
		Text field = findText("quesionField" + id);

		if (field != null) {
			GlobalFuncations.switchScreen(pane, "ChartScreen",
					(getClass().getResource("/view/" + "ChartScreen" + ".fxml")), field.getText());

		}
		}
		catch(Exception e)
		{
			
		}
	}

	@FXML
	void hoverStart(MouseEvent event) {
		hover1(event.getPickResult().getIntersectedNode().getId());
	}

	@FXML
	void hoverStart2(MouseEvent event) {
		hover2(event.getPickResult().getIntersectedNode().getId());
	}

	@FXML
	void hoverEnd(MouseEvent event) {
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
			nextIcon2.setImage(image);
			break;

		default:
			image = createImage("1");
			nextIcon2.setImage(image);

		}
	}

	private void hover2(String id) {
		if (id == null)
			return;
		Image image = createImage("22");

		switch (id) {

		case "backquestionBtn1":
			backquestionBtn1.setImage(image);
			break;

		default:
			image = createImage("23");
			backquestionBtn1.setImage(image);

		}

	}

	// End OnClick Section

}
