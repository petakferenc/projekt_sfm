package hu.inf.unideb;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Car {
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Id
    private String license;
    private String type;
    private int money;
}
