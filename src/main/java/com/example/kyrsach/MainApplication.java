package com.example.kyrsach;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 985, 509);
        stage.setTitle("Superapp");
        stage.setResizable(false);
        stage.getIcons().add(new Image( MainApplication.class.getResourceAsStream("/com/example/kyrsach/Content/AvatarApp.png")));
        stage.setScene(scene);
        stage.show();
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.isControlDown() && event.isAltDown())
                {
                    System.out.println("Мем");
                }
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }
}