package com.example.kyrsach.ThreadCont;

import com.example.kyrsach.MainApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.concurrent.Semaphore;

public class taskFour extends Thread {
    public String timedatestart;
    String PID;
    Semaphore sem;
    ObservableList<String> list = FXCollections.observableArrayList();

    ProcessBuilder proc = new ProcessBuilder();

    String valSizeWH;


    public void getStime() {
        }



    public String getSizeWinApp(){
        return valSizeWH = MainApplication.getSizeW()+" "+MainApplication.getSizeH();
    }
    public void getCountThread(){
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

    @Override
    public void run() {
        try {
            sem.acquire();

            sem.release();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
