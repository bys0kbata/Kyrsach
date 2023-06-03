package com.example.kyrsach.Metod;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;

public class Fleashka {
    static ArrayList<String> arrayList = new ArrayList<String>();

    public static void searchFiles(String fileName, File directory) {
        File[] files = directory.listFiles();
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

        public static void main(String[] args) {
            Fleashka fileSearch = new Fleashka();
            String fileName = "test.txt";
            File directory = new File("D:\\");
            searchFiles(fileName, directory);
            for(String i :arrayList){
                System.out.println(i);
            }
        }
    }

