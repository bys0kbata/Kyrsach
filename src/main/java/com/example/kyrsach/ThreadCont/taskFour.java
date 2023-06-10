
    package com.example.kyrsach.ThreadCont;

import com.example.kyrsach.MainApplication;
import com.example.kyrsach.Metod.openWindows;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;

import java.io.*;
import java.util.Objects;
import java.util.concurrent.Semaphore;

    public class taskFour extends Thread {
        String PID;
        Semaphore sem;
        ObservableList<String> list = FXCollections.observableArrayList();
        String valSizeWH;
        String valstime;
        Label lel;
        openWindows open = new openWindows();
        public taskFour(){}
        public taskFour(String PID, Semaphore sem, Label lel){
            this.PID = PID;
            this.sem = sem;
            this.lel = lel;
        }
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
                System.out.println("Я пришел: "+ PID);
                System.out.println(getStime());
                System.out.println(getCountThread());
                System.out.println(getSizeWinApp());
                System.out.println("Я вышел: " + PID);
                Thread.sleep(500);
                System.out.println("Пока");
                sem.release();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
