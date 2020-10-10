package app;

import helper.GoogleAPI;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        try {
            //dictionary.dictionaryManagement.insertFromFile();
            Parent mainController = FXMLLoader.load(getClass().getResource("../interface/MainController.fxml"));
            //mainStage.getChildrenUnmodifiable(translateStage);
            primaryStage.setTitle("Dictionary");
            //Scene mainScene = new Scene(mainController);
            primaryStage.setScene(new Scene(mainController));
            primaryStage.show();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public static void main(String[] args) {
        /*try {
            System.out.println(GoogleAPI.translate("en", "vi", "hello"));
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        launch(args);
    }
}
