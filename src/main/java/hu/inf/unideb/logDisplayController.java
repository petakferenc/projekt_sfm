package hu.inf.unideb;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;


public class logDisplayController implements Initializable {

    @FXML
    private Label log;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        log.setText("log");
    }
}

