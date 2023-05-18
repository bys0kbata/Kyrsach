package com.example.kyrsach.Controller;

        import java.net.URL;
        import java.util.ResourceBundle;
        import javafx.fxml.FXML;
        import javafx.scene.control.Button;
        import javafx.scene.control.ProgressBar;

public class copyFileController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button button;

    @FXML
    private ProgressBar proccessBar;

    @FXML
    void initialize() {
      button.setOnAction(ActionEvent -> {
          proccessBar.setProgress(0.12);
      });

    }

}
