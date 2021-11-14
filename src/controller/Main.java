package controller;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;



public class Main extends Application {

	public static void main(String[] args) throws ClassNotFoundException {
	
		System.out.println("hello Dror Check");
		
		
		
		
		//Check If deSerialazation from Json works- if so Questions Should print to the console//
		System.out.println("Questions from JSON are:"+ SysData.questions);
		
		launch(args);

			

	}

	
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setResizable(true);
		primaryStage.setTitle("Pacman");
		primaryStage.initStyle(StageStyle.DECORATED);
		primaryStage.show();
		
	}

}
