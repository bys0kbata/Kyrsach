package com.example.kyrsach.Metod;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;

public class Fleashka {
    ArrayList<String> arrayList = new ArrayList<>();

    public String[] searchFiles(String fileName, File directory,ArrayList<String> arr) {
        String[] udfile = new String[0];
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    searchFiles(fileName, file,arr);
                } else if (file.getName().equalsIgnoreCase(fileName)) {
                    System.out.println("File found: " + file.getAbsolutePath());
                    arr.
                }
            }
        }
        return udfile;
    }

        public static void main(String[] args) {
            Fleashka fileSearch = new Fleashka();
            String fileName = "tet";
            File directory = new File("D:\\");
            System.out.println(fileSearch.searchFiles(fileName, directory)[0]);
        }
    }

