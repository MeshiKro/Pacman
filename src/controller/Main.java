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
		
		/*if(ruuningThorthJar())
			runJarCommand();*/
		printPacmanByScorption();
		launch(args);
	}

	private static void runJarCommand() {
		 try {
			 Runtime.
			   getRuntime().
			   exec("cmd /c start \"\" run.bat");
		} catch (IOException e) {
			System.out.print(e.getMessage());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	

	public static boolean ruuningThorthJar() {

		String res = Main.class.getResource("Main.class").toString().substring(0, 4);
		printPacmanByScorption();
	

		return res.equals("rsrc");
	}

	private static void printPacmanByScorption() {
		System.out.println("  _____                                   ____                             \r\n"
				+ " |  __ \\                                 |  _ \\                             \r\n"
				+ " | |__) |_ _  ___ _ __ ___   __ _ _ __   | |_) |_   _                       \r\n"
				+ " |  ___/ _` |/ __| '_ ` _ \\ / _` | '_ \\  |  _ <| | | |                      \r\n"
				+ " | |  | (_| | (__| | | | | | (_| | | | | | |_) | |_| |                      \r\n"
				+ " |_|   \\__,_|\\___|_| |_| |_|\\__,_|_| |_| |____/ \\__, |                      \r\n"
				+ "                                                 __/ |                      \r\n"
				+ "   _____                      _   _             |___/____                   \r\n"
				+ "  / ____|                    | | (_)             |__   __|                  \r\n"
				+ " | (___   ___ ___  _ __ _ __ | |_ _  ___  _ __      | | ___  __ _ _ __ ___  \r\n"
				+ "  \\___ \\ / __/ _ \\| '__| '_ \\| __| |/ _ \\| '_ \\     | |/ _ \\/ _` | '_ ` _ \\ \r\n"
				+ "  ____) | (_| (_) | |  | |_) | |_| | (_) | | | |    | |  __/ (_| | | | | | |\r\n"
				+ " |_____/ \\___\\___/|_|  | .__/ \\__|_|\\___/|_| |_|    |_|\\___|\\__,_|_| |_| |_|\r\n"
				+ "                       | |                                                  \r\n"
				+ "                       |_|                                                      \r\n"
				+ " ___    ___\r\n"
				+ "( _<    >_ )          .-.    .-.    \r\n"
				+ "//        \\\\         | OO|  | OO| o  o  o  o  o  o  o  o  o  o\r\n"
				+ "\\\\___..___//         |   |  |   |\r\n"
				+ " `-(    )-'	     '^^^'  '^^^'\r\n"
				+ "   _|__|_\r\n"
				+ "  /_|__|_\\\r\n"
				+ "  /_|__|_\\\r\n"
				+ "  /_\\__/_\\\r\n"
				+ "   \\ || /  _)\r\n"
				+ "     ||   ( )\r\n"
				+ "     \\\\___//\r\n"
				+ "      `---'");	
	}

	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setResizable(true);
		primaryStage.setTitle("Pacman");
		primaryStage.getIcons().add(new Image("/images/pacicon.png"));
		primaryStage.initStyle(StageStyle.DECORATED);
		primaryStage.show();

	}

}
