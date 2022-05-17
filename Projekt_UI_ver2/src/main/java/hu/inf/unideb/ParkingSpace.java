package hu.inf.unideb;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name="ParkingSpace")
public class ParkingSpace implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne//(cascade = CascadeType.MERGE)
    @JoinColumn(name = "Car_License", referencedColumnName = "license")
    private Car car;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Enumerated(EnumType.STRING)
    private Type type;
    private LocalDateTime date;
    private int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public enum Status{
        FREE,USE,RENT
    }
    public enum Type{
        CAR,BUS,DISABLED
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "ParkingSpace{" +
                "id=" + id +
                ", car=" + car +
                ", status=" + status +
                ", type=" + type +
                ", date=" + date +
                ", position=" + position +
                '}';
    }
}
