package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Scanner;

import static javafx.application.Application.launch;

public class Main extends Application {
    Dictionary dictionary = new Dictionary();

    @Override
    public void start(Stage primaryStage) {
        try {
            dictionary.dictionaryManagement.insertFromFile();
            Parent mainController = FXMLLoader.load(getClass().getResource("MainController.fxml"));
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
        a.dictionaryManagement.insertFromCommandline();
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

        launch(args);
        }
}
