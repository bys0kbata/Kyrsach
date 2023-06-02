package com.example.kyrsach.Metod;

import java.io.File;

public class FindFile {
    public void searchFiles(String fileName, File directory) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    searchFiles(fileName, file);
                } else if (file.getName().equalsIgnoreCase(fileName)) {
                    System.out.println("File found: " + file.getAbsolutePath());
                }
            }
        }
    }

    public static void findfile() {
        Fleashka fileSearch = new Fleashka();
        String fileName = "test";
        File directory = new File("D:\\");
        fileSearch.searchFiles(fileName, directory);
    }
}
