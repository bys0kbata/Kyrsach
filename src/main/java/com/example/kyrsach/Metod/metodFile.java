package com.example.kyrsach.Metod;

import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class metodFile {
    TextInputDialog text = new TextInputDialog();

    public metodFile() {
    }
    alertMembers alert = new alertMembers();
    File FileVal;
    public void createFile(File name) throws IOException {
        File f = new File(name.getPath().toString() + "//Новый Файл".toString());
        if (f.createNewFile())
            System.out.println("File created");
        else
            System.out.println("File already exists");
    }
    public void createFolder(File createPathFolder) throws IOException {
        text.setTitle("Переименовать документ");
        text.setHeaderText("Введите имя: ");
        text.showAndWait();
        if (text.getEditor().getText() != " ") {
            FileVal = new File(createPathFolder.getPath().substring(0, createPathFolder.getPath().lastIndexOf("/")) + text.getEditor().getText());
            if (FileVal.exists()) {
                alert.membersError("Файл существует");
            } else {
                Process rename = Runtime.getRuntime().exec("mv " + createPathFolder.getPath() + " " + FileVal.getAbsolutePath());
            }
        }
    }
    public void deleteFullFile(String deleteVal)
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (new File(deleteVal).isFile()) {
                        Process p = Runtime.getRuntime().exec("rm " + deleteVal);
                    } else {
                        if((new File(deleteVal)).isDirectory()){
                            Process p = Runtime.getRuntime().exec("rm -rf " + deleteVal);
                        }
                    }} catch(IOException e){
                        throw new RuntimeException(e);
                    }
                }
        }).start();
    }
    public void renameFile()
    {

    }
    public void searchfile(){

    }
    public void pasteFile(String copyFile)
    {
        new Thread(new Runnable() {

            @Override
            public void run() {

            }
        }).start();
    }

    public void openFile(String puth)
    {
        try {
            Process command=Runtime.getRuntime().exec("xdg-open "+puth);
        } catch (IOException ioe) {
            alert.membersError("Нет приложения для открытия такого файла.");
        }
    }

    public File findFile(File dir, String name) {
        File result = null; // no need to store result as String, you're returning File anyway
        File[] dirlist  = dir.listFiles();

        for(int i = 0; i < dirlist.length; i++) {
            if(dirlist[i].isDirectory()) {
                result = findFile(dirlist[i], name);
                if (result!=null) break; // recursive call found the file; terminate the loop
            } else if(dirlist[i].getName().matches(name)) {
                return dirlist[i]; // found the file; return it
            }
        }
        return result; // will return null if we didn't find anything
    }
}
