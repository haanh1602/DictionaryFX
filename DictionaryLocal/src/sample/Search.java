package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.controlsfx.control.textfield.TextFields;


public class Search implements Initializable {

    Dictionary dictionary = new Dictionary();

    @FXML
    private TextField word_target;
    @FXML
    private Label word_explain;
    @FXML
    private Button back;

    public void Translate (ActionEvent event) {
        word_explain.setText("  " + dictionary.dictionaryManagement.dictionaryLookup(word_target.getText()));
        //word_explain.setVisible(true);
    }

    public void BackToMain (ActionEvent event) throws IOException {
        Stage stage = null;
        Parent mainScene = null;

        if(event.getSource() == back) {
            stage = (Stage) back.getScene().getWindow();
            mainScene = FXMLLoader.load(getClass().getResource("MainController.fxml"));
        }
        Scene newScene = new Scene(mainScene);
        stage.setScene(newScene);
        stage.setTitle("Search");
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dictionary.dictionaryManagement.insertFromFile();
        List<String> possibleWord = new ArrayList<>();
        while(possibleWord.size() < dictionary.numOfWord) {
            possibleWord.add(dictionary.words[possibleWord.size()].getWord_target());
        }
        TextFields.bindAutoCompletion(word_target, possibleWord);
    }
}