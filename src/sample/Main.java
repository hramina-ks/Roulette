package sample;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.control.*;

public class Main extends Application {

    @Override
    public void start (Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Рулетка");
        primaryStage.setWidth(800);
        primaryStage.setHeight(650);
        primaryStage.setScene(new Scene(root, primaryStage.getWidth(), primaryStage.getHeight()));
        primaryStage.getIcons().add(new Image("roulette.png"));
        primaryStage.setResizable(false);
        primaryStage.show();

    }

    public static void main(String[] args) {
        Application.launch();
    }
}