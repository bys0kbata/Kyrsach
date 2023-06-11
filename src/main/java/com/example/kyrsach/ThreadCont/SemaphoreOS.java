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
    ThreadOne task2 = new ThreadOne(sem);
    openWindows open = new openWindows();
    String sys;

    public void getSemaphoreO() throws IOException {
        task.run();
        task2.run();
    }

}
