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
<<<<<<< Updated upstream
    ProcessBuilder proc = new ProcessBuilder();
    public taskFour(){
=======
    String valSizeWH;
    public taskFour(Semaphore sem,String PID){
        this.PID = PID;
        this.sem = sem;
>>>>>>> Stashed changes
    }
    public void getPID(){

    }

    public void getStime(){
        try {
<<<<<<< Updated upstream

            ProcessBuilder pb = new ProcessBuilder("ps","-ef");
            Process process = pb.start();
            String s;

            BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));

            while ((s = stdInput.readLine()) != null) {

                System.out.println(s);
                String str = new String(s.getBytes("Cp1251"),"Cp866");  //преобразование строки
                System.out.println(str);
                stdInput.close();

=======
            String process;
            Process p =  Runtime.getRuntime().exec("ps -ef -o stime -c "+ PID);
            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            process = input.readLine();
            while ((process = input.readLine()) != null) {
                byte[] arr = process.getBytes("UTF-8");
                process = new String (arr, "utf8");
                list.add(process);// Выписывает результат по строчно
                System.out.println(process);
>>>>>>> Stashed changes
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
<<<<<<< Updated upstream
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
=======
    public String getSizeWinApp(){
        return valSizeWH = MainApplication.getSizeW()+" "+MainApplication.getSizeH();
    }
    public void getCountThread(){
>>>>>>> Stashed changes
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
