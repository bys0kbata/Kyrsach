package com.example.kyrsach;

import com.example.kyrsach.Metod.metodFile;
import com.example.kyrsach.Metod.openWindows;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;


public class MainApplication extends Application {
    openWindows open = new openWindows();
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1078, 509);
        stage.setTitle("Superapp");
        stage.setResizable(false);
        stage.getIcons().add(new Image( MainApplication.class.getResourceAsStream("/com/example/kyrsach/Content/AvatarApp.png")));
        stage.setScene(scene);
        stage.show();
        //Реализация горячих кнопок
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.isControlDown() && event.getCode() == KeyCode.H)
                {
                    try {
                        open.openWindows("/com/example/kyrsach/Addfolders/helper agry.fxml", "Cправка", 323, 172);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else if (event.isControlDown() && event.getCode() == KeyCode.T)
                {
                    try {
                        open.openWindows("/com/example/kyrsach/Addfolders/Terminal.fxml", "Терминал", 566, 563);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                }else if (event.isControlDown() && event.getCode() == KeyCode.D)
                {
                    try {
                        open.openWindows("/com/example/kyrsach/Addfolders/Task.fxml", "Процессы", 600, 400);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }else if (event.isControlDown() && event.getCode() == KeyCode.O)
            {
                try {
                    open.openWindows("/com/example/kyrsach/Addfolders/About.fxml", "О программе", 357, 198);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }else if (event.isControlDown() && event.getCode() == KeyCode.E) {
                    metodFile file = new metodFile();
                    file.MessengerStandart();
                }else if (event.isControlDown() && event.getCode() == KeyCode.I) {
                    metodFile file = new metodFile();
                    try {
                        file.TerminalOpen();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }
}