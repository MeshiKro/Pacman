package view;

import java.io.InputStream;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import com.sun.javafx.stage.EmbeddedWindow;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import misc.GlobalFuncations;
import misc.JsonRead;
import misc.JsonWriterEx;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class MainScreen {

	public static boolean isMute = false;

	public static String theme = "Basic";
	
	public static int characterFlag = 1;

	@FXML
	private AnchorPane pane;

	@FXML
	private ImageView startGameBtn;
	
    @FXML
    private Button chooseBtn;

	@FXML
	private ComboBox<String> themeField;

	@FXML
	private ImageView startButtonClicked;

	@FXML
	private AnchorPane rulesPane;
	
    @FXML
    private Button chooseBtnTheme;

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

	@FXML
	private Label noNameLabel;

	@FXML
	private ImageView muteTest;

	public Stage stage = new Stage();

	private EmbeddedWindow stage1;



	
	public void initialize() {
		themeField.getItems().addAll("Basic", "Candy Land", "Zombie Land");
		themeField.getSelectionModel().select(0);
themeField.setVisible(false);
		setScreenConfig();
		
		setBtnLabel(characterFlag);
		
		if(theme.equals("Basic"))
			chooseBtnTheme.setText("basic");
		if(theme.equals("Candy Land"))
			chooseBtnTheme.setText("candy land");
		if(theme.equals("Zombie Land"))
			chooseBtnTheme.setText("zombie land");

	}
	
	private void setBtnLabel(int characterNum) {

    	if(characterNum==1) {
    		chooseBtn.setText("pacman");
    	}
     	if(characterNum==2) {
     		chooseBtn.setText("pacwoman");
     	}
       	if(characterNum==3) {
       		chooseBtn.setText("viking");
     	}
       	if(characterNum==4) {
       		chooseBtn.setText("officer");
     	}
       	if(characterNum==5) {
       		chooseBtn.setText("santa");
     	}
       	if(characterNum==6) {
       		chooseBtn.setText("alien");
     	}
       	if(characterNum==7) {
       		chooseBtn.setText("angel");
     	}
       	if(characterNum==8) {
       		chooseBtn.setText("devil");
     	}
		}

	private void setScreenConfig() {
		JsonRead jr = new JsonRead();
		jr.readConfig();
		nameFeild.setText(GlobalFuncations.username);
		if (isMute == true) {
			Image muteImage = createImage("unMute");
			muteTest.setImage(muteImage);
			setMute(true);
		} else {
			Image muteImage = createImage("mute");
			muteTest.setImage(muteImage);
			setMute(false);
		}

	}

	// Hover Section
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
	void hoverStartSideButton(MouseEvent event) {
		if (event != null)
			hoverSideButton(event.getPickResult().getIntersectedNode().getId());

	}

	@FXML
	void hoverEndSideButton(MouseEvent event) {
		if (event != null)
			hoverSideButton(event.getPickResult().getIntersectedNode().getId());

	}

	private void hoverSideButton(String id) {
		if (id == null)
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

	private Image createImage(String img) {
		InputStream inStream = getClass().getResourceAsStream("/images/" + img + ".png");
		return new Image(inStream);
	}

	// End Hover Section

	// OnClick Section//
	@FXML
	void muteBtnClicked(MouseEvent event) {
		if (isMute == false) {
			Image muteImage = createImage("unMute");
			muteTest.setImage(muteImage);
			setMute(true);
			return;
		} else if (isMute == true) {
			Image muteImage = createImage("mute");
			muteTest.setImage(muteImage);
			setMute(false);
			return;
		}

	}

	@FXML
	public static boolean isMute() {
		return isMute;
	}

	public static void setMute(boolean isMute) {
		MainScreen.isMute = isMute;
	}

	@FXML
	void startGame(MouseEvent event) {

	//	String th = themeField.getSelectionModel().getSelectedItem();
		String th = theme;

		if (th.equals("Basic")) {
			theme = "Basic";
		}
		if (th.equals("Candy Land")) {
			theme = "Candy Land";
		}
		if (th.equals("Zombie Land")) {
			theme = "Zombie Land";
		}

		// check for a name input
		if (nameFeild.getText().isEmpty()) {
			noNameLabel.setText("*you must enter name");
			return;
		}

		GlobalFuncations.username = nameFeild.getText();
		noNameLabel.setText("");
		stage1 = null;
		try {
		 stage  = (Stage) startGameBtn.getScene().getWindow();
		}
		catch(Exception e)
		{
			System.out.print(e.getMessage());


			stage1  = (com.sun.javafx.stage.EmbeddedWindow) startGameBtn.getScene().getWindow();

		}
		JsonWriterEx wr = new JsonWriterEx();
		wr.SetConfugartion(MainScreen.isMute, GlobalFuncations.username);


		/*Platform.runLater(new Runnable() {
			@Override
			public void run() {
				stage.setOpacity(0);

				
			}
		});*/
		@SuppressWarnings("unused")
		PacWindow pw = new PacWindow();
		stage.setOpacity(0);
		if(stage1 != null)
		stage1.setOpacity(0);
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {

		    @Override
		    public void run() {
		    	if(PacWindow.openMainScreen) {
					try {
					
					stage.setOpacity(1);
					if(stage1 != null)

					stage1.setOpacity(1);

					}
					catch(Exception ex){
						timer.cancel();
					}

		    	}
		    }
		};

		timer.schedule(task, new Date(), 3000);


	}

	@FXML
	void QuestionWizardBtnClicked(MouseEvent event) {
		GlobalFuncations.switchScreen(pane, "QuestionScreen",
				(getClass().getResource("/view/" + "QuestionScreen" + ".fxml")), "");
	}

	@FXML
	void ScoreBoardButtonClicked(MouseEvent event) {
		GlobalFuncations.switchScreen(pane, "ScoreBoardScreen",
				(getClass().getResource("/view/" + "ScoreBoardScreen" + ".fxml")), "");

	}

	@FXML
	void rulesClicked(MouseEvent event) {
			GlobalFuncations.switchScreen(pane, "Rules1Screen",
				(getClass().getResource("/view/" + "Rules1Screen" + ".fxml")), "");

					/*	GlobalFuncations.switchScreen(pane, "ChartScreen",
				(getClass().getResource("/view/" + "ChartScreen" + ".fxml")), "What are partial requirements?");*/
	}
	// End OnClick Section
	
    @FXML
    void chooseBtnClicked(MouseEvent event) {
    	GlobalFuncations.switchScreen(pane, "ChooseCharacterScreen",
				(getClass().getResource("/view/" + "ChooseCharacterScreen" + ".fxml")), "");
    }
    
    @FXML
    void hoverStartC(MouseEvent event) {
    	chooseBtn.setStyle("-fx-background-color: #cba79a; -fx-background-radius:25; -fx-border-width:5; -fx-border-color:white; -fx-border-radius:25");
    }
    
    @FXML
    void HoverEndC(MouseEvent event) {
    	chooseBtn.setStyle("-fx-background-color: #efe4e0; -fx-background-radius:25; -fx-border-width:5; -fx-border-color:white; -fx-border-radius:25");
    }
    
    @FXML
    void chooseThemeBtnClicked(MouseEvent event) {
    	GlobalFuncations.switchScreen(pane, "ChooseThemeScreen",
				(getClass().getResource("/view/" + "ChooseThemeScreen" + ".fxml")), "");
    }

}
