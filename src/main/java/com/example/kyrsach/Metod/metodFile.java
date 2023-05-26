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
        text.setTitle("Cоздать документ");
        text.setHeaderText("Введите имя: ");
        text.showAndWait();
        FileVal = new File(name.getPath().substring(0, name.getPath().lastIndexOf("/")) + text.getEditor().getText());
        System.out.println(name.getPath().substring(0, name.getPath().lastIndexOf("/")) + text.getEditor().getText());
        if (FileVal.exists()) {
            alert.membersError("Папка существует, не получится переименовать");
        } else {
            Process rename = Runtime.getRuntime().exec("touch " + name.getPath()+"/" + text.getEditor().getText());
        }
    }
    public void renameFile(File createPathFolder) throws IOException {
        text.setTitle("Переименовать документ");
        text.setHeaderText("Введите имя: ");
        text.showAndWait();
        if (text.getEditor().getText() != " " ||text.getEditor().getText() != null ) {
            FileVal = new File(createPathFolder.getPath()+"/" + text.getEditor().getText());
            if (FileVal.exists()) {
                alert.membersError("Папка существует, не получится переименовать");
            } else {
                System.out.println("mv " + createPathFolder.getPath() + " " + FileVal.getAbsolutePath());
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
    public void createFolder(File PuthVal) throws IOException {
        text.setTitle("Cоздать папку");
        text.setHeaderText("Введите имя: ");
        text.showAndWait();
        FileVal = new File(PuthVal.getPath() + "/" + text.getEditor().getText());
        if (FileVal.exists()) {
            alert.membersError("Папка существует, не получится переименовать");
        } else {
            Process rename = Runtime.getRuntime().exec("mkdir " + PuthVal.getPath()+"/" + text.getEditor().getText());
        }
    }
    public void searchfile(){

    }
    public void pasteFile(String copyFile, String valURL2)
    {
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    if (new File(copyFile).isFile()) {
                        Process p = Runtime.getRuntime().exec("cp " + copyFile+ " "+ valURL2);
                    } else {
                        if((new File(copyFile)).isDirectory()){
                            Process p = Runtime.getRuntime().exec("cp -rf " + copyFile+ " "+ valURL2);
                        }
                    }
                } catch(IOException e) {
                    throw new RuntimeException(e);
                }
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
