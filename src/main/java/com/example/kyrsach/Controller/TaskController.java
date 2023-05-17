package com.example.kyrsach.Controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableArrayBase;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

public class TaskController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    @FXML
    private Button ButtonUpdate;
    @FXML
    private ListView<String> listTask;
    ObservableList<String> list = FXCollections.observableArrayList();
    public void ProcessList(){
        try {
            list.clear();
            String process;
            Process p = Runtime.getRuntime().exec("tasklist");
            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));process = input.readLine();
            while ((process = input.readLine()) != null) {
                list.add(process);// Выписывает результат по строчно
            }
            input.close();
            listTask.setItems( list);
        } catch (Exception err) {
            err.printStackTrace();
        }
    }

    @FXML
    void initialize() {
        ProcessList();
        ButtonUpdate.setOnAction(actionEvent -> {ProcessList();});
    }
}
