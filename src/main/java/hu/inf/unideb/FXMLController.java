package hu.inf.unideb;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
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
        Thread thread = new Thread(() -> {
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
            ps.setPosition(1);
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
            psb.setPosition(2);
            System.out.println(psb.toString());
            iFace.saveParkingSpace(psb);

            Car findcarbylicens = iFace.findCarByLicense("ABC-510");
            if( findcarbylicens == null)
            {
                System.out.println("nope");
            }
            else
                System.out.println("Yepp");

            LocalDateTime date = iFace.findPSByLicense("ABC-510").getDate();
            long d1 = localeDateTimeToHour(date);
            long d2 = localeDateTimeToHour(LocalDateTime.now());
            d2+=5;
            System.out.println(d1);
            System.out.println(d2);
            long oss = d2-d1;
            System.out.println("calc: " + oss);
            if(oss > 1)
            {
                if(oss >= 2)
                {
                    oss = (oss-1)*500+1000;
                }
                else
                    oss = 1000;
            }
            System.out.println("Fizet: " + oss);

            iFace.deletCar(findcarbylicens);

            System.out.println("Free: "+iFace.GetFreeSpaces());

            if( findcarbylicens == null)
            {
                System.out.println("nope");
            }
            else
                System.out.println("Yepp");

            BlackList bl = new BlackList();
            bl.setLicense("ABC-510");
            iFace.saveBlackList(bl);

            if(iFace.findBlacListByLicense("ABC-510") == null)
            {
                Car f = new Car();
                f.setLicense("ABC-510");
                f.setType(Car.Type.CAR);
                iFace.saveCar(f);
                System.out.println(f.toString());

                ParkingSpace ss = iFace.findPSStatusByType(ParkingSpace.Status.FREE, ParkingSpace.Type.CAR);
                ss.setDate(LocalDateTime.now());
                ss.setStatus(ParkingSpace.Status.USE);
                ss.setCar(f);
                System.out.println(ss.toString());
                iFace.saveParkingSpace(ss);
            }
            else
            {
                System.out.println("can't go in");

            }

            iFace.deletBlackList(bl);

            if(iFace.findBlacListByLicense("ABC-510") == null)
            {
                System.out.println("go in");
                Car f = new Car();
                f.setLicense("ABC-510");
                f.setType(Car.Type.CAR);
                iFace.saveCar(f);
                System.out.println(f.toString());

                ParkingSpace ss = iFace.findPSStatusByType(ParkingSpace.Status.FREE, ParkingSpace.Type.CAR);
                ss.setDate(LocalDateTime.now());
                ss.setStatus(ParkingSpace.Status.USE);
                ss.setCar(f);
                System.out.println(ss.toString());
                iFace.saveParkingSpace(ss);
            }
            else
            {
                System.out.println("can't go in");

            }

            ParkingSpace status = iFace.findPSStatusByType(ParkingSpace.Status.USE, ParkingSpace.Type.CAR);

            if( status == null)
            {
                System.out.println("nope");
            }
            else {
                System.out.print("StatusByCarID: ");
                System.out.println(status.getId());
            }

            iFace.deletParkingSpace(psb);

            iFace.close();
            System.out.println("Done:D");

        }catch (Exception e)
        {
            e.printStackTrace();
        }
        });
        thread.start();
        progressBar.setProgress(0);
        Loading();
    }
    public void handlePassButtonPushed(ActionEvent actionEvent) {
        progressBar.setProgress(0);
        Loading();
    }

    public void handleOkButtonPushed(ActionEvent actionEvent) {
    }

    public void handleLogButtonPushed(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/fxml/logDisplay.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = new Stage();
        stage.setTitle("Log");
        stage.getIcons().add(new Image("/fxml/logo_ver_2_mini.png"));
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    void addPassButton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/fxml/berletek.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = new Stage();
        stage.setTitle("Bérletek");
        stage.getIcons().add(new Image("/fxml/logo_ver_2_mini.png"));
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    void blackListButton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/fxml/feketelista.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = new Stage();
        stage.setTitle("Fekete lista");
        stage.getIcons().add(new Image("/fxml/logo_ver_2_mini.png"));
        stage.setScene(scene);
        stage.show();
    }
    public long localeDateTimeToHour(LocalDateTime date)
    {
        long sec = date.getYear() * 8766;
        sec+= date.getMonthValue() * 730;
        sec+= date.getDayOfMonth() * 24;
        sec+= date.getHour();
        sec+= date.getMinute() / 60;
        sec+= date.getSecond() / 3600 ;
        return sec;
    }
}

