package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        try {
            Parent mainController = FXMLLoader.load(getClass().getResource("../interface/MainController.fxml"));
            primaryStage.setTitle("Dictionary");
            primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("../data/icons/AppIcon.png")));
            primaryStage.setScene(new Scene(mainController));
            primaryStage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
