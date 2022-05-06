package hu.inf.unideb;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


public class FXMLController implements Initializable {

    @FXML
    private ProgressBar progressBar;
    @FXML
    private ProgressBar progressBar2;
    @FXML
    private Label dateIn;
    @FXML
    private Label freeSpaces;
    @FXML
    private Label fee;
    @FXML
    private TextField addPass;
    @FXML
    private TextField licensePlate;
    @FXML
    private TextField getLicensePlate2;
    @FXML
    private Circle redCircle;
    @FXML
    private Circle greenCircle;
    @FXML
    private Circle redCircle2;
    @FXML
    private Circle greenCircle2;


    private void Timenow(){
        Thread thread = new Thread(() -> {
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy \n HH:mm:ss");
            while(true){
                try{
                    Thread.sleep(1000);
                }catch(Exception e){
                    System.out.println(e);
                }
                final String timenow = df.format(new Date());
                Platform.runLater(() -> {
                    dateIn.setText(timenow); // This is the label
                });
            }
        });
        thread.start();
    }
    private void Loading(){
        Thread thread = new Thread(() -> {

            while (progressBar.getProgress() < 1) {
                try {
                    Thread.sleep(1);
                    progressBar.setProgress(progressBar.getProgress() + 0.001);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            if (((int)progressBar.getProgress()) == 1) {
                trafficLight.setGreen(redCircle, greenCircle);
            }
            try {
                Thread.sleep(5000);
                trafficLight.setRed(redCircle, greenCircle);
                progressBar.setProgress(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        redCircle.setFill(Color.valueOf("#ff1c02"));  //piros: #ff1c02 szurke: #948a89 zold: #03ff0a
        greenCircle.setFill(Color.valueOf("#948a89"));
        Timenow();
    }
    @FXML
    private void handleTicketButtonPushed(ActionEvent actionEvent){
        progressBar.setProgress(0);
        Loading();
    }
    public void handlePassButtonPushed(ActionEvent actionEvent) {
        progressBar.setProgress(0);
        Loading();
    }

    public void handleOkButtonPushed(ActionEvent actionEvent) {
    }

    public void handleLogButtonPushed(ActionEvent actionEvent) {
    }

    public void addPassButton(ActionEvent actionEvent) {
    }

    public void blackListButton(ActionEvent actionEvent) {
    }
}

