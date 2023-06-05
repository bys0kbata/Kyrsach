package com.example.kyrsach.ThreadCont;

import com.example.kyrsach.MainApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.Objects;
import java.util.concurrent.Semaphore;

public class taskFour extends Thread {
    public String timedatestart;
    String PID;
    Semaphore sem;
    ObservableList<String> list = FXCollections.observableArrayList();
    String valSizeWH;
    String valstime;
    public String getStime() {
        valstime = "Время старта процесса: "+ MainApplication.getTimeStart();
        return valstime;
        }
    public String getSizeWinApp(){
        return valSizeWH = "Размер окна приложения: "+MainApplication.getSizeW()+" "+MainApplication.getSizeH();
    }
    public String getCountThread(){
        return "Количество потоков в ПО: "+ Thread.getAllStackTraces().keySet().size();
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
