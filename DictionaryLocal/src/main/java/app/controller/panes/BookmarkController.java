package app.controller.panes;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class BookmarkController extends ContentController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dictionary.dictionaryManagement.getFile("Bookmark.txt");
        dictionary.dictionaryManagement.insertFromFile();
        initExplainArea();
    }
}
