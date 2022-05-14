package hu.inf.unideb;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class trafficLight {

     public static void setGreen(Circle red, Circle green){
        red.setFill(Color.valueOf("#948a89"));  //piros: #ff1c02 szurke: #948a89 zold: #03ff0a
        green.setFill(Color.valueOf("#03ff0a"));
    }
    public static void setRed(Circle red, Circle green){
        red.setFill(Color.valueOf("#ff1c02"));  //piros: #ff1c02 szurke: #948a89 zold: #03ff0a
        green.setFill(Color.valueOf("#948a89"));
    }
}

