package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static javafx.application.Application.launch;

public class MainController implements Initializable {

    Dictionary dictionary = new Dictionary();

    @FXML
    private Button search;

    public void Search(ActionEvent event) {
        System.out.println("OKE");
    }

    @FXML
    private void handleMenuButtonAction(ActionEvent event) throws IOException {
        Stage stage = null;
        Parent mainScene = null;

        if(event.getSource() == search) {
            stage = (Stage) search.getScene().getWindow();
            mainScene = FXMLLoader.load(getClass().getResource("Search.fxml"));
        }
        Scene newScene = new Scene(mainScene);
        stage.setScene(newScene);
        stage.setTitle("Search");
        stage.show();
    }

    /*@Override
    public void start(Stage primaryStage) throws Exception{
        try {
            dictionary.dictionaryManagement.insertFromFile();
            Parent mainController = FXMLLoader.load(getClass().getResource("Search.fxml"));
            //mainStage.getChildrenUnmodifiable(translateStage);
            primaryStage.setTitle("Dictionary");

            primaryStage.setScene(new Scene(mainController));
            primaryStage.show();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        /*Dictionary a = new Dictionary();
        Scanner sc = new Scanner(System.in);
        //a.dictionaryManagement.insertFromCommandline();
        a.dictionaryManagement.insertFromFile();
        while(true) {
            System.out.println("[1]     Options" + "\n" +
                               "[other] Exit");
            String option = sc.nextLine().trim();
            if(option.equals("1")) {
                a.dictionaryCommandline.showAllWords();
                a.dictionaryManagement.setting();
            } else {
                a.dictionaryCommandline.showAllWords();
                break;
            }
        }
        Dictionary dictionary = new Dictionary();
        dictionary.dictionaryManagement.insertFromFile();*/

        //launch(args);
    //}*/

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
