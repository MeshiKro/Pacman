package controller;

import java.util.ArrayList;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import misc.QuestionJsonRead;
import misc.QuestionJsonWriterEx;
import model.QuestionObject;

public class Main extends Application {

	public static void main(String[] args) throws Exception {

		ArrayList<QuestionObject> a = new ArrayList<QuestionObject>();
		QuestionJsonRead JR = new QuestionJsonRead();
		QuestionJsonWriterEx JW = new QuestionJsonWriterEx();

		// Read Json Question from file
		a = JR.readQuestionsFromJson();

		// Write to json file
		JW.serialazation(a);

		launch(args);

	}

	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));
//		Parent root = FXMLLoader.load(getClass().getResource("/view/ConfirmPopUp.fxml"));

		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setResizable(true);
		primaryStage.setTitle("Pacman");
		primaryStage.getIcons().add(new Image("/images/pacicon.png"));
		primaryStage.initStyle(StageStyle.DECORATED);
		primaryStage.show();

	}

}
