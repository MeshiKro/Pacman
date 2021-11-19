package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import misc.Global;

public class ConfirmPopUp {

    @FXML
    private AnchorPane pane;

    @FXML
    private ImageView startButtonClicked;

    @FXML
    private ImageView okBtn;

    @FXML
    private Label meg;

    

    @FXML
    void OkBtnClicked(MouseEvent event) {

    }

    @FXML
    void HoverEnd(MouseEvent event) {
		Global.hoverButton(getClass().getResourceAsStream("/images/" + "okBtn" + ".png"), okBtn);		

    }
    
    @FXML
    void hoverStart(MouseEvent event) {
		Global.hoverButton(getClass().getResourceAsStream("/images/" + "okBtnClicked" + ".png"), okBtn);		

    }

}
