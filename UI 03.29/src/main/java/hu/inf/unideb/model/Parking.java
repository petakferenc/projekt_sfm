package hu.inf.unideb.model;

import javax.persistence.*;

@Entity
public class Parking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String license;
    @OneToOne
    private ParkingSpace space;

    public Parking(int id, String license, ParkingSpace space) {
        this.id = id;
        this.license = license;
        this.space = space;
    }

    public Parking(String license, ParkingSpace space) {
        this.license = license;
        this.space = space;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public ParkingSpace getSpace() {
        return space;
    }

    public void setSpace(ParkingSpace space) {
        this.space = space;
    }
}
