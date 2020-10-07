package app.controller.panes;

import app.dictionary.Dictionary;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class SearchController implements Initializable {

    Dictionary dictionary = new Dictionary();

    @FXML
    private TextField word_target;
    @FXML
    private Label word_explain;
    @FXML
    private Button back;

    private static String translate(String langFrom, String langTo, String text) throws IOException {
        // INSERT YOU URL HERE
        if(text.trim().isEmpty()) return "";
        String urlStr =
                "https://script.google.com/macros/s/AKfycbyz2XsrX8yPv5KSegyJbZVYghrJWTD5bx0BfSn6ZJEsZyX2VNNg/exec" +
                        "?q=" + URLEncoder.encode(text, "UTF-8") +
                        "&target=" + langTo +
                        "&source=" + langFrom;
        URL url = new URL(urlStr);
        StringBuilder response = new StringBuilder();
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }

    public void Translate(ActionEvent event) throws IOException {
        word_explain.setText("  " + translate("en", "vi", word_target.getText()));
    }

    public void BackToMain(ActionEvent event) throws IOException {
        Stage stage = null;
        Parent mainScene = null;

        if (event.getSource() == back) {
            stage = (Stage) back.getScene().getWindow();
            mainScene = FXMLLoader.load(getClass().getResource("../interface/MainController.fxml"));
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
        while (possibleWord.size() < dictionary.numOfWord) {
            possibleWord.add(dictionary.words[possibleWord.size()].getWord_target());
        }
    }
}