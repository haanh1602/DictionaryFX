package app.controller.panes;

import helper.GoogleAPI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HistoryController extends ContentController implements Initializable {

    private String fileName = "History.txt";

    @FXML
    public void SearchTyping(KeyEvent event) throws IOException {
        getListViewSearchFromFile(fileName);
    }

    @FXML
    public void Translate(ActionEvent event) throws IOException {
        translate_fixed();
    }

    @FXML
    public void SelectItemListView(MouseEvent event) {
        selectItemListView(event);
        getListViewSearchFromFile(fileName);
    }

    @FXML
    public void resetHistory(MouseEvent event) {
        resetFile("History.txt");
        resetDictionary("History.txt");
        resetListViewWords();
    }

    public void initListView() {
        resetDictionary("History.txt");
        resetListViewWords();
        dictionary.dictionaryManagement.insertFromFile();
        ArrayList list_view = new ArrayList<String>();
        for(int i = 0; i < dictionary.numOfWord; i++) {
            list_view.add(dictionary.words.get(i).getWord_target());
        }
        search_list.getItems().addAll(list_view);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dictionary.dictionaryManagement.getFile("History.txt");
        initExplainArea();
    }
}
