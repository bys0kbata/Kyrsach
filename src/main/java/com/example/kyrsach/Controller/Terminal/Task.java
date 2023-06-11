package com.example.kyrsach.Controller.Terminal;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

public class Task extends Application {
    String  size = " ";


    public void start(Stage primaryStage) throws IOException {
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefWidth(411);
        anchorPane.setPrefHeight(173);
        anchorPane.setStyle("-fx-background-color: #B5B8B1; -fx-border-color: #464451;");

        AnchorPane innerPane = new AnchorPane();
        innerPane.setLayoutX(7);
        innerPane.setLayoutY(8);
        innerPane.setPrefWidth(396);
        innerPane.setPrefHeight(158);
        innerPane.setStyle("-fx-background-color: white; -fx-border-color: #464451; -fx-background-radius: 20; -fx-border-radius: 20;");

        Label titleLabel = new Label("Окно по заданию");
        titleLabel.setLayoutX(100);
        titleLabel.setLayoutY(14);
        titleLabel.setPrefWidth(159);
        titleLabel.setPrefHeight(27);
        titleLabel.setFont(new Font(18));

        Label sizeLabel = new Label("Размеры окна приложения: 00x00 \nВремя старта процесса: 00:00:00 \nКоличество всего потоков: 0 ");
        sizeLabel.setLayoutX(29);
        sizeLabel.setLayoutY(60);
        getReaderVal();
        sizeLabel.setText(size);
        size = " ";
        innerPane.getChildren().addAll(titleLabel, sizeLabel);
        anchorPane.getChildren().add(innerPane);

        Scene scene = new Scene(anchorPane);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void getReaderVal() throws IOException {
        try(FileReader reader = new FileReader("Kyrsovay/System/Log/TaskWin.txt"))
        {
            // читаем посимвольно
            int c;
            while((c=reader.read())!=-1){

                 size += (char) c;
                System.out.println((char) c);
            }
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
        Path path = Paths.get("Kyrsovay/System/Log/TaskWin.txt");
        Files.writeString(path, "");
    }

    public static void main(String[] args) {
        launch(args);
    }
}