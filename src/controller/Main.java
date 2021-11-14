package controller;

import java.util.ArrayList;

import Tests.sysDataTester;
import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import misc.MapData;
import misc.QuestionJsonRead;
import misc.QuestionJsonWriterEx;
import model.QuestionObject;



public class Main extends Application {

	public static void main(String[] args) throws ClassNotFoundException {

		QuestionJsonRead r = new QuestionJsonRead ();

		ArrayList<QuestionObject> CheckList = r.readQuestionsFromJson();

		
		sysDataTester test = new sysDataTester();
		test.MapData_Singleton_Check(); // this is the Jtest Call For the MapData Singleton Check//
		test.questionDeSer_test();// this is the Jtest Call For the QuestionDeser  Check//
		test.QuestionSer_test(CheckList);
		
		
		
		
		
		 
		 
		 
		 /*QuestionJsonWriterEx check = new QuestionJsonWriterEx();
		 check.serialazation(QuestionJsonRead.questionsAndAnswers);*/
		
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
