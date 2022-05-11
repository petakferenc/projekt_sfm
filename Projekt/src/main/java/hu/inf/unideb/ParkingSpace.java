package hu.inf.unideb;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="ParkingSpace")
public class ParkingSpace implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @OneToOne
    @JoinColumn(name = "Car_License", referencedColumnName = "license")
    private Car car;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Enumerated(EnumType.STRING)
    private Type type;
    private java.time.LocalDate date;

    public enum Status{
        FREE,USE,RENT
    }
    public enum Type{
        CAR,BUS,DISABLED
    }


}
