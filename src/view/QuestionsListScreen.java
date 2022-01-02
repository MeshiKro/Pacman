package view;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.json.JSONException;

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

		for (int i = 0; i < 6; i++) {
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
		filterBy.setOnAction((event) -> {
		    int selectedIndex = filterBy.getSelectionModel().getSelectedIndex();
		    String selectedItem = filterBy.getSelectionModel().getSelectedItem();
			if (selectedItem.equals("Easy")) {
				 ArrayList<QuestionInJson> filteredEasy = null;
				 try {
					 filteredEasy = new ArrayList<>();
				        for (int i = 0; i < array.size(); ++i) {
				            QuestionInJson obj = array.get(i);
				            String id = obj.getLevel();
				            if (id.equals("1")) {
				            	filteredEasy.add(obj);
				            }
				        }
				    } catch (JSONException e) {
				        // handle exception
				    }

				 if(filteredEasy.size() %6 != 0) {
					 
					 	for(int i=1;i<7;i++)
					 clearQuestion(i);
					 

				 }
				  QuestionOnScreenIndex =1;
				  setNextAndBackBtn(filteredEasy.size());

				  
				  
				for (int i = 0; i <filteredEasy.size(); i++) {

					try {
						addQuestion(filteredEasy, i);

					} catch (IndexOutOfBoundsException e) {

					}
	
				}
	
			}	 if (selectedItem.equals("Medium")) {
					 ArrayList<QuestionInJson> filteredMedium = null;
				 try {
					 filteredMedium = new ArrayList<>();
				        for (int i = 0; i < array.size(); ++i) {
				            QuestionInJson obj = array.get(i);
				            String id = obj.getLevel();
				            if (id.equals("2")) {
				            	filteredMedium.add(obj);
				            }
				        }
				    } catch (JSONException e) {
				        // handle exception
				    }

				 if(filteredMedium.size() %6 != 0) {
					 
					 	for(int i=1;i<7;i++)
					 clearQuestion(i);
					 

				 }
				  setNextAndBackBtn(filteredMedium.size());
				  
				 
				  QuestionOnScreenIndex =1;
				for (int i = 0; i <filteredMedium.size(); i++) {

					try {
						addQuestion(filteredMedium, i);

					} catch (IndexOutOfBoundsException e) {

					}
	
				}
				
			}if (selectedItem.equals("Hard")) {
				
					ArrayList<QuestionInJson> filteredHard = null;
				 try {
					 filteredHard = new ArrayList<>();
				        for (int i = 0; i < array.size(); ++i) {
				            QuestionInJson obj = array.get(i);
				            String id = obj.getLevel();
				            if (id.equals("3")) {
				            	filteredHard.add(obj);
				            }
				        }
				    } catch (JSONException e) {
				        // handle exception
				    }
				 if(filteredHard.size() %6 != 0) {
					 
					 	for(int i=1;i<7;i++)
					 clearQuestion(i);
					 

				 }
				
				  QuestionOnScreenIndex =1;
				  setNextAndBackBtn(filteredHard.size());
				for (int i = 0; i <filteredHard.size(); i++) {

					try {
						addQuestion(filteredHard, i);

					} catch (IndexOutOfBoundsException e) {

					}
	
				}
		}});
			
		

	}
	
	private void clearAllQuestion(int p) {
		page=p;
	int min =getMinIndexOfPage(p);
	int max=min+6;
	if(max >array.size())
		max = array.size();
	
	
	for (int i=min; i < max; i++) {
	
		try {
			clearPrevoiusQuestion();
		} catch (IndexOutOfBoundsException e) {
			

		}
	}
	
	
	}


	private int getMinIndexOfPage(int p) {
		int min=0;
		if(p==1)
		return min;
		int tempPage =1;
		while(tempPage <p)
		{
			min+=6;
			tempPage++;
		}
		
		/*if(page ==1)
		{
			

			min=0;
			//max=maxPage1Index;
		}
		if(page ==2)
		{

			min=6;
			//max=maxPage2Index;
			
		}
		if(page ==3)
		{

			min=12;
			//max=maxPage3Index;
		}*/
		return min;
	}

	
	private void clearQuestion(int index) {

		// Find question / level field
				Text field = findText("quesionField" + String.valueOf(index));
				if (field != null)
					field.setVisible(false);

				field = findText("levleField" + String.valueOf(index));
				if (field != null)
					field.setVisible(false);

				// find edit / delete icon
				ImageView img = findImageView("editBtn" + String.valueOf(index));
				if (img != null)
					img.setVisible(false);

				img = findImageView("deleteBtn" + String.valueOf(index));
				if (img != null)
					img.setVisible(false);
		
				

				// find Chart icon
				img = findImageView("chartBtn" + String.valueOf(index));
				if (img != null)
				img.setVisible(false);
				
			
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
			lvl = "Medium";
			break;
		case "3":
			lvl = "Hard";
			break;
		}
		return lvl;

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

	
		boolean lastPage = false;
		page++;
		int min =getMinIndexOfPage(page);
		int max=min+6;
		if(max >array.size()) {
			max = array.size();
			lastPage = true;
		}
	
		for (int i=min; i < max; i++) {
		
			try {
				addQuestion(array, i);
			} catch (IndexOutOfBoundsException e) {
				//removeQuestion(array, i);

			}
		}
		
		if(QuestionOnScreenIndex != 1 && lastPage)
		{
			while(QuestionOnScreenIndex != 1)
			clearPrevoiusQuestion();
		}

	}

	@FXML
	void BackQuestionBtnClicked(MouseEvent event) {
		
		if(page >1)
		page--;

		int min =getMinIndexOfPage(page);
		int max=min+6;
		if(max >array.size())
			max = array.size();


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
	
	@FXML
	void hoverStartImage(MouseEvent event) {
		if (event != null)
			hoverImage(event.getPickResult().getIntersectedNode().getId());

	}

	@FXML
	void hoverEndImage(MouseEvent event) {
		if (event != null)
			hoverImage(event.getPickResult().getIntersectedNode().getId());

	}

	private void hoverImage(String id) {
		if (id == null)
			return;
		Image image = createImage("deleteB");
		Image image2 = createImage("editB");
		Image image3 = createImage("graphB");

		switch (id) {
		case "deleteBtn1":
			deleteBtn1.setImage(image);
			break;
		case "deleteBtn2":
			deleteBtn2.setImage(image);
			break;
		case "deleteBtn3":
			deleteBtn3.setImage(image);
			break;
		case "deleteBtn4":
			deleteBtn4.setImage(image);
			break;
		case "deleteBtn5":
			deleteBtn5.setImage(image);
			break;
		case "deleteBtn6":
			deleteBtn6.setImage(image);
			break;
			
		case "editBtn1":
			editBtn1.setImage(image2);
			break;
		case "editBtn2":
			editBtn2.setImage(image2);
			break;
		case "editBtn3":
			editBtn3.setImage(image2);
			break;
		case "editBtn4":
			editBtn4.setImage(image2);
			break;
		case "editBtn5":
			editBtn5.setImage(image2);
			break;
		case "editBtn6":
			editBtn6.setImage(image2);
			break;
			
		case "chartBtn1":
			chartBtn1.setImage(image3);
			break;
		case "chartBtn2":
			chartBtn2.setImage(image3);
			break;
		case "chartBtn3":
			chartBtn3.setImage(image3);
			break;
		case "chartBtn4":
			chartBtn4.setImage(image3);
			break;
		case "chartBtn5":
			chartBtn5.setImage(image3);
			break;
		case "chartBtn6":
			chartBtn6.setImage(image3);
			break;

		default:
			image = createImage("delete");
			image2 = createImage("edit");
			image3 = createImage("line-chart-256");
			deleteBtn1.setImage(image);
			deleteBtn2.setImage(image);
			deleteBtn3.setImage(image);
			deleteBtn4.setImage(image);
			deleteBtn5.setImage(image);
			deleteBtn6.setImage(image);
			editBtn1.setImage(image2);
			editBtn2.setImage(image2);
			editBtn3.setImage(image2);
			editBtn4.setImage(image2);
			editBtn5.setImage(image2);
			editBtn6.setImage(image2);
			chartBtn1.setImage(image3);
			chartBtn2.setImage(image3);
			chartBtn3.setImage(image3);
			chartBtn4.setImage(image3);
			chartBtn5.setImage(image3);
			chartBtn6.setImage(image3);
		}

	}
	
	public static String removeLastChar(String str) {
        return removeLastChars(str, 1);
    }

    public static String removeLastChars(String str, int chars) {
        return str.substring(0, str.length() - chars);
    }

}
