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
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    private Label dateOut;
    @FXML
    private Label freeSpaces;
    @FXML
    private Label fee;
    @FXML
    private TextField addPass;
    @FXML
    private TextField licensePlate;
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
    @FXML
    private ImageView red1;
    @FXML
    private ImageView green1;
    @FXML
    private ImageView red2;
    @FXML
    private ImageView green2;

    private static JPA_IFace iFace  = new JPA_DAO();

    public static JPA_IFace getiFace() {
        return iFace;
    }

    private void Timenow(){
        Thread thread = new Thread(() -> {
            DateFormat df = new SimpleDateFormat("yyyy/MM/dd \n HH:mm:ss");
            while(true){
                try{
                    Thread.sleep(1000);
                }catch(Exception e){
                    System.out.println(e);
                }
                final String timenow = df.format(new Date());
                Platform.runLater(() -> {
                    dateIn.setText(timenow); // This is the label
                    dateOut.setText(timenow);
                });
            }
        });
        thread.start();
    }

    private void Loading(ProgressBar progressBar, ImageView red1, ImageView green1){
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
                //trafficLight.setGreen(redCircle, greenCircle);
                red1.setVisible(false);
                green1.setVisible(true);

            }
            try {
                Thread.sleep(5000);
                green1.setVisible(false);
                red1.setVisible(true);
                progressBar.setProgress(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //redCircle.setFill(Color.valueOf("#ff1c02"));  //piros: #ff1c02 szurke: #948a89 zold: #03ff0a
        //greenCircle.setFill(Color.valueOf("#948a89"));
        green1.setVisible(false);
        red1.setVisible(true);
        green2.setVisible(false);
        red2.setVisible(true);
        Timenow();

        System.out.println("init run");

        try
        {
            //add 10 parking space
            //4 car use; 2 rent, 1 in 1 out; 4 free
            Car a = new Car();
            a.setLicense("ABC-510");
            a.setType(Car.Type.CAR);
            iFace.saveCar(a);

            ParkingSpace ps = new ParkingSpace();
            ps.setStatus(ParkingSpace.Status.USE);
            ps.setDate(LocalDateTime.now());
            ps.setType(ParkingSpace.Type.CAR);
            ps.setCar(a);
            iFace.saveParkingSpace(ps);
            System.out.println("CAR 1 AND PS 1");

            Car b = new Car();
            b.setLicense("ASE-123");
            b.setType(Car.Type.CAR);
            iFace.saveCar(b);

            ParkingSpace pss = new ParkingSpace();
            pss.setStatus(ParkingSpace.Status.USE);
            pss.setDate(LocalDateTime.now());
            pss.setType(ParkingSpace.Type.CAR);
            pss.setCar(b);
            iFace.saveParkingSpace(pss);
            System.out.println("CAR 2 AND PS 2");

            Car d = new Car();
            d.setLicense("ASE-155");
            d.setType(Car.Type.CAR);
            iFace.saveCar(d);

            ParkingSpace pc = new ParkingSpace();
            pc.setStatus(ParkingSpace.Status.USE);
            pc.setDate(LocalDateTime.now());
            pc.setType(ParkingSpace.Type.CAR);
            pc.setCar(d);
            iFace.saveParkingSpace(pc);
            System.out.println("CAR 3 AND PS 3");

            Car c = new Car();
            c.setLicense("ARR-123");
            c.setType(Car.Type.CAR);
            iFace.saveCar(c);

            ParkingSpace pcc = new ParkingSpace();
            pcc.setStatus(ParkingSpace.Status.USE);
            pcc.setDate(LocalDateTime.now());
            pcc.setType(ParkingSpace.Type.CAR);
            pcc.setCar(c);
            iFace.saveParkingSpace(pcc);
            System.out.println("CAR 4 AND PS 4");

            Car w = new Car();
            w.setLicense("AAA-909");
            w.setType(Car.Type.CAR);
            iFace.saveCar(w);

            ParkingSpace pr = new ParkingSpace();
            pr.setStatus(ParkingSpace.Status.RENTIN);
            pr.setDate(LocalDateTime.now());
            pr.setType(ParkingSpace.Type.CAR);
            pr.setCar(w);
            iFace.saveParkingSpace(pr);
            System.out.println("CAR 5 AND PS 5");

            Car r = new Car();
            r.setLicense("AUU-123");
            r.setType(Car.Type.CAR);
            iFace.saveCar(r);

            ParkingSpace prr = new ParkingSpace();
            prr.setStatus(ParkingSpace.Status.RENTOUT);
            prr.setDate(LocalDateTime.now());
            prr.setType(ParkingSpace.Type.CAR);
            prr.setCar(r);
            iFace.saveParkingSpace(prr);
            System.out.println("CAR 6 AND PS 6");

            Car t = new Car();
            t.setLicense("ATR-123");
            t.setType(Car.Type.CAR);
            iFace.saveCar(t);

            ParkingSpace pt = new ParkingSpace();
            pt.setStatus(ParkingSpace.Status.FREE);
            pt.setDate(LocalDateTime.now());
            pt.setType(ParkingSpace.Type.CAR);
            pt.setCar(t);
            iFace.saveParkingSpace(pt);
            System.out.println("CAR 7 AND PS 7");

            Car f = new Car();
            f.setLicense("RRR-123");
            f.setType(Car.Type.CAR);
            iFace.saveCar(f);

            ParkingSpace ptt = new ParkingSpace();
            ptt.setStatus(ParkingSpace.Status.FREE);
            ptt.setDate(LocalDateTime.now());
            ptt.setType(ParkingSpace.Type.CAR);
            ptt.setCar(f);
            iFace.saveParkingSpace(ptt);
            System.out.println("CAR 8 AND PS 8");

            Car z = new Car();
            z.setLicense("RFE-123");
            z.setType(Car.Type.CAR);
            iFace.saveCar(z);

            ParkingSpace pf = new ParkingSpace();
            pf.setStatus(ParkingSpace.Status.FREE);
            pf.setDate(LocalDateTime.now());
            pf.setType(ParkingSpace.Type.CAR);
            pf.setCar(z);
            iFace.saveParkingSpace(pf);
            System.out.println("CAR 9 AND PS 9");

            Car g = new Car();
            g.setLicense("ROT-123");
            g.setType(Car.Type.CAR);
            iFace.saveCar(g);

            ParkingSpace pff = new ParkingSpace();
            pff.setStatus(ParkingSpace.Status.FREE);
            pff.setDate(LocalDateTime.now());
            pff.setType(ParkingSpace.Type.CAR);
            pff.setCar(g);
            iFace.saveParkingSpace(pff);
            System.out.println("CAR 10 AND PS 10");

            freeSpaces.setText(String.valueOf(iFace.GetFreeSpaces()));


        } catch (Exception e) {
            System.out.println("init error");
            e.printStackTrace();
        }
        System.out.println("init done");

    }

    @FXML
    private void handleTicketButtonPushed(ActionEvent actionEvent){
        progressBar.setProgress(0);
        Loading(progressBar, red1, green1);
        //Thread thread = new Thread(() -> {
            try
            {
                String license = licensePlate.getText();
                //System.out.println("Licens: "+license);
                if(!license.matches("[A-Z]{3}-[0-9]{3}"))
                {
                    errorMessage("Nem megfelelő formályú rendszám");
                    return;
                }

                if(iFace.findBlacListByLicense(license) != null)
                {
                    errorMessage("Fekete listás jármű");
                    return;
                }

                Car a = iFace.findCarByLicense(license);
                if(a == null)
                {
                    a = new Car();
                    a.setLicense(license);
                    a.setType(Car.Type.CAR);
                }

                ParkingSpace ps = iFace.findPSByLicense(license);//iFace.findPSStatusByType(ParkingSpace.Status.USE, ParkingSpace.Type.CAR);
                if(ps != null)
                {
                    /*if(ps.getStatus() == ParkingSpace.Status.RENTIN)
                    {
                        errorMessage("Már bent van a bérelt helyén a jármű");
                        return;
                    }
                    else if(ps.getStatus() == ParkingSpace.Status.RENTOUT)
                    {
                        ps.setStatus(ParkingSpace.Status.RENTIN);
                        ps.setDate(LocalDateTime.now());
                        iFace.saveParkingSpace(ps);
                        return;
                    }
                    else*/ if(ps.getStatus() == ParkingSpace.Status.USE)
                    {
                        errorMessage("Már van bent ilyen jármű");
                        return;
                    }
                    else
                    {
                        errorMessage("ERROR: Rossz gombot használ");
                        return;
                    }
                }

                ps = iFace.findPSStatusByType(ParkingSpace.Status.FREE, ParkingSpace.Type.CAR);
                System.out.println(ps);
                if(ps == null)
                {
                    errorMessage("Nincs szabad hely");
                    return;
                }

                ps.setStatus(ParkingSpace.Status.USE);
                ps.setDate(LocalDateTime.now());
                ps.setCar(a);

                iFace.saveCar(a);
                iFace.saveParkingSpace(ps);

                freeSpaces.setText(String.valueOf(iFace.GetFreeSpaces()));

            }catch (Exception e)
            {
                e.printStackTrace();
            }
       // });
        //.start();
        //errorMessage(thread.toString());

    }
    public void handlePassButtonPushed(ActionEvent actionEvent) {
        progressBar.setProgress(0);
        Loading(progressBar, red1, green1);
        try {
            String license = licensePlate.getText();
            //System.out.println("Licens: "+license);
            if(!license.matches("[A-Z]{3}-[0-9]{3}"))
            {
                errorMessage("Nem megfelelő formályú rendszám");
                return;
            }

            if(iFace.findBlacListByLicense(license) != null)
            {
                errorMessage("Fekete listás jármű");
                return;
            }

            ParkingSpace ps = iFace.findPSByLicense(license);
            if(ps != null)
            {
                if(ps.getStatus() == ParkingSpace.Status.RENTIN)
                {
                    errorMessage("Már bent van a bérelt helyén a jármű");
                    return;
                }
                else if(ps.getStatus() == ParkingSpace.Status.RENTOUT)
                {
                    ps.setStatus(ParkingSpace.Status.RENTIN);
                    ps.setDate(LocalDateTime.now());
                    iFace.saveParkingSpace(ps);
                    return;
                }
                else
                {
                    errorMessage("ERROR: rossz gombot használ");
                    return;
                }
            }

        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void handleOkButtonPushed(ActionEvent actionEvent) {
        progressBar2.setProgress(0);
        Loading(progressBar2, red2, green2);
        try {
            String license = licensePlate2.getText();
            if (!license.matches("[A-Z]{3}-[0-9]{3}")) {
                errorMessage("Nem megfelelő formályú rendszám");
                return;
            }

            Car car = iFace.findCarByLicense(license);
            if (car != null) {

                ParkingSpace ps = iFace.findPSByLicense(license);
                if(ps != null) {
                    if (ps.getStatus() != ParkingSpace.Status.RENTIN || ps.getStatus() != ParkingSpace.Status.RENTOUT)
                    {
                        LocalDateTime date = ps.getDate();
                        long d1 = localeDateTimeToHour(date);
                        long d2 = localeDateTimeToHour(LocalDateTime.now());
                        //System.out.println(d1);
                        //System.out.println(d2);
                        long oss = d2 - d1;

                        if (oss > 1) {
                            if (oss >= 2) {
                                oss = (oss - 1) * 500 + 1000;
                            } else
                                oss = 1000;
                        }

                        fee.setText("Fizetendő: " + oss + "Ft");

                    }
                    iFace.deleteCar(car);
                }
            }

            freeSpaces.setText(String.valueOf(iFace.GetFreeSpaces()));

        }catch (Exception e)
        {
            e.printStackTrace();
        }

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

    @FXML
    public void errorMessage(String message)
    {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setContentText(message);
        errorAlert.showAndWait();
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

