package com.example.kyrsach.Metod;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class metodFile {

    public metodFile() {
    }
    alertMembers alert = new alertMembers();
    File FileVal;
    public void createFile(File name) throws IOException {
        File f = new File(name.getPath().toString() + "//Новый Файл".toString());
        //Создайте новый файл
        // Убедитесь, что он не существует
        if (f.createNewFile())
            System.out.println("File created");
        else
            System.out.println("File already exists");
    }
    public void createFolder(File createPathFolder) throws IOException {
        FileVal = new File(createPathFolder.getPath()+"//Новая папка");
        FileVal.mkdir();
    }
    public void deleteFile()
    {

    }
    public void deleteFolder()
    {

    }
    public void renameFile()
    {

    }
    public void renameFolder()
    {

    }
    public void copeFile()
    {

    }

    public void openFile(String puth)
    {
        try {
            Desktop desktop = null;
            Desktop Desktop;
            Desktop = null;
            if (Desktop.isDesktopSupported()) {
                desktop = Desktop.getDesktop();
            }

            desktop.open(new File(puth));
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
