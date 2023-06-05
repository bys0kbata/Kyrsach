package com.example.kyrsach.ThreadCont;

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
    public taskFour(){
    }
    public void getPID(){

    }

    public void getStime(){
        try {

            ProcessBuilder pb = new ProcessBuilder("ps","-ef");
            Process process = pb.start();
            String s;

            BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));

            while ((s = stdInput.readLine()) != null) {

                System.out.println(s);
                String str = new String(s.getBytes("Cp1251"),"Cp866");  //преобразование строки
                System.out.println(str);
                stdInput.close();

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static String asString(InputStream is, String charsetName ) throws IOException {
        ByteArrayOutputStream temp = new ByteArrayOutputStream();

        byte[] buffer = new byte[1024];
        int bytesRead;
        while ( (bytesRead = is.read( buffer )) > 0 ) {
            temp.write( buffer, 0, bytesRead );
        }

        return temp.toString( charsetName );
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
