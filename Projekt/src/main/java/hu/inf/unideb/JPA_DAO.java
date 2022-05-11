package hu.inf.unideb;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class JPA_DAO implements JPA_IFace{
    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Projekt");
    private final EntityManager entityManager = entityManagerFactory.createEntityManager();

    @Override
    public void saveCar(Car a) {
        entityManager.getTransaction().begin();
        entityManager.persist(a);
        entityManager.getTransaction().commit();
    }

    @Override
    public void deletCar(Car a) {
        entityManager.getTransaction().begin();
        entityManager.remove(a);
        entityManager.getTransaction().commit();
    }

    @Override
    public void saveParkingSpace(ParkingSpace a) {
        entityManager.getTransaction().begin();
        entityManager.persist(a);
        entityManager.getTransaction().commit();
    }

    @Override
    public void deletParkingSpace(ParkingSpace a) {
        entityManager.getTransaction().begin();
        entityManager.remove(a);
        entityManager.getTransaction().commit();
    }


    @Override
    public void close() throws Exception {
        entityManager.close();
        entityManagerFactory.close();
    }

}
