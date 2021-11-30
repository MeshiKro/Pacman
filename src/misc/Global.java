package misc;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import view.QuestionScreen;

public class Global {

	public static void hoverButton(InputStream inStream, ImageView element) {
		element.setImage(new Image(inStream));
	}

	public static void switchScreen(Pane pane, String screenName, URL Path) {
		FXMLLoader loader = new FXMLLoader(Path);
		try {
			AnchorPane newPane = loader.load();

			pane.setMaxSize(pane.getWidth(), pane.getHeight());
			pane.setMinSize(pane.getWidth(), pane.getHeight());
			pane.getChildren().removeAll(pane.getChildren());

			initializeConroller(loader, screenName);

			pane.getChildren().add(newPane);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static void initializeConroller(FXMLLoader loader, String screenName) {

		switch (screenName) {
		case "QuestionScreen":
			QuestionScreen controller = loader.getController();
			controller.initialize();
			break;
		default:
			break;

		}

	}

}
