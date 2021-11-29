package view;

import java.awt.LayoutManager;
import java.io.InputStream;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import javafx.embed.swing.SwingNode;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import misc.Global;

public class GameScreen {

	@FXML
	private ImageView QABtn;

	@FXML
	private AnchorPane pane;

	@FXML
	private AnchorPane gamePane;

	@FXML
	private ImageView homeBtn;

	@FXML
	private AnchorPane homePane;
	
    @FXML
    private ImageView paneImage;
    
	@FXML
	private ImageView muteBtn;

	@FXML
	private AnchorPane mutePane;

	public void initialize() {

		SwingNode swingNode = new SwingNode();
		createAndSetSwingContent(swingNode);
		gamePane.getChildren().add(swingNode); 

	}

	
	@FXML
	void HomeBtnClicked(MouseEvent event) {

	}

	
	@FXML
	void hoverStartSideButton(MouseEvent event) {
		String img ="buttonconinterClicked";
		ImageView image =	getClickBtn(event.getPickResult().getIntersectedNode().getId());
		if(image !=null)
		Global.hoverButton(getClass().getResourceAsStream("/images/" + img + ".png"),image);		

	}

	@FXML
	void hoverEndSideButton(MouseEvent event) {
		String img ="buttonconinter";
		ImageView image =	getClickBtn(event.getPickResult().getIntersectedNode().getId());
		if(image !=null)
		Global.hoverButton(getClass().getResourceAsStream("/images/" + img + ".png"),image);		
	}
	
	private ImageView getClickBtn(String id) {
		if(id == null)
			return null;
			
		ImageView toReturn = null;
		id = id.replace("Btn", "").replace("Pane", "");
		switch (id) {
		case "mute":
			toReturn= muteBtn;
			break;
		case "home":
			toReturn= homeBtn;
			break;
		default:
			Image	image = createImage("buttonconinter");
			homeBtn.setImage(image);
			muteBtn.setImage(image);
		}
		return toReturn;
	}
	

	private Image createImage(String img) {
		InputStream inStream = getClass().getResourceAsStream("/images/" + img + ".png");
		return new Image(inStream);
	}
	
	

	
	
	private void createAndSetSwingContent(final SwingNode swingNode) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				PacWindow pacmanGame = new PacWindow();
				JPanel p = pacmanGame.createGameBoard();
				swingNode.setContent(p);
			}
		});
	}

	
}
