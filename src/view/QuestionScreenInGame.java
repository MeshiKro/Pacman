package view;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

public class QuestionScreenInGame {

    @FXML
    private ImageView answer1;

    @FXML
    private ImageView answer2;

    @FXML
    private ImageView answer3;

    @FXML
    private ImageView answer4;

    @FXML
    private AnchorPane pane;

    @FXML
    private ImageView startButtonClicked;

    @FXML
    void HoverEnd(MouseEvent event) {

    }

    @FXML
    void answerClicked(MouseEvent event) {
    	System.out.println(event.getPickResult().getIntersectedNode().getId());
    }

    @FXML
    void hoverStart(MouseEvent event) {

    }

}
