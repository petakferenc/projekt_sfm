package hu.inf.unideb;

import hu.inf.unideb.model.ParkingSpace;
import hu.inf.unideb.model.ParkingSpaceDAO;

public class Controller {

    public void saveParking(String license) {
        try (ParkingSpaceDAO parkingSpaceDAO = new ParkingSpaceDAO()) {
            parkingSpaceDAO.saveParkingSpaces();
            ParkingSpace space = parkingSpaceDAO.getFreeParkingSpace();
            parkingSpaceDAO.saveParking(license, space);
        }
    }
}
