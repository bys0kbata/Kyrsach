package com.example.kyrsach.ThreadCont;

import com.example.kyrsach.Metod.metodFile;

import java.io.File;

public class FindFileThread extends Thread {
    metodFile filemetode;
    File dir = new File("D:\\скрипты");
    String nameFile;
    File ResultFile;
    public  FindFileThread(String nameFile){
            this.nameFile = nameFile;
    }
    @Override
    public void run() {
        System.out.println("Пока");
        ResultFile = new File(filemetode.findFile(dir,nameFile).toURI());
    }
    public File getFiles(){
        return ResultFile;
    }
}
