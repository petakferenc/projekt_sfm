package hu.inf.unideb;

import javax.persistence.*;
import java.util.List;

public class JPA_DAO implements JPA_IFace{
    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Projekt");
    //private final EntityManager em = entityManagerFactory.createEntityManager();

    @Override
    public void saveCar(Car a) {
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(a);
            em.getTransaction().commit();
        }
        catch (Exception e)
        {
            if(em.getTransaction() !=null)
            {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }
        finally {
            em.close();
        }

    }

    @Override
    public void deletCar(Car a) {
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            em.getTransaction().begin();
            em.remove(a);
            em.getTransaction().commit();
        }
        catch (Exception e)
        {
            if(em.getTransaction() !=null)
            {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }
        finally {
            em.close();
        }
    }

    @Override
    public void saveParkingSpace(ParkingSpace a) {
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(a);
            em.getTransaction().commit();
        }
        catch (Exception e)
        {
            if(em.getTransaction() !=null)
            {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }
        finally {
            em.close();
        }

    }

    @Override
    public void deletParkingSpace(ParkingSpace a) {
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            em.getTransaction().begin();
            em.remove(a);
            em.getTransaction().commit();
        }
        catch (Exception e)
        {
            if(em.getTransaction() !=null)
            {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }
        finally {
            em.close();
        }
    }

    public Car findCarLicense(String license)
    {
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            //return em.find(Car.class, license);
            TypedQuery<Car> query = em.createQuery("SELECT a FROM Car a WHERE a.license=:license", Car.class);
            query.setParameter("license",license);
            return query.getSingleResult();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
            em.close();
        }
        return null;
    }

    public ParkingSpace findPSStatusByType(ParkingSpace.Status status, ParkingSpace.Type type)
    {
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            //return em.find(ParkingSpace.class,ParkingSpace.Status.FREE);
            TypedQuery<ParkingSpace> query = em.createQuery("SELECT a FROM ParkingSpace a WHERE a.status=:status AND a.type=:type", ParkingSpace.class);
            query.setParameter("status",status);
            query.setParameter("type",type);
            return query.getSingleResult();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
            em.close();
        }
        return null;
    }

    /*@Override
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
    }*/


    @Override
    public void close() throws Exception {
        //entityManager.close();
        entityManagerFactory.close();
    }

}
