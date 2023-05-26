package com.example.kyrsach;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import com.example.kyrsach.Controller.renameFileController;
import com.example.kyrsach.Metod.alertMembers;
import com.example.kyrsach.Metod.metodFile;
import com.example.kyrsach.Metod.nameTable;
import com.example.kyrsach.Metod.openWindows;
import com.example.kyrsach.ThreadCont.FindFileThread;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.*;



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
    /**
     * Переменные для функций и сами необходимые функций.
     */
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
      MenuItem menuOpen = new MenuItem("Открыть");//Создание элемента в контекстном меню с названием.
      MenuItem menuCreateDir = new MenuItem("Создать Директорию");
      MenuItem menuRestored = new MenuItem("Восстановить");
      MenuItem menuCreateFile = new MenuItem("Создать Файл");
      MenuItem menuRename= new MenuItem("Переименовать");
      MenuItem menuDelete = new MenuItem("Удалить");
      MenuItem menuСopy = new MenuItem("Копировать");
      MenuItem menuPaste = new MenuItem("Вставить");
      MenuItem menuDeleteBasket = new MenuItem("Удалить в Корзину");

    ObservableList<nameTable> dirname2 = FXCollections.observableArrayList();
    ObservableList<nameTable> dirname1 = FXCollections.observableArrayList();
    alertMembers alert = new alertMembers();
    FindFileThread FindThread;

    private void initData(String nameFile, String sizeFile, Date lastOperationFile, String typeFile, String puth) {
        dirname2.add(new nameTable(nameFile, sizeFile, lastOperationFile, typeFile, puth));
    }
    private void initData1(String nameFile, String sizeFile, Date lastOperationFile, String typeFile, String puth) {
        dirname1.add(new nameTable(nameFile, sizeFile, lastOperationFile, typeFile, puth));
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
            tableView.setItems(dirname2);
            fieldURL.setText(dir);
            histiryURL.add(fieldURL.getText());
        } catch (Exception e) {
            alert.membersError("Неккоректный путь.");
        }
    }

    public void openFileandReadFile() {
        Date date;
        dir = fieldURL.getText().toString();
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
            tableView.setItems(dirname2);
            fieldURL.setText(dir);
            histiryURL.add(fieldURL.getText());
        } catch (Exception e) {
            alert.membersError("Неккоректный путь.");
        }
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

    void OpenTreeView(MouseEvent event) throws IOException {
        if (event.getButton() == MouseButton.PRIMARY) {
            if (event.getClickCount()==2) {
                String URLI = String.valueOf(treeFile.getSelectionModel().getSelectedItem().getValue());
                OpenURLTreeView(URLI);
                System.out.println(treeFile.getSelectionModel().getSelectedItem().getValue().getAbsolutePath()+"File");
               /* if( new File(URLI).exists())
                {
                    //System.out.println(treeFile.getSelectionModel().getSelectedItem().getParent().getValue().getCanonicalPath()+ "\\"+ treeFile.getSelectionModel().getSelectedItem().getParent().getParent().getValue().getName());
                    fieldURL.setText(URLI+ "\\"+ treeFile.getSelectionModel().getSelectedItem().getValue().getName());
                    dirname2.clear();
                    openFileandReadFile();
                }else {
                    URLI += "\\" + treeFile.getSelectionModel().getSelectedItem().getValue().getName();
                    if (new File(URLI+"\\" + treeFile.getSelectionModel().getSelectedItem().getValue().getName()).exists()){
                        URLI += "\\" + treeFile.getSelectionModel().getSelectedItem().getValue().getName();
                        fieldURL.setText(URLI);
                        dirname2.clear();
                        openFileandReadFile();
                }else {
                        URLI = treeFile.getSelectionModel().getSelectedItem().getParent().getParent().getValue().getAbsolutePath()+"\\"+treeFile.getSelectionModel().getSelectedItem().getParent().getValue().getName();
                        System.out.println(treeFile.getSelectionModel().getSelectedItem().getParent().getValue().getCanonicalPath()+ "\\"+ treeFile.getSelectionModel().getSelectedItem().getParent().getParent().getValue().getName());
                        System.out.println(URLI);
                        fieldURL.setText(URLI + "\\" + treeFile.getSelectionModel().getSelectedItem().getValue().getName());
                        dirname2.clear();
                        openFileandReadFile();
                    }
                    System.out.println(treeFile.getSelectionModel().getSelectedItem().getParent().getValue().getCanonicalPath()+ "\\"+ treeFile.getSelectionModel().getSelectedItem().getParent().getParent().getValue().getName());
                    System.out.println(URLI);
                }*/
                  /*  fieldURL.setText(treeFile.getSelectionModel().getSelectedItem().getParent().getValue().getCanonicalPath()+ "\\"+ treeFile.getSelectionModel().getSelectedItem().getValue().getName());
                    dirname2.clear();
                    openFileandReadFile()*/;

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
        @FXML 
    void initialize() {
            System.out.println(dirname1.size());
            openFileandReadFile2();
            treeFile.setRoot(romans);
            creetree(treeFile.getRoot(),new File("/home/denis/Документы/GitHub/Kyrsach/Kyrsovay"));
            /**
             * Контекстное меню для tableView
             *  */
            contextMenu.getItems().addAll(menuOpen, menuCreateDir, menuDelete,menuСopy,menuPaste,menuDeleteBasket,menuRename,menuCreateFile);
            contextMenu.setStyle("-fx-background-color: white; -fx-border-radius: 10; -fx-background-radius: 10; -fx-border-color: #464451;");
            //Контекстное меню
            menuOpen.setOnAction(actionEvent -> {
                System.out.println(treeFile.getSelectionModel().getSelectedItem().getValue().getParentFile().getAbsolutePath());
                String URL1 = fieldURL.getText()+"\\"+treeFile.getSelectionModel().getSelectedItem().getValue().getName();
                System.out.println(URL1);
                fieldURL.setText(URL1);
                dirname2.clear();
                openFileandReadFile();
            });
            menuСopy.setOnAction(actionEvent -> {
                copyVal = tableView.getSelectionModel().getSelectedItem().getPuth();
                copyNameVal = tableView.getSelectionModel().getSelectedItem().getNameFile();
                System.out.println(copyVal);
            });
            menuPaste.setOnAction(actionEvent -> {
                filemetod.pasteFile(copyVal,tableView.getSelectionModel().getSelectedItem().getPuth() + "\\" + copyNameVal );
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
                //File filemenu = treeFile.getSelectionModel().getSelectedItem().getValue();
                System.out.println("fff");
                //System.out.println(filemenu.getName());
            });
            menuDelete.setOnAction(actionEvent ->{
                filemetod.deleteFullFile(tableView.getSelectionModel().getSelectedItem().getPuth());
            });
            menuDeleteBasket.setOnAction(actionEvent ->{
                //Нужно перемещать в папку Корзина
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            if (new File(tableView.getSelectionModel().getSelectedItem().getPuth()).isFile()){
                                Files.delete(Path.of(tableView.getSelectionModel().getSelectedItem().getPuth()));
                                System.out.println("Это файл");
                                dirname2.clear();
                                openFileandReadFile();
                                Thread.interrupted();
                            }else {
                                System.out.println("Это директория");
                            }
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }).start();
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
                            Point location = MouseInfo.getPointerInfo().getLocation();
                            contextMenu.show(treeFile, location.getX(), location.getY());
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
                backButton.setOnAction(actionEvent -> {
                    int i = histiryURL.size()-2;
                    String URL1 = new String(histiryURL.get(i));
                    fieldURL.setText(URL1);
                    dirname2.clear();
                    openFileandReadFile();
                });
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
                        mouseEvent -> {
                            System.out.println(fieldURL1.getText());
                            FindThread = new FindFileThread(fieldURL1.getText());
                            FindThread.run();
                            //File filesearch = new File(filemetod.findFile(new File("//Kyrsovay//"), fieldURL1.getText()).toString());
                            String puthFind = FindThread.getFiles().getPath();
                            System.out.println(puthFind.substring(0,puthFind.lastIndexOf("\\")+1));
                            fieldURL.setText(puthFind.substring(0,puthFind.lastIndexOf("\\")+1));
                            dirname2.clear();
                            openFileandReadFile();
                        }
                );
                fieldURL1.setOnKeyPressed(Event ->{
                    if (Event.getCode().equals(KeyCode.ENTER)) {//Условие, что нажалась кнопка Enter
                        File filesearch = new File(filemetod.findFile(new File("//Kyrsovay//"), fieldURL1.getText()).toString());
                        String puthFind = filesearch.getPath();
                        System.out.println(puthFind.substring(0,puthFind.lastIndexOf("\\")+1));
                        fieldURL.setText(puthFind.substring(0,puthFind.lastIndexOf("\\")+1));
                        dirname2.clear();
                        openFileandReadFile();
                    }});
                buttonFind.setOnMouseExited(
                        mouseEvent -> {
                            buttonFind.setStyle("-fx-background-color: white; -fx-border-radius: 20; -fx-background-radius: 20; -fx-border-color: #464451;");
                        }
                );
            }
            /***
             * Реализация DragandDrop
             */
            /*tableView.setRowFactory(tv -> {
                Image Lav = new Image("D:\\Kyrsach\\src\\main\\resources\\com\\example\\kyrsach\\Content\\AvatarFolder_photo-resizer.ru.png");
                TableRow<nameTable> row = new TableRow<>();
                row.setOnDragDetected(DragEvent -> {
                    //System.out.println(tableView.getSelectionModel().getSelectedItem().getNameFile());
                    Dragboard db = row.startDragAndDrop(TransferMode.ANY);
                    ClipboardContent content = new ClipboardContent();
                    if(new File(tableView.getSelectionModel().getSelectedItem().getPuth()).isDirectory()){
                    db.setDragView(new Image("D:\\Kyrsach\\src\\main\\resources\\com\\example\\kyrsach\\Content\\AvatarFolder_photo-resizer.ru.png"));
                    *//* put a string on dragboard *//*
                    content.putImage(new Image("D:\\Kyrsach\\src\\main\\resources\\com\\example\\kyrsach\\Content\\AvatarFolder_photo-resizer.ru.png"));
                   } else {
                        db.setDragView(new Image("D:\\Kyrsach\\src\\main\\resources\\com\\example\\kyrsach\\Content\\rrt_photo-resizer.ru (1).png"));
                        *//* put a string on dragboard *//*
                        content.putImage(new Image("D:\\Kyrsach\\src\\main\\resources\\com\\example\\kyrsach\\Content\\rrt_photo-resizer.ru (1).png"));
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
                row.setOnDragDone(DragEvent ->{

                    System.out.println(tableView.getSelectionModel().getSelectedItem().getPuth());
                });
                row.setOnDragDropped(event -> {
                    System.out.println(event.getGestureTarget());
                    event.setDropCompleted(true);
                    System.out.println(event.getGestureTarget());
                });
                return row;
            });*/

        }
    }
