package com.example.kyrsach.Metod;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;

import java.util.Optional;

public class alertMembers {
    public Alert alert = new Alert(Alert.AlertType.ERROR);
    public void membersError(String titlename) {
        alert.setTitle("ERROR Dialog");
        alert.setHeaderText(titlename);
        //alert.setContentText("Измените путь файла");

        Optional<ButtonType> result = alert.showAndWait();
    }

}
