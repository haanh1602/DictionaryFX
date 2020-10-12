package app.controller.panes;

import app.dictionary.Dictionary;
import helper.GoogleAPI;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;

public class ContentController {
    protected Dictionary dictionary = new Dictionary();

    @FXML
    protected TextField word_target;
    @FXML
    protected Label word_explain;
    @FXML
    protected ListView<String> search_list;
    @FXML
    protected AnchorPane explainArea;

    protected ExplainAnchorController explainAnchorController;

    public void resetListViewWords(ListView listViewWords) {
        listViewWords.getItems().clear();
    }

    public void selectItemListView(MouseEvent event) {
        String selectedItem = search_list.getSelectionModel().getSelectedItem();
        if(selectedItem == null) {
            return;
        }
        word_target.setText(selectedItem);
        try {
            translate_fixed();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void translate_fixed() throws IOException {
        String[] result = GoogleAPI.translate("en", "vi", word_target.getText()).split("\n");
        if(result.length < 2) {
            explainAnchorController.reset();
            return;
        }
        String explainWord = rightMargin(result[0], 25);
        String pronounce = result[1];
        explainAnchorController.loadData(rightMargin(word_target.getText(), 23), explainWord, pronounce);
    }

    public ArrayList sortList(ArrayList<String> arrayList) {
        Collections.sort(arrayList);
        return arrayList;
    }

    public static String rightMargin(String explain, int widthPerLine) {
        if(explain.trim().equals("")) return "";
        String res = "";
        String[] words = explain.trim().split(" ");
        int count = 0;
        while(count < words.length) {
            int lengthOfLine = 0;
            if(words[count].length() >= widthPerLine) {
                res += words[count++];
            }
            else {
                while((count < words.length) && (lengthOfLine + words[count].length() < widthPerLine)) {
                    res += words[count] + " ";
                    lengthOfLine += words[count].length() + 1;
                    count++;
                }
            }
            res += "\n";
        }
        return res;
    }

    public boolean isExist(String word_target) {
        return (this.dictionary.dictionaryManagement.dictionaryLookup(word_target) != null);
    }

    protected static String translate(String langFrom, String langTo, String text) throws IOException {
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

    public void initExplainArea() {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("../../../interface/ExplainAnchor.fxml"));
        AnchorPane explain;
        try {
            explain = fxmlLoader.load();
        } catch (IOException e) {
            System.out.println("Error load view word pane.");
            return;
        }
        explainArea.getChildren().addAll(explain);
        explainAnchorController = fxmlLoader.getController();
    }

}
