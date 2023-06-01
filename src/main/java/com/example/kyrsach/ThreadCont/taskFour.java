package com.example.kyrsach.ThreadCont;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class taskFour {
    public String timedatestart;
    String PID;
    ObservableList<String> list = FXCollections.observableArrayList();

    public void getStime(){
        try {
            String process;
            Process p =  Runtime.getRuntime().exec("ps -ef -o stime -c "+PID);
            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            process = input.readLine();
            while ((process = input.readLine()) != null) {
                byte[] arr = process.getBytes("UTF-8");
                process = new String (arr, "utf8");
                list.add(process);// Выписывает результат по строчно
                System.out.println(process);
            }
            input.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void getSizeWinApp(){
        try {
            String process;
            Process p =  Runtime.getRuntime().exec("ps hH p"+ PID +" | wc -l\n");
            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            process = input.readLine();
            while ((process = input.readLine()) != null) {
                byte[] arr = process.getBytes("UTF-8");
                process = new String (arr, "utf8");
                list.add(process);// Выписывает результат по строчно
                System.out.println(process);
            }
            input.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void getCountThread(){

    }
    

}
