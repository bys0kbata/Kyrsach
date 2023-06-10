package com.example.kyrsach.Metod;


import com.example.kyrsach.MainApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;



public class openWindows {
    public LogFile log =   new LogFile();
    public void openWindows(String url, String text, int sizeOne,int sizeTwo) throws IOException {
        log.writeFile("Открыли окно " + text);
        Parent root;
        Stage stage = new Stage();
        root = FXMLLoader.load(getClass().getResource(url));
        Scene scene = new Scene(root,sizeOne,sizeTwo);
        stage.setResizable(false);
        stage.setTitle(text);
        stage.getIcons().add(new Image(MainApplication.class.getResourceAsStream("/com/example/kyrsach/Content/AvatarApp.png")));
        stage.setScene(scene);
        stage.show();
    }
}
