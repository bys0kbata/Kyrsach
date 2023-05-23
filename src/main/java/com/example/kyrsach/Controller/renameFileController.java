package com.example.kyrsach.Controller;


import com.example.kyrsach.MainController;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;

public class renameFileController {

    @FXML // fx:id="buttonRename"
    private Button buttonRename; // Value injected by FXMLLoader
    Stage stage;
    @FXML // fx:id="textnamenew"
    private TextField textnamenew; // Value injected by FXMLLoader
    public String textnamenewval;
    String starName;
    /*public renameFileController(String starName)
    {
    this.starName = starName;
    }*/
    String renameOne;
    public renameFileController(String renameOne){
        this.renameOne = renameOne;
    }
    @FXML
    void initialize() {
        buttonRename.setOnMouseMoved(
                mouseEvent -> {
                    buttonRename.setStyle("-fx-background-color: black; -fx-border-radius: 20; -fx-background-radius: 20; -fx-border-color: white;");
                }
        );
        buttonRename.setOnAction(ActionEvent -> {
            textnamenewval = textnamenew.getText();
            System.out.println();
            System.out.println(textnamenewval);
            Stage stage = (Stage) buttonRename.getScene().getWindow();
            stage.close();

        });
        buttonRename.setOnMouseExited(
                mouseEvent -> {
                    buttonRename.setStyle("-fx-background-color: white; -fx-border-radius: 20; -fx-background-radius: 20; -fx-border-color: #464451;");
                }
        );
    }
}

