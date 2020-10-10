package app.controller.panes;

import app.dictionary.Dictionary;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.effect.Bloom;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable{

    @FXML
    private Button searchBt, historyBt, settingBt, bookmarkBt;
    @FXML
    private AnchorPane currentPane;

    private AnchorPane searchPane = null;
    private AnchorPane bookmarkPane = null;
    private AnchorPane historyPane = null;
    private AnchorPane settingPane = null;
    private AnchorPane explainAnchor = null;

    public Bloom bloom = new Bloom();

    private SearchController searchController;
    private HistoryController historyController;
    private BookmarkController bookmarkController;
    private SettingController settingController;
    private ExplainAnchorController explainAnchorController;

    public void setPane(AnchorPane anchorPane) {
        this.currentPane.getChildren().setAll(anchorPane);
    }

    public void resetStyleBtNav() {
        searchBt.setStyle(null);
        historyBt.setStyle(null);
        bookmarkBt.setStyle(null);
        settingBt.setStyle(null);
    }

    public void resetEffect() {
        searchBt.setEffect(null);
        historyBt.setEffect(null);
        bookmarkBt.setEffect(null);
        settingBt.setEffect(null);
    }

    public void showSearchPane() {
        this.setPane(searchPane);
        this.resetStyleBtNav();
        this.resetEffect();
        searchBt.setEffect(bloom);
        searchBt.setStyle("-fx-background-color: #424a53");
    }
    public void showHistoryPane() {
        this.setPane(historyPane);
        this.resetStyleBtNav();
        this.resetEffect();
        historyBt.setEffect(bloom);
        historyBt.setStyle("-fx-background-color: #424a53");
    }
    public void showBookmarkPane() {
        this.setPane(bookmarkPane);
        this.resetStyleBtNav();
        this.resetEffect();
        bookmarkBt.setEffect(bloom);
        bookmarkBt.setStyle("-fx-background-color: #424a53");
    }
    public void showSettingPane() {
        this.setPane(settingPane);
        this.resetEffect();
        this.resetStyleBtNav();
        settingBt.setEffect(bloom);
        settingBt.setStyle("-fx-background-color: #424a53");
    }

    @FXML
    private void handleMenuButtonAction(ActionEvent event) {
        if (event.getSource() == searchBt) {
            this.showSearchPane();
        }
        else if (event.getSource() == historyBt) {
            this.showHistoryPane();
        }
        else if (event.getSource() == bookmarkBt) {
            this.showBookmarkPane();
        }
        else if (event.getSource() == settingBt) {
            this.showSettingPane();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("../../../interface/Search.fxml"));
            searchPane = fxmlLoader.load();
            searchController = fxmlLoader.getController();
        } catch (IOException e) {
            System.out.println("Error: Cannot load searchPane!");
        }

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("../../../interface/History.fxml"));
            historyPane = fxmlLoader.load();
            historyController = fxmlLoader.getController();
        } catch (IOException e) {
            System.out.println("Error: Cannot load historyPane!");
        }

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("../../../interface/Bookmark.fxml"));
            bookmarkPane = fxmlLoader.load();
            bookmarkController = fxmlLoader.getController();
        } catch (IOException e) {
            System.out.println("Error: Cannot load bookmarkPane!");
        }

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("../../../interface/Setting.fxml"));
            settingPane = fxmlLoader.load();
            settingController = fxmlLoader.getController();
        } catch (IOException e) {
            System.out.println("Error: Cannot load settingPane!");
        }

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("../../../interface/ExplainAnchor.fxml"));
            explainAnchor = fxmlLoader.load();
            explainAnchorController = fxmlLoader.getController();
        } catch (IOException e) {
            System.out.println("Error: Cannot load settingPane!");
        }

        this.showSearchPane();
    }
}
