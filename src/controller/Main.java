package controller;

import Tests.sysDataTester;
import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import misc.MapData;
import misc.QuestionJsonRead;
import model.QuestionObject;



public class Main extends Application {

	public static void main(String[] args) throws ClassNotFoundException {
	
		System.out.println("hello Dror Check");
		
		sysDataTester test = new sysDataTester();
		test.MapData_Singleton_Check(); // this is the Jtest Call For the MapData Singleton Check//
		
		
		System.out.println("Questions from JSON are:");
		
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
