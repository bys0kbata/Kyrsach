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
    public ArrayList<Long> listPID = new ArrayList<>();
    private Boolean protect(File file){
        System.out.println(root.getAbsolutePath());
        if (!Objects.isNull(file) && (file.toString().startsWith(new File(root+"/System").toString())
                ||file.toString().startsWith(new File(root+"/Trash").toString())))
        {
            Alert alert=new Alert(Alert.AlertType.ERROR,"Нельзя удалиь сиситемные папки", ButtonType.OK);
            alert.showAndWait();
            throw new RuntimeException("Нельзя удалить системные папки");
        }else {
            return true;
        }
    }

    public metodFile() {
    }
    public void moveFile(File copyFiles){
        File source=new File(copyFiles.toURI());
        if (protect(source)){
            String command = "mv " + source + " " + source;

            // Создание процесса для выполнения команды в терминале
            ProcessBuilder processBuilder = new ProcessBuilder("bash", "-c", command);

            // Запуск процесса и ожидание завершения
            try {
                Process process = processBuilder.start();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
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
    
    public void MessengerStandart(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");
        log.writeFile("Открыли локальный файловый менджер" );
        File file = fileChooser.showOpenDialog(null);
    }
    public void TerminalOpen() throws IOException {
        log.writeFile("Открыли локальный терминал");
        Process p = Runtime.getRuntime().exec("gnome-terminal");
        Write(p.pid(),"Терминал");
        System.out.println(p.pid());
    }
    public void GnomeControl() {
        try {
            log.writeFile("Открыли локальный контроллер сети");
            Process p =Runtime.getRuntime().exec("gnome-control-center");
            Write(p.pid(),"Контроллер сети");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void GnomeSystemMonitor() {
        try {
            log.writeFile("Открыли локальный системный мониторинг");
            Process p =Runtime.getRuntime().exec("gnome-system-monitor");
            Write(p.pid(),"Cистема мониторинга");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void GnomeLogs() {
        try {
            log.writeFile("Открыли локальный систему логов");
            Process p =Runtime.getRuntime().exec("gnome-logs");
            Write(p.pid(),"Cистема логов");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void NetworkManager() {
        try {
            log.writeFile("Открыли локальный интернет менеджер");
            Process p =Runtime.getRuntime().exec("nm-connection-editor");
            Write(p.pid(),"интернет менеджер");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void Write(Long Pid, String name){ try(
            FileWriter fw = new FileWriter(new File("Kyrsovay/System/Log/task.txt"), true))
    {
        fw.write(Pid+" "+ name+"\n");
        System.out.println("Successfully written data to the file");
    } catch (IOException e) {
        e.printStackTrace();
    }}
}
