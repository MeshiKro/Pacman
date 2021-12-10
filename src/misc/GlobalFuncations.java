package misc;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import view.EditQuestionScreen;
import view.QuestionScreen;
import view.Rules1Screen;
import view.Rules2Screen;

public class GlobalFuncations {

	public static String username;

	public static void hoverButton(InputStream inStream, ImageView element) {
		element.setImage(new Image(inStream));
	}

	public static void switchScreen(Pane pane, String screenName, URL Path,String type) {
		FXMLLoader loader = new FXMLLoader(Path);
		try {
			AnchorPane newPane = loader.load();

			pane.setMaxSize(pane.getWidth(), pane.getHeight());
			pane.setMinSize(pane.getWidth(), pane.getHeight());
			pane.getChildren().removeAll(pane.getChildren());

			initializeConroller(loader, screenName,type);

			pane.getChildren().add(newPane);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static void initializeConroller(FXMLLoader loader, String screenName,String type) {

		switch (screenName) {
		case "QuestionScreen":
			QuestionScreen controller = loader.getController();
			controller.initialize();
			break;
		case "EditQuestionScreen":
			EditQuestionScreen controller1 = loader.getController();
			controller1.initialize(type);
			break;
		case "Rules2Screen":
			Rules2Screen controller2 = loader.getController();
			controller2.initialize();
			break;
		case "Rules1Screen":
			Rules1Screen controller3 = loader.getController();
			controller3.initialize();
			break;
		default:
			break;

		}

	}

}
