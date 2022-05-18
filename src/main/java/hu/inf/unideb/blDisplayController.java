package hu.inf.unideb;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;


public class blDisplayController implements Initializable {

    @FXML
    private Label bl;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        bl.setText("Fekete listán lévő rendszámok");
    }
}

