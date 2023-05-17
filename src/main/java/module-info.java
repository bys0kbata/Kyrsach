module com.example.kyrsach {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml;
    requires java.datatransfer;
    requires java.desktop;


    opens com.example.kyrsach to javafx.fxml;
    exports com.example.kyrsach;
    exports com.example.kyrsach.Controller;
    opens com.example.kyrsach.Controller to javafx.fxml;
    exports com.example.kyrsach.Metod;
    opens com.example.kyrsach.Metod to javafx.fxml;
    exports com.example.kyrsach.Controller.Terminal;
    opens com.example.kyrsach.Controller.Terminal to javafx.fxml;
}