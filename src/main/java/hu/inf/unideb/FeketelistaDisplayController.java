package hu.inf.unideb;

import java.io.IOException;
import java.net.URL;
import java.text.BreakIterator;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;



public class FeketelistaDisplayController implements Initializable {

    @FXML
    private Label blLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        blLabel.setText("Fekete listán lévő rendszámok");
    }
}

