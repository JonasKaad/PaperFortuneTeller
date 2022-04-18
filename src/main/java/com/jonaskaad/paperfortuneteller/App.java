package com.jonaskaad.paperfortuneteller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("primary.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
        stage.setTitle("PROSA - Nip Napper / Flip Flapper");
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
        stage.getIcons().add(new Image(this.getClass().getResourceAsStream("images/nipnap.png")));
    }

    public static void main(String[] args) {
        launch();
    }
}