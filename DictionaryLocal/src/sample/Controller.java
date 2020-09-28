package sample;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Controller {
    @FXML
    private TextField word_target;
    @FXML
    private Label word_explain;
    @FXML
    private TextField word_explain_input;
    @FXML
    private Button accept;

    public void Translate (ActionEvent event) {
        //word_explain.setText(word_target.getText());
        //Alert.AlertType alertAlertType;
        //Alert alert = new Alert(AlertType.INFORMATION);
        //alert.setContentText(word_explain.getText());
        //word_explain.setText(word_target.getText());
        //alert.setContentText(word_target.getText());
        //alert.show();
        word_explain.setText("  " + word_target.getText());
        word_explain_input.setVisible(false);
        word_explain.setVisible(true);
    }

    public void Add (ActionEvent event) {
        word_explain.setVisible(false);
        word_explain_input.setDisable(false);
        word_explain_input.setVisible(true);
        accept.setVisible(true);
    }

    public void Delete (ActionEvent event) {

    }

    public void Edit (ActionEvent event) {

    }
}