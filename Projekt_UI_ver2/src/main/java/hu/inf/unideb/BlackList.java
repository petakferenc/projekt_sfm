package hu.inf.unideb;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="BlackList")
public class BlackList implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String license;

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

    @Override
    public String toString() {
        return "BlackList{" +
                "id=" + id +
                ", license='" + license + '\'' +
                '}';
    }
}
