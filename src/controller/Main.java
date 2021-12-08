package controller;

import java.util.ArrayList;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import misc.JsonRead;
import misc.JsonWriterEx;
import model.QuestionObject;

public class Main extends Application {

	public static void main(String[] args) throws Exception {

		ArrayList<QuestionObject> a = new ArrayList<QuestionObject>();
		JsonRead JR = new JsonRead();
		JsonWriterEx JW = new JsonWriterEx();
// TEST
		// Read Json Question from file
	//	a = JR.readQuestionsFromJson();

		// Write to json file
	//	JW.serialazation(a);

		//JW.writeScordboardRecords("fff",132,"15.12.2021");
		
		//System.out.println(JR.readScoreBoardFromJson());

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
