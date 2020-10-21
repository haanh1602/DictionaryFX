package app.controller.panes;

import app.dictionary.Dictionary;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.awt.*;

public class SettingController extends ContentController {

    private Dictionary myDictionary = new Dictionary();

    @FXML
    protected TextField word_explain;
    @FXML
    protected TextField pronounce;

    @FXML
    private TextField notification;

    @FXML
    public void ADD(ActionEvent event) {
        String Pronounce = "Null";
        String Word_target = word_target.getText();
        String Word_explain = word_explain.getText();
        Pronounce = pronounce.getText();
        myDictionary.dictionaryManagement.addWordToFile(Word_target, Word_explain, Pronounce);
        notification.setText("Your word is added");
    }

    @FXML
    public void DELETE(ActionEvent event) {
        String Word_target = word_target.getText();
        myDictionary.dictionaryManagement.insertFromFile();
        for(int i = 0; i < myDictionary.numOfWord; i++) {
            if(Word_target.equals(myDictionary.words.get(i).getWord_target())) {
                myDictionary.words.remove(i);
                myDictionary.numOfWord--;
                myDictionary.dictionaryManagement.dictionaryExportToFile();
                notification.setText("Your word is deleted");
                return;
            }
        }
        notification.setText("Not found");
    }

    @FXML
    public void EDIT(ActionEvent event) {
        String Pronounce = "Null";
        myDictionary.dictionaryManagement.insertFromFile();
        String Word_target = word_target.getText();
        String Word_explain = word_explain.getText();
        Pronounce = pronounce.getText();
        for(int i = 0; i < myDictionary.numOfWord; i++) {
            if(Word_target.equals(myDictionary.words.get(i).getWord_target()) && Word_explain.equals(myDictionary.words.get(i).getWord_explain())) {
                myDictionary.words.remove(i);
                myDictionary.numOfWord--;
                myDictionary.dictionaryManagement.dictionaryExportToFile();
                myDictionary.dictionaryManagement.addWordToFile(Word_target, Word_explain, Pronounce);
                notification.setText("Your word is edited");
                return;
            }
        }
        notification.setText("Not found");
    }
}
