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



public class FeketelistaController implements Initializable {

    @FXML
    private TextField lp;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void listButton(ActionEvent actionEvent)throws IOException {
        FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/fxml/feketelistaDisplay.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = new Stage();
        stage.setTitle("Fekete Lista");
        stage.getIcons().add(new Image("/fxml/logo_ver_2_mini.png"));
        stage.setScene(scene);
        stage.show();
    }
    public void deleteFromList(ActionEvent actionEvent)throws IOException {
        String licensePlate = lp.getText();
    }
    public void addToList(ActionEvent actionEvent)throws IOException {
        String licensePlate = lp.getText();
    }


}

