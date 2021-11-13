package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class MainScreen {

	@FXML
	private AnchorPane pane;

	@FXML
	private ImageView startGameBtn;

	@FXML
	private ComboBox<String> themeField;

	@FXML
	private TextField nameFeild;

	public void initialize() {
		themeField.getItems().addAll("Basic", "Candy Land", "Zombie Land");
		themeField.getSelectionModel().select(0);	}

	@FXML
	void startGame(MouseEvent event) {
		PacWindow pw = new PacWindow();
		Stage stage = (Stage) startGameBtn.getScene().getWindow();
		stage.close();
	}

}
