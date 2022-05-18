package hu.inf.unideb;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="Cars")
public class Car implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String license;
    @Enumerated(EnumType.STRING)
    private Type type;
    private int money;

    public Car(int id, String license, Type type, int money) {
        this.id = id;
        this.license = license;
        this.type = type;
        this.money = money;
    }

    public Car() {
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

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", license='" + license + '\'' +
                ", type=" + type +
                ", money=" + money +
                '}';
    }
}
