package com.example.kyrsach.ThreadCont;
import com.example.kyrsach.Metod.openWindows;
import javafx.scene.control.Label;

import java.io.IOException;
import java.util.concurrent.Semaphore;

public class SemaphoreOS extends Thread {
    Semaphore sem = new Semaphore(1);
    Label lel;
    public SemaphoreOS(){}
    public SemaphoreOS(Label lel){
        this.lel = lel;
    }
    taskFour task = new taskFour("Первый", sem);
    taskFour task2 = new taskFour("Первый2", sem);
    openWindows open = new openWindows();
    String sys;

    public void getSemaphoreO() throws IOException {
        Semaphore sem = new Semaphore(1);
        open.openWindows("/com/example/kyrsach/Addfolders/WinTask.fxml","Окно по заданию", 411,173);

        task.start();
        System.out.println(task.getName());
        task2.start();

        System.out.println(task2.valstime);
    }

}
