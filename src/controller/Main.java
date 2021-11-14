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

		
		
		sysDataTester test = new sysDataTester();
		test.MapData_Singleton_Check(); // this is the Jtest Call For the MapData Singleton Check//
		
		
		readJson();
		launch(args);

			

	}

	
	private static void readJson() {
		QuestionJsonRead r = new QuestionJsonRead ();
		 r.readQuestionsFromJson();
		 System.out.println(QuestionJsonRead.questionsAndAnswers);
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
