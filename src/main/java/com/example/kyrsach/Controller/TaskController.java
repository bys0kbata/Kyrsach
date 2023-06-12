package com.example.kyrsach.Controller;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import com.example.kyrsach.Metod.metodFile;
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
    metodFile met = new metodFile();
    ArrayList<String> fileLinesList = new ArrayList<>();

    private void readFile(){
        File file = new File("./Kyrsovay/System/Log/task.txt"); // Укажите путь к вашему файлу
        try {
            fileLinesList.clear();
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()){
                fileLinesList.add(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private void isProcessRunning() {

        try {

            for (int i = 0; i < fileLinesList.size(); i++) {
                System.out.println(fileLinesList.get(i));
                Process command = new ProcessBuilder("ps", "-p", fileLinesList.get(i)).start();
                command.waitFor(20, TimeUnit.SECONDS);

                String process;
                BufferedReader input = new BufferedReader(new InputStreamReader(command.getInputStream()));
                process = input.readLine();
                process=input.readLine();
                if (command.exitValue() ==0){
                    list.add(process);
                }
                while (process != null) {
                    System.out.println(process);
                    process = input.readLine();
                }
                System.out.println(command.exitValue());
            }
        } catch (Exception  e) {
            e.printStackTrace();
        }
    }
//    public void Read(){ try(FileReader reader = new FileReader("Kyrsovay/System/Log/task.txt"))
//    {
//        // читаем посимвольно
//        int c;
//        while((c=reader.read())!=-1){
//            // Выписывает результат по строчно
//            r +=(char)c;
//            System.out.print((char)c);
//        }
//        list.add(r);
//        Files.writeString(Path.of(new File("Kyrsovay/System/Log/task.txt").getAbsolutePath()), (CharSequence) "");
//    }
//    catch(IOException ex){
//
//        System.out.println(ex.getMessage());
//    }}
    public void ProcessList(){
        try {
            list.clear();
            String process;
            Process p = Runtime.getRuntime().exec("ps -C java -o pid,user,lstart,cmd");
            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            process = input.readLine();
            while ((process = input.readLine()) != null) {
                list.add(process);// Выписывает результат по строчно
            }
            input.close();
            readFile();
            isProcessRunning();
            listTask.setItems(list);
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
