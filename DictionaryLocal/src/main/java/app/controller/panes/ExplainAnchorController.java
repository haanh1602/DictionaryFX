package app.controller.panes;

import helper.GoogleAPI;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ExplainAnchorController implements Initializable {
    @FXML
    private Label word_explain;
    @FXML
    private Label word_target;
    @FXML
    private Label pronounce;

    public ExplainAnchorController() {
    }

    @FXML
    public void SpeakEn (MouseEvent event) {
        String spelling = word_target.getText().trim();
        speakEn(spelling);
    }

    @FXML
    public void SpeakVi (MouseEvent event) {
        String spelling = word_explain.getText().trim();
        speakVi(spelling);
    }

    public void speakEn (String word) {
        if(word.equals("")) {
            word = "Please enter something!";
        }
        try {
            GoogleAPI.speak("en", word);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void speakVi (String word) {
        if(word.equals("")) {
            word = "Xin hãy nhập gì đó!";
        }
        try {
            GoogleAPI.speak("vi", word);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadData(String word_target, String word_explain, String pronounce) {
        this.word_target.setText(word_target);
        this.word_explain.setText(word_explain);
        this.pronounce.setText(pronounce);
    }

    public void reset() {
        this.word_target.setText("");
        this.word_explain.setText("");
        this.pronounce.setText("");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
