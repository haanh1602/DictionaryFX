package app.controller.panes;

import app.dictionary.Dictionary;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable{

    Dictionary dictionary = new Dictionary();

    @FXML
    private Button searchBt, historyBt, settingBt, bookmarkBt;
    @FXML
    private AnchorPane currentPane;
    @FXML
    private AnchorPane searchPane = null;
    @FXML
    private AnchorPane bookmarkPane = null;
    @FXML
    private AnchorPane historyPane = null;
    @FXML
    private AnchorPane settingPane = null;

    private SearchController searchController;
    private HistoryController historyController;
    private BookmarkController bookmarkController;
    private SettingController settingController;

    public void setPane(AnchorPane anchorPane) {
        this.currentPane.getChildren().setAll(anchorPane);
    }

    public void resetStyleBtNav() {
        searchBt.setStyle(null);
        historyBt.setStyle(null);
        bookmarkBt.setStyle(null);
        settingBt.setStyle(null);
    }

    public void showSearchPane() {
        this.setPane(searchPane);
        searchBt.setStyle("-fx-background-color: #424a53");
    }

    @FXML
    private void handleMenuButtonAction(ActionEvent event) throws IOException {
        Parent currentScene = null;
        if (event.getSource() == searchBt) {
            //currentScene = FXMLLoader.load(getClass().getResource("../interface/SearchController.fxml"));
            //mainScene.getStylesheets().add(getClass().getResource("/design/application.css").toExternalForm());
            this.setPane(searchPane);
            this.resetStyleBtNav();
            searchBt.setStyle("-fx-background-color: #424a53");
        }
        else if (event.getSource() == historyBt) {
            this.setPane(historyPane);
            this.resetStyleBtNav();
            historyBt.setStyle("-fx-background-color: #424a53");
        }
        else if (event.getSource() == bookmarkBt) {
            this.setPane(bookmarkPane);
            this.resetStyleBtNav();
            bookmarkBt.setStyle("-fx-background-color: #424a53");
        }
        else if (event.getSource() == settingBt) {
            this.setPane(settingPane);
            this.resetStyleBtNav();
            settingBt.setStyle("-fx-background-color: #424a53");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("../../../interface/Search.fxml"));
            searchPane = fxmlLoader.load();
            searchController = fxmlLoader.getController();
            //searchPaneController.initData(this);
        } catch (IOException e) {
            System.out.println("Error: Cannot load searchPane!");
        }

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("../../../interface/History.fxml"));
            historyPane = fxmlLoader.load();
            historyController = fxmlLoader.getController();
            //historyController.initData(this);
        } catch (IOException e) {
            System.out.println("Error: Cannot load historyPane!");
        }

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("../../../interface/Bookmark.fxml"));
            bookmarkPane = fxmlLoader.load();
            bookmarkController = fxmlLoader.getController();
            //historyController.initData(this);
        } catch (IOException e) {
            System.out.println("Error: Cannot load bookmarkPane!");
        }

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("../../../interface/Setting.fxml"));
            settingPane = fxmlLoader.load();
            settingController = fxmlLoader.getController();
            //historyController.initData(this);
        } catch (IOException e) {
            System.out.println("Error: Cannot load settingPane!");
        }

        this.showSearchPane();
    }
}
