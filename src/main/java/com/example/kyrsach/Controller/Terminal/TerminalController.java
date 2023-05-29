package com.example.kyrsach.Controller.Terminal;

import com.example.kyrsach.Metod.openWindows;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.IOException;
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
        private Button CompBut1;
        @FXML
        private MenuItem HelpBut;
        @FXML
        private ListView<String> ListTerminalView;
        openWindows open = new openWindows();
        ObservableList<String> list = FXCollections.observableArrayList();
        String textComandTerminal;
        String[] words;
        @FXML
        public void getComandTerminal() {
               textComandTerminal = ComandTerminal.getText();
               words = textComandTerminal.split(" ");
               ProcessList();
               System.out.println(textComandTerminal);
        }
        @FXML
        public void clearComandTerminal() {
                list.clear();
        }
        public void ProcessList(){
                try {
                        //list.clear();
                        String process;
                        list.add("- - - Ваша команда: "+textComandTerminal+ " - - -");
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
                        String proces123 = "Команда выполнена";
                        list.add(proces123);
                        ListTerminalView.setItems(list);

                } catch (Exception err) {
                        String process = "Не удалось выполнить команду.";
                        list.add(process);
                        ListTerminalView.setItems(list);
                }
        }
        @FXML
        void initialize() {
                HelpBut.setOnAction(event -> {
                        try {
                                open.openWindows("/com/example/kyrsach/Addfolders/TerminalHelp.fxml", "Команды терминала", 420, 425);
                        } catch (IOException e) {
                                throw new RuntimeException(e);
                        }
                });

        }

}

