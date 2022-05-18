package hu.inf.unideb;

import java.io.IOException;
import java.net.URL;
import java.text.BreakIterator;
import java.text.DateFormat;
import java.text.ParseException;
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


public class BerletekController implements Initializable {


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

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
    public void addPass(ActionEvent actionEvent) throws ParseException {
        String from = fromText.getText();
        String to = toText.getText();
        String lp = licensePlate1.getText();
        if(!from.matches("[0-9]{4}.[0-9]{2}.[0-9]{2}") || !to.matches("[0-9]{4}.[0-9]{2}.[0-9]{2}")
        || !lp.matches("[A-Z]{3}-[0-9]{3}")) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setContentText("Nem megfelelő kitöltés");
            errorAlert.showAndWait();
        }
        SimpleDateFormat formatter1=new SimpleDateFormat("yyyy.MM.dd");
        Date date1=formatter1.parse(from);
        Date date2=formatter1.parse(to);
        if (date1.after(date2) || date1.before(new Date())){
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setContentText("Hibás dátumok");
            errorAlert.showAndWait();
        }
    }
    public void deletePass(ActionEvent actionEvent){
        String lp = licensePlate2.getText();
        if(!lp.matches("[A-Z]{3}-[0-9]{3}")/*vagy nincs benne a bérletesekben*/) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.showAndWait();
        }
    }
    public void listButton(ActionEvent actionEvent)throws IOException {
        FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/fxml/feketelista.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = new Stage();
        stage.setTitle("Fekete Lista");
        stage.getIcons().add(new Image("/fxml/logo_ver_2_mini.png"));
        stage.setScene(scene);
        stage.show();
    }
}

