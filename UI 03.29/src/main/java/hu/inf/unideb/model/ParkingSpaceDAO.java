package hu.inf.unideb.model;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class ParkingSpaceDAO implements AutoCloseable {
    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("br.com.fredericci.pu");
    private final EntityManager entityManager = entityManagerFactory.createEntityManager();

    public void close() {
        entityManager.close();
        entityManagerFactory.close();
    }

    public void saveParkingSpaces() {
        ParkingSpace parkingSpace = new ParkingSpace("name", true);
        entityManager.getTransaction().begin();
        entityManager.persist(parkingSpace);
        entityManager.getTransaction().commit();
    }

    public ParkingSpace getFreeParkingSpace() {
        return entityManager.find(ParkingSpace.class, 1);
    }

    public void saveParking(String license, ParkingSpace space) {
        Parking parking = new Parking(license, space);
        entityManager.getTransaction().begin();
        entityManager.persist(parking);
        entityManager.getTransaction().commit();
    }
}
