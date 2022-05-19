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
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;



public class FeketelistaController implements Initializable {

    @FXML
    private TextField lp;

    private JPA_IFace iFace = FXMLController.getiFace();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    void listButton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/fxml/blDisplay.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = new Stage();
        stage.setTitle("Fekete lista");
        stage.getIcons().add(new Image("/fxml/logo_ver_2_mini.png"));
        stage.setScene(scene);
        stage.show();
    }
    public void deleteFromList(ActionEvent actionEvent)throws IOException {
        String licensePlate = lp.getText();
        if(!licensePlate.matches("[A-Z]{3}-[0-9]{3}") /*vagy nincs benne a listában*/) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.showAndWait();
        }
        if(iFace.findBlacListByLicense(licensePlate) != null) {
            BlackList bl = new BlackList();
            bl.setLicense(licensePlate);
            iFace.saveBlackList(bl);
        }
        //else System.out.println(licensePlate);
    }
    public void addToList(ActionEvent actionEvent)throws IOException {
            String licensePlate = lp.getText();
            if(!licensePlate.matches("[A-Z]{3}-[0-9]{3}")) {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.showAndWait();
            }

        if(iFace.findBlacListByLicense(licensePlate) != null) {
            BlackList bl = new BlackList();
            bl.setLicense(licensePlate);
            iFace.saveBlackList(bl);
        }

           //else System.out.println(licensePlate);

    }


}

