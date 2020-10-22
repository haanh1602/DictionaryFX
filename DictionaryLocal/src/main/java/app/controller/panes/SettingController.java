package app.controller.panes;

import app.dictionary.Dictionary;
import app.dictionary.Word;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.effect.Bloom;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import javax.swing.text.StyledEditorKit;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

public class SettingController implements Initializable {

    private Dictionary myDictionary = new Dictionary("MyDictionary.txt");

    @FXML
    private TextField word_target;
    @FXML
    private TextField word_explain;
    @FXML
    private TextField pronounce;
    @FXML
    private Label notification;
    @FXML
    private ListView<String> my_list;
    @FXML
    protected JFXButton addBt, deleteBt, editBt;
    @FXML
    private Button acceptBt;
    @FXML
    private String current_feature;

    public void resetButtons() {
        resetButton(addBt);
        resetButton(deleteBt);
        resetButton(editBt);
    }

    public void resetButton(JFXButton button) {
        button.setStyle(null);
        button.setEffect(null);
        button.setUnderline(false);
        button.textFillProperty().set(Color.BLACK);
    }

    public void resetContent() {
        word_target.setText("");
        word_explain.setText("");
        pronounce.setText("");
    }

    public void setButtonOn(JFXButton button) {
        resetButtons();
        button.setStyle("-fx-background-color: #2a3039");
        button.setEffect(new Bloom());
        button.setUnderline(true);
        button.textFillProperty().set(Color.WHITE);
        current_feature = button.getText();
        notification.setText("");
        resetContent();
    }

    @FXML
    public void nav(MouseEvent event) {
        if(event.getSource() == addBt) {
            setButtonOn(addBt);
        }
        else if(event.getSource() == deleteBt) {
            setButtonOn(deleteBt);
        }
        else if(event.getSource() == editBt) {
            setButtonOn(editBt);
        }
    }

    private ArrayList<String > list = new ArrayList<>();

    public void get_list_view() {
        my_list.getItems().clear();
        my_list.getItems().addAll(list);
    }

    public void init_list() {
        myDictionary.dictionaryManagement.insertFromFile();
        for(int i = 0; i < myDictionary.numOfWord; i++) {
            list.add(myDictionary.words.get(i).getWord_target());
        }
        Collections.sort(list);
    }

    public void format_input() {
        word_target.setText(word_target.getText().trim());
        word_explain.setText(word_explain.getText().trim());
        pronounce.setText(pronounce.getText().trim());
        if(word_explain.getText().equals("")) word_explain.setText(word_target.getText());
        word_explain.setText(word_explain.getText() + " ");
        if(!word_target.getText().equals("")) {
            if(pronounce.getText().equals("")) pronounce.setText("/null/");
        }
    }

    @FXML
    public void process(ActionEvent event) {
        if(current_feature.equals("ADD")) {
            ADD();
        }
        else if(current_feature.equals("DELETE")) {
            DELETE();
        }
        else if(current_feature.equals("EDIT")) {
            EDIT();
        }
    }

    public void ADD() {
        format_input();
        if(word_target.getText().equals("")) {
            notification.setText("Please enter something in\nword-target !");
            return;
        }
        Word word = new Word(word_target.getText(), word_explain.getText(), " /" + pronounce.getText() + "/");
        int index = myDictionary.dictionaryManagement.dictionaryLookupIndex(word_target.getText());
        if(myDictionary.dictionaryManagement.editWord(word.getWord_target(), word)) {
            notification.setText("Your word is existed, the word\nwill be overwritten!");
            //return;
        } else {
            myDictionary.dictionaryManagement.addWordToFile(
                    word.getWord_target(), word.getWord_explain(), word.getPronounce());
            list.add(word_target.getText());
            Collections.sort(list);
            notification.setText("Your word is added");
        }
        get_list_view();
    }

    public void DELETE() {
        format_input();
        if(word_target.getText().equals("")) {
            notification.setText("Please enter something in\nword-target !");
            return;
        }
        int index = myDictionary.dictionaryManagement.dictionaryLookupIndex(word_target.getText());
        if(myDictionary.dictionaryManagement.deleteWord_FX(word_target.getText())) {
            notification.setText("Your word is deleted");
            list.remove(index);
            get_list_view();
            return;
        }
        notification.setText("Not found");
    }

    public void EDIT() {
        format_input();
        if(word_target.getText().equals("")) {
            notification.setText("Please enter something in\nword-target !");
            return;
        }
        Word word = new Word(word_target.getText(), word_explain.getText(), " /" + pronounce.getText() + "/");
        for(int i = 0; i < myDictionary.numOfWord; i++) {
            if(myDictionary.dictionaryManagement.editWord(word.getWord_target(), word)) {
                notification.setText("Your word is overwritten !");
                return;
            }
        }
        notification.setText("Not found");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        init_list();
        get_list_view();
        resetButtons();
    }
}
