package app.controller.panes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import helper.GoogleAPI;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SearchController extends ContentController implements Initializable {

    @FXML
    public void SearchTyping(KeyEvent event) throws IOException {
        getListViewSearch();
    }

    public void getListViewSearch() {
        resetListViewWords();
        ArrayList<String> listView_words;
        if (!word_target.getText().trim().equals("")) {
            try {
                listView_words = sortList(GoogleAPI.search(word_target.getText()));
                search_list.getItems().addAll(listView_words);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void Translate(ActionEvent event) throws IOException {
        translate_fixed();
        addHistory();
    }

    @FXML
    public void SelectItemListView(MouseEvent event) {
        selectItemListView(event);
        addHistory();
        getListViewSearch();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initExplainArea();
    }
}