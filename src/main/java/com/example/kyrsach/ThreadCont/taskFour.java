
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

        public taskFour() {
        }

        public taskFour(String PID, Semaphore sem) {
            this.PID = PID;
            this.sem = sem;
        }

        public String getStime() {
            valstime = "Время старта процесса: " + MainApplication.getTimeStart();
            return valstime;
        }

        public String getSizeWinApp() {
            return valSizeWH = "Размер окна приложения: " + MainApplication.getSizeW() + " " + MainApplication.getSizeH();
        }

        public String getCountThread() {
            return "Количество потоков в ПО: " + Thread.getAllStackTraces().keySet().size();
        }
        public void write(){
            String val = getSizeWinApp()+"\n "+getStime()+ "\n "+getCountThread();
            try(FileWriter writer = new FileWriter("Kyrsovay/System/Log/TaskWin.txt", false))
            {
                // запись всей строки
                writer.write(val);
                writer.flush();
            }
            catch(IOException ex){

                System.out.println(ex.getMessage());
            }
        }

        @Override
        public void run() {
            try {
                sem.acquire();
                write();
                sem.release();
                interrupt();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
