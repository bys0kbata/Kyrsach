package com.example.kyrsach;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.concurrent.Semaphore;

import com.example.kyrsach.Metod.*;
import com.example.kyrsach.ThreadCont.SemaphoreOS;
import com.example.kyrsach.ThreadCont.taskFour;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.*;
import javafx.stage.Stage;


public class MainController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    /**
     * Обьявление кнопок для Меню.
     */
    @FXML
    private MenuItem buttonAbout; //Кнопка из меню "О программе"

    @FXML
    private MenuItem WinTest;
    @FXML
    private MenuItem buttonAgry;//Кнопка из меню "О программе"
    @FXML
    private MenuItem buttonStartTask;//Кнопка из меню "Запуск Диспетчера задач"
    @FXML
    private MenuItem buttonStartTerminal;//Кнопка из меню "Запуск Терминала"
    /**
     * Кнопки связанные с адресом
     */
    @FXML
    private Button backButton;//Кнопка назад
    @FXML
    private Button buttonFind;//Кнопка поиска по имени файла
    @FXML
    private Button buttonOpen;//Кнопка для открытия пути файла
    @FXML
    private Button forwatdButton;//Кнопка вперед
    /**
     * Все использующиеся поля для работы в суперапп
     */
    @FXML
    private TextField fieldURL;//Поле для заполнения адреса
    @FXML
    private TextField fieldURL1;//Поле для поиска по имени

    /**
     * Реализация Вывода файла в дерево
     */
    @FXML
    private TreeView<File> treeFile;
    /**
     * Реализация Вывода файла в таблицвью
     */
    @FXML
    private TableColumn<nameTable, String> oneColumnTableView;

    @FXML
    private TableView<nameTable> tableView;

    @FXML
    private TableColumn<nameTable, String> twoColumnTableView;
    @FXML
    private TableColumn<nameTable, Date> freeColumnTableView;
    @FXML
    private TableColumn<nameTable, String> foureColumnTableView;
    @FXML
    private TableColumn<nameTable, String> sixColumnTableView;
    /**
     * Переменные для функций и сами необходимые функций.
     */
    LogFile log = new LogFile();
    Stage stage = new Stage();
    openWindows open = new openWindows();
    File MainFolder = new File("Kyrsovay//");
    public String dir = MainFolder.getAbsolutePath();
    public  String renameVal;
    public String copyVal;
    public String copyNameVal;
    ArrayList<String> histiryURL = new ArrayList<>(100000);
    metodFile filemetod = new metodFile();
     ContextMenu contextMenu = new ContextMenu();
    ContextMenu contextMenuTrash = new ContextMenu();
    ContextMenu contextMenuSystem = new ContextMenu();
     //Cоздание контекстного меню, который будет выплываться через правую кнопку мыши
     //Создание элемента в контекстном меню с названием.
      MenuItem menuCreateDir = new MenuItem("Создать Директорию");
      MenuItem menuRestored = new MenuItem("Восстановить");
      MenuItem menuCreateFile = new MenuItem("Создать Файл");
      MenuItem menuRename= new MenuItem("Переименовать");
      MenuItem menuDelete = new MenuItem("Удалить");
      MenuItem menuСopy = new MenuItem("Копировать");
      MenuItem menuPaste = new MenuItem("Вставить");
      MenuItem menuDeleteBasket = new MenuItem("Удалить в Корзину");
      SemaphoreOS semOS = new SemaphoreOS();
    @FXML
    private MenuItem FSLocal;

    @FXML
    private MenuItem TerminalLocal;

    ObservableList<nameTable> dirname2 = FXCollections.observableArrayList();
    alertMembers alert = new alertMembers();
    FindFile FindThread = new FindFile();
    private System Logger;

    private void initData(String nameFile, String sizeFile, Date lastOperationFile, String typeFile, String puth) {
        dirname2.add(new nameTable(nameFile, sizeFile, lastOperationFile, typeFile, puth));
    }
    /**
     * Метод openFileandReadFile() присылает все необходимые значение в treeview и tableview
     * и реализует все необходимые для него модификаций.
     */
    TreeItem<File> romans = new TreeItem<File>(new File("Superapp"));

    public void openFileandReadFile2() {
        Date date;
        String dirname = null;
        String type;
        String size12;
        File[] files = new File(dir).listFiles();
        try {
            for (File file : files) {
                dirname = file.toString();
                type = dirname.substring(dirname.lastIndexOf(".") + 1, dirname.length());
                if (dirname.lastIndexOf(".") == -1) {
                    type = "No type";
                }
                if (file.isDirectory() == true) {
                    type = "Folder";
                    size12 = "Folder";
                }
                date = new Date(file.lastModified());
                if (file.length() / 1024 / 1024 / 1024 > 1) {
                    size12 = file.length() / 1024 / 1024 / 1024 + " ГБ";
                } else if (file.length() / 1024 / 1024 > 1024) {
                    size12 = file.length() / 1024 / 1024 + "  МБ";
                } else {
                    size12 = file.length() / 1024 + " КБ";
                }
                initData(dirname.substring(dirname.lastIndexOf("\\") + 1), size12, date, type, file.getPath());

            }
            oneColumnTableView.setCellValueFactory(new PropertyValueFactory<nameTable, String>("nameFile"));
            twoColumnTableView.setCellValueFactory(new PropertyValueFactory<nameTable, String>("sizeFile"));
            freeColumnTableView.setCellValueFactory(new PropertyValueFactory<nameTable, Date>("lastOperationFile"));
            foureColumnTableView.setCellValueFactory(new PropertyValueFactory<nameTable, String>("typeFile"));
            sixColumnTableView.setVisible(false);
            fieldURL.setText(dir);
            histiryURL.add(fieldURL.getText());
        } catch (Exception e) {
            alert.membersError("Неккоректный путь.");
        }
    }

    public void openFileandReadFile() {
        Date date;
        dir = fieldURL.getText().toString();
        log.writeFile("Открыли путь: "+ dir);
        String dirname = null;
        String type;
        String size12;
        if (!Objects.isNull(dir) && (dir.toString().startsWith(new File(root).toString()) || (dir.toString().startsWith("/run/media/")))){
        File[] files = new File(dir).listFiles();
        try {
            for (File file : files) {
                dirname = file.toString();
                type = dirname.substring(dirname.lastIndexOf(".") + 1, dirname.length());
                if (dirname.lastIndexOf(".") == -1) {
                    type = "No type";
                }
                if (file.isDirectory() == true) {
                    type = "Folder";
                }
                date = new Date(file.lastModified());
                if (file.length() / 1024 / 1024 / 1024 > 1) {
                    size12 = file.length() / 1024 / 1024 / 1024 + " ГБ";
                } else if (file.length() / 1024 / 1024 > 1024) {
                    size12 = file.length() / 1024 / 1024 + "  МБ";
                } else {
                    size12 = file.length() / 1024 + " КБ";
                }
                initData(dirname.substring(dirname.lastIndexOf("\\") + 1), size12, date, type, file.getPath());

            }
            oneColumnTableView.setCellValueFactory(new PropertyValueFactory<nameTable, String>("nameFile"));
            twoColumnTableView.setCellValueFactory(new PropertyValueFactory<nameTable, String>("sizeFile"));
            freeColumnTableView.setCellValueFactory(new PropertyValueFactory<nameTable, Date>("lastOperationFile"));
            foureColumnTableView.setCellValueFactory(new PropertyValueFactory<nameTable, String>("typeFile"));
            sixColumnTableView.setVisible(false);
            tableView.setItems(dirname2);
            fieldURL.setText(dir);
            histiryURL.add(fieldURL.getText());
        }
        catch (Exception e) {
            alert.membersError("Неккоректный путь.");}
        } else {
            alert.membersError("Неккоректный путь.");
        }
    }
    Semaphore sem = new Semaphore(1);
    public void OpenFindFile(ArrayList<String> arrayLURL)
    {
        dirname2.clear();
        Date date;
        String type;
        String size12;
        for (String file : arrayLURL) {
            type = file.substring(file.lastIndexOf(".") + 1, file.length());
            if (file.lastIndexOf(".") == -1) {
                type = "No type";
            }
            if (new File (file).isDirectory() == true) {
                type = "Folder";
            }
            date = new Date(new File(file).lastModified());
            if (file.length() / 1024 / 1024 / 1024 > 1) {
                size12 = file.length() / 1024 / 1024 / 1024 + " ГБ";
            } else if (file.length() / 1024 / 1024 > 1024) {
                size12 = file.length() / 1024 / 1024 + "  МБ";
            } else {
                size12 = file.length() / 1024 + " КБ";
            }
            initData(file.substring(file.lastIndexOf("\\") + 1), size12, date, type, new File (file).getPath());

        }
        oneColumnTableView.setCellValueFactory(new PropertyValueFactory<nameTable, String>("nameFile"));
        twoColumnTableView.setCellValueFactory(new PropertyValueFactory<nameTable, String>("sizeFile"));
        freeColumnTableView.setCellValueFactory(new PropertyValueFactory<nameTable, Date>("lastOperationFile"));
        foureColumnTableView.setCellValueFactory(new PropertyValueFactory<nameTable, String>("typeFile"));
        sixColumnTableView.setVisible(true);
        sixColumnTableView.setCellValueFactory(new PropertyValueFactory<nameTable, String>("puth"));
        tableView.setItems(dirname2);
        URLFindList.clear();
    }
    String timeURLI;
    public void OpenURLTreeView(String URLI) throws IOException {
        timeURLI = treeFile.getSelectionModel().getSelectedItem().getValue().getAbsolutePath();
        if(new File(timeURLI).exists())
        {
            fieldURL.setText(timeURLI);
            dirname2.clear();
            openFileandReadFile();
        }else {
            timeURLI=timeURLI.substring(0,timeURLI.lastIndexOf("\\")+1)+treeFile.getSelectionModel().getSelectedItem().getParent().getValue().getName()+"\\"+treeFile.getSelectionModel().getSelectedItem().getValue().getName();
            System.out.println(timeURLI);
            String rrr= treeFile.getSelectionModel().getSelectedItem().getParent().getParent().getParent().getValue().getName();
            System.out.println(rrr);

        }
    }
    private ArrayList<Path> pathHistory =new ArrayList<>();
    private int pathHistoryI=0;
    private boolean isBack=false;
    @FXML
    void btnBackAction(ActionEvent event) {
        try {
            if (pathHistoryI!=0){
                pathHistoryI-=1;
            }
            fieldURL.setText(pathHistory.get(pathHistoryI).toString());
            System.out.println(pathHistory.get(pathHistoryI).toString());
            dirname2.clear();
            openFileandReadFile();
            System.out.println(pathHistoryI);
            isBack=true;
        }catch (RuntimeException e){}

    }

    @FXML
    void btnForwardAction(ActionEvent event) {
        try {
            if (pathHistoryI < pathHistory.size()){
                pathHistoryI+=1;
                fieldURL.setText(pathHistory.get(pathHistoryI).toString());
                System.out.println(pathHistory.get(pathHistoryI).toString());
                dirname2.clear();
                openFileandReadFile();
                isBack=true;
            }
        }catch (RuntimeException e){}

    }
    void OpenTreeView(MouseEvent event) throws IOException {
        if (event.getButton() == MouseButton.PRIMARY) {
            if (event.getClickCount()==2) {
                String URLI = String.valueOf(treeFile.getSelectionModel().getSelectedItem().getValue());
                OpenURLTreeView(URLI);
                pathHistory.add(Paths.get(fieldURL.getText()));
                System.out.println(treeFile.getSelectionModel().getSelectedItem().getValue().getAbsolutePath()+"File");


                }
            }
        }

    /**
     *
     * Функция Open помогает открывать файлы через приложение по умолчанию.
     */
     void OpenTableView(MouseEvent event) throws Exception {
         if (event.getButton() == MouseButton.PRIMARY) {
             if (event.getClickCount()==2) {
                     File nonstop = new File(tableView.getSelectionModel().getSelectedItem().getPuth());
                 if (nonstop.isFile() == true) {
                         filemetod.openFile(tableView.getSelectionModel().getSelectedItem().getPuth());
                 } else {
                     fieldURL.setText(tableView.getSelectionModel().getSelectedItem().getPuth() + "/");
                     dirname2.clear();
                     openFileandReadFile();
                     pathHistory.add(Paths.get(fieldURL.getText()));

             }
             }
        }
     }

    /**
     * Cоздание для листView
     *
     */
    public void creetree(TreeItem parentNode,File file){
        //FileInfo fileInfo=new FileInfo(file);
        if(file.isDirectory()){
            TreeItem node=new TreeItem(file);
            node.setExpanded(true);
            parentNode.getChildren().add(node);
            for (File subFile : Objects.requireNonNull(file.listFiles())) {
                creetree(node, subFile);
            }
        }

    }
    public void updateTreeView() {
        TreeItem<File> rootItem = new TreeItem<>(new File(root));
        treeFile.setRoot(rootItem);
        treeFile.setShowRoot(false);
        creetree(rootItem, filePath);
// Загружаем дочерние элементы папки
        creetree(rootItem, new File(root));
    }

    public void monitorUSB() {
        java.util.List<File> usbList = new ArrayList<>();
        java.util.List<File> usbListNew = new ArrayList<>();
        try {
            Thread.sleep(2000);
            usbList = java.util.List.of(filePath.listFiles());
        } catch (Exception e) {
            throw new RuntimeException("Флешки нет");
        }
        while (true) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            usbListNew = List.of(filePath.listFiles());
            if (usbListNew.size() != usbList.size()) {
                usbList = usbListNew;
                Platform.runLater(()->{
                    updateTreeView();
                });

            }
        }
    }
    String root = "/home/denis/Документы/GitHub/Kyrsach/Kyrsovay";
    ArrayList<String> URLFindList;
    public String userName;
    public File filePath;
    @FXML
    private MenuItem Connection;

    @FXML
    private MenuItem ControlSeti;
    @FXML
    private MenuItem Login;

    @FXML
    private MenuItem SysMon;
    Semaphore sem12 = new Semaphore(3);
    taskFour task = new taskFour("Первый", sem12);
    public void TaskFo() {
        ProcessBuilder pb = new ProcessBuilder();
        String javaExecutable = System.getProperty("java.home") + File.separator + "bin" + File.separator + "java";
        String pathToJarFile = "./out/artifacts/Kyrsach_jar/Kyrsach.jar";
        String classpath = new String("./lib/javafx-sdk-17.0.2/lib/"); // Замените на реальный путь к JavaFX SDK
        System.out.println(javaExecutable);
        pb.command(javaExecutable, "--module-path", classpath, "--add-modules", "javafx.controls,javafx.fxml", "-jar", pathToJarFile, "myArg", "123");
        System.out.println(pb.command());
        File log = new File("log" + 0 + ".txt"); //debug log for started process
        try {
            pb.redirectOutput(log);
            pb.redirectError(log);
            pb.start();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
        @FXML 
    void initialize() throws IOException {
        pathHistory.add(0, Path.of(root));
            openFileandReadFile2();
            treeFile.setRoot(romans);
            creetree(treeFile.getRoot(),new File(root));
            Process command = null;
           try {
                command = Runtime.getRuntime().exec("users");
                BufferedReader input = new BufferedReader(new InputStreamReader(command.getInputStream()));
                userName = input.readLine();
                filePath = new File("/run/media/" + userName);
               System.out.println("Нашла");
               creetree(treeFile.getRoot(), filePath);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            //updateTreeView();
            Thread flashDriveThread = new Thread(this::monitorUSB);
            flashDriveThread.setDaemon(true);
            flashDriveThread.start();
            /**
             * Контекстное меню для tableView
             *  */
            Login.setOnAction(actionEvent -> {
                filemetod.GnomeLogs();
            });
            Connection.setOnAction(actionEvent -> {
                filemetod.NetworkManager();
            });
            ControlSeti.setOnAction(actionEvent -> {
                filemetod.GnomeControl();
            });
            SysMon.setOnAction(actionEvent ->{
                filemetod.GnomeSystemMonitor();
            });
            contextMenu.getItems().addAll(menuCreateDir, menuDelete,menuСopy,menuPaste,menuDeleteBasket,menuRename,menuCreateFile, menuRestored);
            contextMenu.setStyle("-fx-background-color: white; -fx-border-radius: 10; -fx-background-radius: 10; -fx-border-color: #464451;");
            //Контекстное меню
            menuСopy.setOnAction(actionEvent -> {
                copyVal = tableView.getSelectionModel().getSelectedItem().getPuth();
                copyNameVal = tableView.getSelectionModel().getSelectedItem().getNameFile();
                System.out.println(copyVal);
            });
            menuPaste.setOnAction(actionEvent -> {
                filemetod.pasteFile(copyVal,tableView.getSelectionModel().getSelectedItem().getPuth() + "/" + copyNameVal );
            });
            menuRename.setOnAction(actionEvent -> {
                try {
                    filemetod.renameFile(new File(tableView.getSelectionModel().getSelectedItem().getPuth()));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            menuCreateFile.setOnAction(actionEvent -> {
                try {
                    filemetod.createFile(new File(tableView.getSelectionModel().getSelectedItem().getPuth()));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            menuCreateDir.setOnAction(actionEvent ->{
                try {
                    filemetod.createFolder(new File(tableView.getSelectionModel().getSelectedItem().getPuth()));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            menuDelete.setOnAction(actionEvent ->{
                if(new File(tableView.getSelectionModel().getSelectedItem().getPuth()).isDirectory()) {
                    filemetod.deleteFullFile(tableView.getSelectionModel().getSelectedItem().getPuth());
                }else {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                if (new File(tableView.getSelectionModel().getSelectedItem().getPuth()).isFile()){
                                    Files.delete(Path.of(tableView.getSelectionModel().getSelectedItem().getPuth()));
                                    System.out.println("Это файл");
                                    Thread.interrupted();
                                }else {
                                    System.out.println("Это директория");
                                }
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }).start();
                }
                dirname2.clear();
                openFileandReadFile();
            });
            menuDeleteBasket.setOnAction(actionEvent ->{
                //Нужно перемещать в папку Корзина
                try {
                    filemetod.deleteinBasket(tableView.getSelectionModel().getSelectedItem().getPuth());
                } catch (IOException e) {
                    System.out.println("Печаль");
                }
            });
            menuRestored.setOnAction(actionEvent -> {
                try {
                    filemetod.returninBasket(tableView.getSelectionModel().getSelectedItem().getPuth());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            /**
             * Функционал Менюшки
             */
            {
                //При нажатий на кнопку открывает всплывающее окно О программе
                buttonAbout.setOnAction(actionEvent ->
                {
                    try {
                        open.openWindows("/com/example/kyrsach/Addfolders/About.fxml", "О программе", 357, 198);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
                //При нажатий на кнопку открывает всплывающее окно Справки
                buttonAgry.setOnAction(actionEvent ->
                {
                    try {
                        open.openWindows("/com/example/kyrsach/Addfolders/helper agry.fxml", "Cправка", 323, 172);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
                //При нажатий на кнопку открывает всплывающее окно Терминала
                buttonStartTerminal.setOnAction(actionEvent -> {
                    try {
                        open.openWindows("/com/example/kyrsach/Addfolders/Terminal.fxml", "Терминал", 566, 563);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
                buttonStartTask.setOnAction(actionEvent -> {
                    try {
                        open.openWindows("/com/example/kyrsach/Addfolders/Task.fxml", "Процессы", 600, 400);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
            /**
             * В поле адреса при нажатий на кнопку Enter будет происходить действие.
            */
            fieldURL.setOnKeyPressed(Event ->{
                if (Event.getCode().equals(KeyCode.ENTER)) {//Условие, что нажалась кнопка Enter
                    dirname2.clear();
                    openFileandReadFile();
                    pathHistory.add(Paths.get(fieldURL.getText()));
                }});
            /**
             * Функционал кнопки поиска buttonOpen
            */
            {
                buttonOpen.setOnMouseMoved(
                        mouseEvent -> {
                            buttonOpen.setStyle("-fx-background-color: black; -fx-border-radius: 20; -fx-background-radius: 20; -fx-border-color: white;");
                        }
                );
                buttonOpen.setOnAction(actionEvent -> {
                    dirname2.clear();
                    openFileandReadFile();
                    pathHistory.add(Paths.get(fieldURL.getText()));
                });
                buttonOpen.setOnMouseExited(
                        mouseEvent -> {
                            buttonOpen.setStyle("-fx-background-color: white; -fx-border-radius: 20; -fx-background-radius: 20; -fx-border-color: #464451;");
                        }
                );
            }
            treeFile.setOnMouseClicked(mouseEvent -> {
                    if (mouseEvent.getButton() == MouseButton.SECONDARY) {
                        if (mouseEvent.getClickCount() == 1) {
                            //Point location = MouseInfo.getPointerInfo().getLocation();
                            // contextMenu.show(treeFile, location.getX(), location.getY());
                        }
                    }else {
                        try {
                            contextMenu.hide();
                            OpenTreeView(mouseEvent);
                        } catch (Exception e) {
                            alert.membersError("Нет приложения для открытия такого файла.");
                        }
                    }

            });
            tableView.setOnMouseClicked(mouseEvent -> {
                if (mouseEvent.getButton() == MouseButton.SECONDARY)
                {
                    if(mouseEvent.getClickCount() == 1)
                    {
                        Point location = MouseInfo.getPointerInfo().getLocation();
                        contextMenu.show(tableView,location.getX(),location.getY());
                    }
                }else {
                    try {
                        contextMenu.hide();
                        OpenTableView(mouseEvent);
                    } catch (Exception e) {
                        alert.membersError("Нет приложения для открытия такого файла.");
                    }
                }
            });
            /**
             * Функционал кнопки  backButton
             */
            {
                backButton.setOnMouseMoved(
                        mouseEvent -> {
                            backButton.setStyle("-fx-background-color: black; -fx-border-radius: 20; -fx-background-radius: 20; -fx-border-color: white;");
                        }
                );
                backButton.setOnMouseExited(
                        mouseEvent -> {
                            backButton.setStyle("-fx-background-color: white; -fx-border-radius: 20; -fx-background-radius: 20; -fx-border-color: #464451;");
                        }
                );
            }
            /**
             * Функционал кнопки buttonForward
             */
            {
                forwatdButton.setOnMouseMoved(
                        mouseEvent -> {
                            forwatdButton.setStyle("-fx-background-color: black; -fx-border-radius: 20; -fx-background-radius: 20; -fx-border-color: white;");
                        }
                );
                forwatdButton.setOnMouseExited(
                        mouseEvent -> {
                            forwatdButton.setStyle("-fx-background-color: white; -fx-border-radius: 20; -fx-background-radius: 20; -fx-border-color: #464451;");
                        }
                );
            }
            /**
             * Функционал кнопки buttonFind
             */
            {
                buttonFind.setOnMouseMoved(
                        mouseEvent -> {
                            buttonFind.setStyle("-fx-background-color: black; -fx-border-radius: 20; -fx-background-radius: 20; -fx-border-color: white;");
                        }
                );
                buttonFind.setOnAction(
                        Event -> {
                            System.out.println(fieldURL1.getText());
                            FindThread.searchFiles(fieldURL1.getText(), new File(root));
                            URLFindList = FindThread.getArrayList();
                            OpenFindFile(URLFindList);
                        }
                );
                fieldURL1.setOnKeyPressed(Event ->{
                    if (Event.getCode().equals(KeyCode.ENTER)) {//Условие, что нажалась кнопка Enter
                        System.out.println(fieldURL1.getText());
                        FindThread.searchFiles(fieldURL1.getText(), new File(root));
                        URLFindList = FindThread.getArrayList();
                        OpenFindFile(URLFindList);
                    }});
                buttonFind.setOnMouseExited(
                        mouseEvent -> {
                            buttonFind.setStyle("-fx-background-color: white; -fx-border-radius: 20; -fx-background-radius: 20; -fx-border-color: #464451;");
                        }
                );
            }
            TerminalLocal.setOnAction(event -> {
                filemetod.MessengerStandart();
            });
            FSLocal.setOnAction(event -> {
                try {
                    filemetod.TerminalOpen();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            WinTest.setOnAction(event -> {
                //task.run();
                //TaskFo();
                try {
                    semOS.getSemaphoreO();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            /***
             * Реализация DragandDrop
             */
            tableView.setRowFactory(tv -> {
                //javafx.scene.image.Image Lav = new javafx.scene.image.Image("src/main/resources/com/example/kyrsach/Content/AvatarFolder_photo-resizer.ru.png");
                TableRow<nameTable> row = new TableRow<>();
                row.setOnDragDetected(DragEvent -> {
                    //System.out.println(tableView.getSelectionModel().getSelectedItem().getNameFile());
                    copyVal = tableView.getSelectionModel().getSelectedItem().getPuth();
                    copyNameVal = tableView.getSelectionModel().getSelectedItem().getNameFile();
                    Dragboard db = row.startDragAndDrop(TransferMode.ANY);
                    ClipboardContent content = new ClipboardContent();
                    if(new File(tableView.getSelectionModel().getSelectedItem().getPuth()).isDirectory()){
                    db.setDragView(new javafx.scene.image.Image(MainApplication.class.getResourceAsStream("/com/example/kyrsach/Content/AvatarFolder_photo-resizer.ru.png")));
                    /* put a string on dragboard */
                    content.putImage(new javafx.scene.image.Image(MainApplication.class.getResourceAsStream("/com/example/kyrsach/Content/AvatarFolder_photo-resizer.ru.png")));
                   } else {
                        db.setDragView(new javafx.scene.image.Image(MainApplication.class.getResourceAsStream("/com/example/kyrsach/Content/rrt_photo-resizer.ru (1).png")));
                        /* put a string on dragboard */
                        content.putImage(new javafx.scene.image.Image(MainApplication.class.getResourceAsStream("/com/example/kyrsach/Content/rrt_photo-resizer.ru (1).png")));
                    }
                    db.setContent(content);
                    DragEvent.consume();
                });
                row.setOnDragOver(DragEvent  -> {
                    Dragboard db = DragEvent.getDragboard();
                    DragEvent.acceptTransferModes(TransferMode.ANY);
                    row.setStyle("-fx-background-color: #a3c6c9 ;");
                });
               row.setOnDragExited(event -> {
                    row.setStyle("-fx-background-color:  ;");});
                row.setOnDragDone(event ->{
                });
                row.setOnDragDropped(event -> {
                    event.setDropCompleted(true);
                    System.out.println(row.getItem().getNameFile());
                    filemetod.pasteFile(copyVal,row.getItem().getPuth() + "/" + copyNameVal);
                    System.out.println(row.getItem().getPuth() + "/" + copyNameVal);
                    System.out.println(copyVal);
                });
                return row;
            });

        }
    }
