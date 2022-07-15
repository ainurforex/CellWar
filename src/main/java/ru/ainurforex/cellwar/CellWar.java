package ru.ainurforex.cellwar;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CellWar extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CellWar.class.getResource("CellWar.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 1024);
        stage.setTitle("CellWar");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}