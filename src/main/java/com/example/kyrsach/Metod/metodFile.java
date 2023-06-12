package com.example.kyrsach.Metod;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;


public class metodFile {
    TextInputDialog text = new TextInputDialog();
    LogFile log = new LogFile();
    File root= new File("/home/denis/Документы/GitHub/Kyrsach/Kyrsovay");
    private String processPID;

    private Boolean protect(File file){
        System.out.println(root.getAbsolutePath());
        System.out.println(file.getAbsolutePath());
        if (!Objects.isNull(file) && (file.toString().startsWith(new File(root+"/System").toString())
                ||file.getAbsolutePath().equals(root.getAbsolutePath()+"/Trash")))
        {
            Alert alert=new Alert(Alert.AlertType.ERROR,"Нельзя удалить сиситемные папки", ButtonType.OK);
            alert.showAndWait();
            throw new RuntimeException("Нельзя удалить системные папки");
        }else {
            return true;
        }
    }

    public metodFile() {
    }
    alertMembers alert = new alertMembers();
    File FileVal;
    public void createFile(File name) throws IOException {
        text.setTitle("Cоздать документ");
        text.setHeaderText("Введите имя: ");
        text.showAndWait();
        log.writeFile("Создали файл с названием: "+ text.getEditor().getText() );
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
        log.writeFile("Переименовали файл с названием: "+ text.getEditor().getText());
        System.out.println(createPathFolder.getPath()+"/" + text.getEditor().getText());
        if(protect(createPathFolder)){
        if (text.getEditor().getText() != " " ||text.getEditor().getText() != null ) {
            FileVal = new File(createPathFolder.getPath().substring(0,createPathFolder.getPath().lastIndexOf("/")+1) + text.getEditor().getText());
            if (FileVal.exists()) {
                alert.membersError("Папка существует, не получится переименовать");
            } else {
                System.out.println("Файл переименовался");
                createPathFolder.renameTo(FileVal);
            }
        }
        }else {
            alert.membersError("Нельзя переименовать папку");
        }
    }
    public void deleteFullFile(String deleteVal)
    {
        if (protect(new File(deleteVal))){
            log.writeFile("Удалили объект с путем: "+ deleteVal);
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
    }
    public void createFolder(File PuthVal) throws IOException {
        text.setTitle("Cоздать папку");
        text.setHeaderText("Введите имя: ");
        text.showAndWait();
        log.writeFile("Создали папку с названием: "+ text.getEditor().getText() );
        FileVal = new File(PuthVal.getPath() + "/" + text.getEditor().getText());
        if (FileVal.exists()) {
            alert.membersError("Папка существует, не получится переименовать");
        } else {
            Process rename = Runtime.getRuntime().exec("mkdir " + PuthVal.getPath()+"/" + text.getEditor().getText());
        }
    }
    public void pasteFile(String copyFile, String valURL2)
    {
        log.writeFile("Вставили файл с путем: "+ valURL2 );
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
            log.writeFile("Открыли файл с путем: "+ puth );
        } catch (IOException ioe) {
            alert.membersError("Нет приложения для открытия такого файла.");
        }
    }
    public void deleteinBasket(String url) throws IOException {
        if (protect(new File(url))){
        File urlq = new File(url);
        log.writeFile("Удалили файл в корзину с названием: "+ urlq.getName() );
        Process command=Runtime.getRuntime().exec("mv "+ url +" "+root+"/Trash/"+urlq.getName());}
        else {
            alert.membersError("Нельзя удалить файл в корзину");
        }
    }
    public void returninBasket(String url) throws IOException {
        File urlq = new File(url);
        log.writeFile("Вернули файл с корзины с названием: "+ urlq.getName() );
        Process command=Runtime.getRuntime().exec("mv "+ url +" "+root+"/Restored objects/"+urlq.getName());}

    public void TerminalOpen() throws IOException {
        log.writeFile("Открыли локальный терминал");
        try{
            Process p =Runtime.getRuntime().exec("gnome-terminal");
            processPID= String.valueOf(p.pid());

            File file = new File("./Kyrsovay/System/Log/task.txt");
//create the file.
            if (file.createNewFile()) {
                System.out.println("File is created!");
            } else {
                System.out.println("File already exists.");
            }
            FileWriter writer = new FileWriter(file);
            writer.write("");
            writer.write(processPID);
            //write content
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void GnomeControl() {
            log.writeFile("Открыли локальный контроллер сети");
            try{
            Process p =Runtime.getRuntime().exec("gnome-control-center");
                processPID= String.valueOf(p.pid());

                File file = new File("./Kyrsovay/System/Log/task.txt");
//create the file.
                if (file.createNewFile()) {
                    System.out.println("File is created!");
                } else {
                    System.out.println("File already exists.");
                }
                FileWriter writer = new FileWriter(file);
                writer.write("");
                writer.write(processPID);
                //write content
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    public void GnomeSystemMonitor() {
            log.writeFile("Открыли локальный системный мониторинг");
            try {
                Process process = Runtime.getRuntime().exec("gnome-system-monitor");
                processPID= String.valueOf(process.pid());

                File file = new File("./Kyrsovay/System/Log/task.txt");
//create the file.
                if (file.createNewFile()) {
                    System.out.println("File is created!");
                } else {
                    System.out.println("File already exists.");
                }
                FileWriter writer = new FileWriter(file);
                writer.write("");
                writer.write(processPID);
                //write content
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    public void GnomeLogs() {
            log.writeFile("Открыли локальный систему логов");
            try {
                Process process = Runtime.getRuntime().exec("gnome-logs");
                processPID= String.valueOf(process.pid());

                File file = new File("./Kyrsovay/System/Log/task.txt");
//create the file.
                if (file.createNewFile()) {
                    System.out.println("File is created!");
                } else {
                    System.out.println("File already exists.");
                }
                FileWriter writer = new FileWriter(file);
                writer.write("");
                writer.write(processPID);
                //write content
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    public void NetworkManager() {
            log.writeFile("Открыли локальный интернет менеджер");
            try {
                Process process = Runtime.getRuntime().exec("gnm-connection-editor");
                processPID= String.valueOf(process.pid());

                File file = new File("./Kyrsovay/System/Log/task.txt");
//create the file.
                if (file.createNewFile()) {
                    System.out.println("File is created!");
                } else {
                    System.out.println("File already exists.");
                }
                FileWriter writer = new FileWriter(file);
                writer.write("");
                writer.write(processPID);
                //write content
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    public void MessengerStandart() {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("System File Manager");
            File file = fileChooser.showOpenDialog(null);
    }
}
