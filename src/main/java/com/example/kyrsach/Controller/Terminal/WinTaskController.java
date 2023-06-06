package com.example.kyrsach.Controller.Terminal;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.kyrsach.ThreadCont.SemaphoreOS;
import com.example.kyrsach.ThreadCont.taskFour;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class WinTaskController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button primary;

    @FXML
    private Label sizeWine;

    @FXML
    private Label stimeps;

    @FXML
    private Label valThread;
    SemaphoreOS sem = new SemaphoreOS(stimeps);
    taskFour task = new taskFour();

    @FXML
    void initialize() {

    }

    public void getVal(javafx.event.ActionEvent actionEvent) {
                stimeps.setText(task.getStime());
                sizeWine.setText(task.getSizeWinApp());
               valThread.setText(task.getCountThread());
    }
}

