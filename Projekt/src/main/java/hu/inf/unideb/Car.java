package hu.inf.unideb;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="cars")
public class Car implements Serializable{
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Id
    private String license;
    @Enumerated(EnumType.STRING)
    private Type type;
    private int money;

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
