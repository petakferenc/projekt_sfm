package hu.inf.unideb;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;




public class blDisplayController implements Initializable {

    @FXML
    private ListView<BlackList> blackListView;

    private JPA_IFace iFace = FXMLController.getiFace();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        blackListView.setItems(FXCollections.observableArrayList(iFace.printBlacList()));

        //bl.setText("Fekete listán lévő rendszámok");

    }


}

