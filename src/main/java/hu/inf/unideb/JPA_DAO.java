package hu.inf.unideb;

import javax.persistence.*;

public class JPA_DAO implements JPA_IFace {
    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Projekt");
    private EntityManager em = entityManagerFactory.createEntityManager();

    void setEntityManager(EntityManager em) {
        this.em = em;
    }

    @Override
    public void saveCar(Car a) {
        //EntityManager em = entityManagerFactory.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(a);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction() != null) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }
        /*finally {
            em.close();
        }*/

    }

    @Override
    public void deleteCar(Car a) {
        //EntityManager em = entityManagerFactory.createEntityManager();
        try {

            ParkingSpace ps = findPSByLicense(a.getLicense());
            if(ps.getStatus() == ParkingSpace.Status.RENTOUT)
                return;
            em.getTransaction().begin();
            if(ps.getStatus() == ParkingSpace.Status.RENTIN)
            {
                ps.setStatus(ParkingSpace.Status.RENTOUT);
                em.persist(ps);
            }
            else
            {
                ps.setStatus(ParkingSpace.Status.FREE);
                ps.setCar(null);
                ps.setDate(null);
                em.persist(ps);
                em.remove(em.contains(a) ? a : em.merge(a));
            }
            //System.out.println(ps.toString());

            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction() != null) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }
        /*finally {
            em.close();
        }*/
    }

    @Override
    public void saveParkingSpace(ParkingSpace a) {
        //EntityManager em = entityManagerFactory.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(a);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction() != null) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }
        /*finally {
            em.close();
        }*/

    }

    @Override
    public void deletParkingSpace(ParkingSpace a) {
        //EntityManager em = entityManagerFactory.createEntityManager();
        try {
            //deleteCar(a.getCar());
            em.getTransaction().begin();
            em.remove(a);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction() != null) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }
        /*finally {
            em.close();
        }*/
    }

    @Override
    public void saveBlackList(BlackList a) {
        //EntityManager em = entityManagerFactory.createEntityManager();
        try {
            if (findBlacListByLicense(a.getLicense()) != null)
                return;
            em.getTransaction().begin();
            em.persist(a);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction() != null) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }
        /*finally {
            em.close();
        }*/

    }

    @Override
    public void deletBlackList(BlackList a) {
        //EntityManager em = entityManagerFactory.createEntityManager();
        try {
            em.getTransaction().begin();
            em.remove(a);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction() != null) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }
        /*finally {
            em.close();
        }*/
    }

    public Car findCarByLicense(String license) {
        //EntityManager em = entityManagerFactory.createEntityManager();
        try {
            //return em.find(Car.class, license);
            TypedQuery<Car> query = em.createQuery("SELECT a FROM Car a WHERE a.license=:license", Car.class);
            query.setParameter("license", license);
            return query.getResultStream().findFirst().orElse(null);//getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*finally {
            em.close();
        }*/
        return null;
    }

    public ParkingSpace findPSByLicense(String license) {
        //EntityManager em = entityManagerFactory.createEntityManager();
        try {
            TypedQuery<ParkingSpace> query = em.createQuery("SELECT a FROM ParkingSpace a WHERE a.car.license=:license", ParkingSpace.class);
            query.setParameter("license", license);
            return query.getResultStream().findFirst().orElse(null);//getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*finally {
            em.close();
        }*/
        return null;
    }

    public BlackList findBlacListByLicense(String license) {
        //EntityManager em = entityManagerFactory.createEntityManager();
        try {
            //return em.find(Car.class, license);
            TypedQuery<BlackList> query = em.createQuery("SELECT a FROM BlackList a WHERE a.license=:license", BlackList.class);
            query.setParameter("license", license);
            return query.getResultStream().findFirst().orElse(null);//getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*finally {
            em.close();
        }*/
        return null;
    }

    public ParkingSpace findPSStatusByType(ParkingSpace.Status status, ParkingSpace.Type type) {
        //EntityManager em = entityManagerFactory.createEntityManager();
        try {
            //return em.find(ParkingSpace.class,ParkingSpace.Status.FREE);
            TypedQuery<ParkingSpace> query = em.createQuery("SELECT a FROM ParkingSpace a WHERE a.status=:status AND a.type=:type", ParkingSpace.class);
            query.setParameter("status", status);
            query.setParameter("type", type);
            return query.getResultStream().findFirst().orElse(null);// .getSingleResult().;
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*finally {
            em.close();
        }*/
        return null;
    }

    @Override
    public long GetFreeSpaces() {
        //EntityManager em = entityManagerFactory.createEntityManager();
        try {
            //return em.find(ParkingSpace.class,ParkingSpace.Status.FREE);
            TypedQuery<ParkingSpace> query = em.createQuery("SELECT a FROM ParkingSpace a WHERE a.status=:status", ParkingSpace.class);
            query.setParameter("status", ParkingSpace.Status.FREE);
            return query.getResultStream().count();
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*finally {
            em.close();
        }*/
        return -1;
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
        //em.close();
        entityManagerFactory.close();
    }

    @Override
    public void flushAndClear() {
        em.flush();
        em.clear();
    }

}
