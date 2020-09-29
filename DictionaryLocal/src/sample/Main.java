package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Scanner;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
            primaryStage.setTitle("Dictionary");
            primaryStage.setScene(new Scene(root));
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
        }*/
        //Dictionary dictionary = new Dictionary();
        //dictionary.dictionaryManagement.insertFromFile();

        launch(args);
    }
}
