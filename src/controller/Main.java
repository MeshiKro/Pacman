package controller;

import java.io.IOException;
import java.io.InputStream;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import misc.JsonRead;

public class Main extends Application {

	public static void main(String[] args) throws Exception {

		JsonRead JR = new JsonRead();
		JsonRead.questionsAndAnswers = JR.readQuestionsFromJson();
		
	//	if (ruuningThorthJar())
	runJarCommand(args);
		//else
		
		//launch(args);
	}

	private static void runJarCommand(String[] args) {
		 try {
			 Runtime.
			   getRuntime().
			   exec("cmd /c start \"\" run.bat");
		} catch (IOException e) {
			System.out.print(e.getMessage());
			launch(args);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static String getCommand() {
		// TODO Auto-generated method stub
		return "java -jar --module-path \"javafx-sdk-11.0.2\\lib\" --add-modules=javafx.controls,javafx.fxml,javafx.swing game.jar";
	}

	public static boolean ruuningThorthJar() {

		String res = Main.class.getResource("Main.class").toString().substring(0, 4);
		System.out.println(res);

		return res.equals("rsrc");
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
