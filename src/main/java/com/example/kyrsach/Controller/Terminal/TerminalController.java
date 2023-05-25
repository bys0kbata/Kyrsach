package com.example.kyrsach.Controller.Terminal;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class TerminalController {

        @FXML
        private ResourceBundle resources;
        @FXML
        private URL location;

        @FXML
        private TextField ComandTerminal;

        @FXML
        private Button CompBut;

        @FXML
        private MenuItem HelpBut;
        @FXML
        private ListView<String> ListTerminalView;
        ObservableList<String> list = FXCollections.observableArrayList();
        String textComandTerminal;
        String textComandTerminalf;
        String[] words;
        @FXML
        public void getComandTerminal() {
               textComandTerminal = ComandTerminal.getText();
               words = textComandTerminal.split(" ");
               ProcessList();
               System.out.println(textComandTerminal);
        }
        public void ProcessList(){
                try {
                        textComandTerminalf = null;
                        list.clear();
                        String process;
                        Process p = Runtime.getRuntime().exec(textComandTerminal);
                        BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
                        process = input.readLine();
                        while ((process = input.readLine()) != null) {
                                byte[] arr = process.getBytes("UTF-8");
                                process = new String (arr, "utf8");
                                list.add(process);// Выписывает результат по строчно
                                System.out.println(process);
                        }
                        input.close();
                        ListTerminalView.setItems(list);

                } catch (Exception err) {
                        String process = "Не удалось выполнить команду.";
                        list.add(process);
                        ListTerminalView.setItems(list);
                        err.printStackTrace();
                }
        }
        @FXML
        void initialize() {


        }

}

