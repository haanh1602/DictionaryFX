package app.controller.panes;

import com.sun.javaws.IconUtil;
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

public class BookmarkController extends ContentController implements Initializable {

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
        getListViewSearch();
    }

    @FXML
    public void resetBookmark(MouseEvent event) {
        resetFile("Bookmark.txt");
        resetDictionary("Bookmark.txt");
        resetListViewWords();
    }

    public void initListView() {
        resetDictionary("Bookmark.txt");
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
        dictionary.dictionaryManagement.getFile("Bookmark.txt");
        initExplainArea();
    }
}
