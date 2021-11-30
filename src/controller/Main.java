package controller;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import misc.QuestionJsonWriterEx;




public class Main extends Application {

	public static void main(String[] args) throws Exception {
		
		
		
		QuestionJsonWriterEx check = new QuestionJsonWriterEx();
		check.serialazation();
		
		launch(args);
// 
			
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
