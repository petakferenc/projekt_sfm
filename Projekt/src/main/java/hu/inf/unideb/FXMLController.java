package hu.inf.unideb;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
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

        try(JPA_IFace iFace  = new JPA_DAO();)
        {
            Car a = new Car();
            a.setLicense("ABC-510");
            a.setType(Car.Type.CAR);
            iFace.saveCar(a);
            System.out.println(a.toString());

            ParkingSpace ps = new ParkingSpace();
            ps.setStatus(ParkingSpace.Status.USE);
            ps.setDate(LocalDateTime.now());
            ps.setType(ParkingSpace.Type.CAR);
            ps.setCar(a);
            System.out.println(ps.toString());
            iFace.saveParkingSpace(ps);

            Car b = new Car();
            b.setLicense("BBB-456");
            b.setType(Car.Type.CAR);
            iFace.saveCar(b);
            System.out.println(b.toString());

            ParkingSpace psb = new ParkingSpace();
            psb.setStatus(ParkingSpace.Status.RENT);
            psb.setDate(LocalDateTime.now());
            psb.setType(ParkingSpace.Type.CAR);
            psb.setCar(b);
            System.out.println(psb.toString());
            iFace.saveParkingSpace(psb);

            Car findcarbylicens = iFace.findCarLicense("ABC-510");
            if( findcarbylicens == null)
            {
                System.out.println("nope");
            }
            else
                System.out.println("Yepp");

            ParkingSpace status = iFace.findPSStatusByType(ParkingSpace.Status.USE, ParkingSpace.Type.CAR);
            if( status == null)
            {
                System.out.println("nope");
            }
            else {
                System.out.print("StatusByCarID: ");
                System.out.println(status.getId());
            }

            System.out.println("Done:D");

        }catch (Exception e)
        {
            e.printStackTrace();
        }
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

