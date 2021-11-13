package controller;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import view.StartWindow;


public class Main extends Application {

	public static void main(String[] args) throws ClassNotFoundException {
	
		launch(args);
		//
			

	}

	
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setResizable(true);
		//primaryStage.getIcons().add(new Image("./microphone-2-5123.png"));
	//	primaryStage.setTitle("Show4All");
		primaryStage.initStyle(StageStyle.DECORATED);
		primaryStage.show();
		
	}

    //I am a comment
}
