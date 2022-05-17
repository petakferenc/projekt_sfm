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


public class BerletekController implements Initializable {

    @FXML
    private ProgressBar progressBar;
    @FXML
    private ProgressBar progressBar2;
    @FXML
    private Label dateIn;
    @FXML
    private Label dateOut;
    @FXML
    private Label freeSpaces;
    @FXML
    private Label fee;
    @FXML
    private TextField addPass;
    @FXML
    private TextField fromText;
    @FXML
    private TextField toText;
    @FXML
    private TextField licensePlate1;
    @FXML
    private TextField licensePlate2;
    @FXML
    private Circle redCircle;
    @FXML
    private Circle greenCircle;
    @FXML
    private Circle redCircle2;
    @FXML
    private Circle greenCircle2;


    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
    public void addPass(ActionEvent actionEvent){
        String from = fromText.getText();
        String to = toText.getText();
        String lp = licensePlate1.getText();
    }
    public void deletePass(ActionEvent actionEvent){
        String lp = licensePlate2.getText();
    }
}

