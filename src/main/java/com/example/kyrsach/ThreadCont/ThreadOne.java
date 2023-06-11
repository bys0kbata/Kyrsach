package com.example.kyrsach.ThreadCont;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.Semaphore;

public class ThreadOne extends Thread {
    Semaphore sem;
    public ThreadOne(Semaphore sem){
        this.sem = sem;
    }
    public void TaskFo() {
        ProcessBuilder pb = new ProcessBuilder();
        String javaExecutable = System.getProperty("java.home") + File.separator + "bin" + File.separator + "java";
        String pathToJarFile = "./out/artifacts/Kyrsach_jar/Kyrsach.jar";
        String classpath = new String("./lib/javafx-sdk-17.0.2/lib/"); // Замените на реальный путь к JavaFX SDK
        System.out.println(javaExecutable);
        pb.command(javaExecutable, "--module-path", classpath, "--add-modules", "javafx.controls,javafx.fxml", "-jar", pathToJarFile, "myArg", "123");
        System.out.println(pb.command());
        File log = new File("log" + 0 + ".txt"); //debug log for started process
        try {
            pb.redirectOutput(log);
            pb.redirectError(log);
            pb.start();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    @Override
    public void run() {
        try {
            sem.acquire();
            TaskFo();
            sem.release();
            interrupt();
    } catch (InterruptedException e) {
        throw new RuntimeException(e);
    }
    }
}
