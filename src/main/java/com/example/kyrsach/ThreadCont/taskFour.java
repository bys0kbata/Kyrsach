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
    public void Write(){ try(
            FileWriter fw = new FileWriter(new File("Kyrsovay/System/Log/Text4.txt"), true))
    {
        fw.write(getSizeWinApp()+ " "+ getCountThread()+ " "+ getCountThread()+" ");
        System.out.println("Successfully written data to the file");
    } catch (IOException e) {
        e.printStackTrace();
    }}
    public void Read(){ try(FileReader reader = new FileReader("Kyrsovay/System/Log/Text4.txt"))
    {
        // читаем посимвольно
        int c;
        while((c=reader.read())!=-1){

            System.out.print((char)c);
        }
    }
        catch(IOException ex){

        System.out.println(ex.getMessage());
    }}
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
            sem.release();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
    }
}
}

