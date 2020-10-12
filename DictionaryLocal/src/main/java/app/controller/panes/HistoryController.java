package app.controller.panes;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class HistoryController extends ContentController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dictionary.dictionaryManagement.getFile("History.txt");
        dictionary.dictionaryManagement.insertFromFile();
        initExplainArea();
    }
}
