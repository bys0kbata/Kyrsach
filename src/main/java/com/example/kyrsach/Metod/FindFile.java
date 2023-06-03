package com.example.kyrsach.Metod;

import java.io.File;
import java.util.ArrayList;

public class FindFile {
    static ArrayList<String> arrayList = new ArrayList<String>();
    public void searchFiles(String fileName, File directory) {
        File[] files = directory.listFiles();
       new Thread(new Runnable() {
           @Override
           public void run() {
               if (files != null) {
                   for (File file : files) {
                       if (file.isDirectory()) {
                           searchFiles(fileName, file);
                       } else if (file.getName().equalsIgnoreCase(fileName)) {
                           System.out.println("File found: " + file.getAbsolutePath());
                           arrayList.add(file.getAbsolutePath());
                       }
                   }
               }
           }
       }).start();
    }

    public  void findfile() {
        Fleashka fileSearch = new Fleashka();
        String fileName = "test";
        File directory = new File("D:\\");
        fileSearch.searchFiles(fileName, directory);
        for(String i :arrayList){
            System.out.println(i);
        }
    }
    public ArrayList<String> getArrayList(){
        return arrayList;

    }
}
