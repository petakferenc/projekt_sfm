package hu.inf.unideb;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import java.security.InvalidParameterException;
import java.util.stream.Stream;

import static hu.inf.unideb.Car.Type.CAR;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;

class JPA_DAOTest {
    @Mock
    private EntityManager entityManager;
    @Mock
    private EntityManagerFactory entityManagerFactory;
    @Mock
    private EntityTransaction entityTransaction;
    @Mock
    private TypedQuery<ParkingSpace> parkingSpaceTypedQuery;
    @InjectMocks
    private JPA_DAO underTest;

    @BeforeEach //@Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveCarShouldSaveAGivenCar() {
        given(entityManager.getTransaction()).willReturn(entityTransaction);
        Car car = new Car(1, "ABC-123", CAR, 1500);
        underTest.setEntityManager(entityManager);
        underTest.saveCar(car);
        then(entityManager).should(times(2)).getTransaction();
    }

    @Test
    void saveCarShouldRollBackWhenExceptionThrown() {
        given(entityManager.getTransaction()).willReturn(entityTransaction);
        willThrow(IllegalArgumentException.class).given(entityManager).persist(any(Car.class));
        Car car = new Car(1, "ABC-123", CAR, 1500);
        underTest.setEntityManager(entityManager);
        underTest.saveCar(car);
        then(entityManager).should(times(3)).getTransaction();
        then(entityTransaction).should().rollback();
    }

    @Test
    void deleteCarShouldDeleteCars() {
        Stream<ParkingSpace> build = Stream.<ParkingSpace>builder().add(new ParkingSpace()).build();
        given(entityManager.getTransaction()).willReturn(entityTransaction);
        given(entityManager.createQuery(any(String.class), eq(ParkingSpace.class))).willReturn(parkingSpaceTypedQuery);
        given(parkingSpaceTypedQuery.setParameter(any(String.class), any())).willReturn(parkingSpaceTypedQuery);
        given(parkingSpaceTypedQuery.getResultStream()).willReturn(build);
        given(entityManager.contains(any(Car.class))).willReturn(true);
        underTest.setEntityManager(entityManager);
        Car car = new Car(1, "ABC-123", CAR, 1500);
        underTest.deleteCar(car);
        then(entityManager).should(times(2)).getTransaction();
        then(entityManager).should().createQuery(any(String.class), eq(ParkingSpace.class));
        then(parkingSpaceTypedQuery).should().setParameter(any(String.class), any());
        then(parkingSpaceTypedQuery).should().getResultStream();
        then(entityManager).should().contains(any(Car.class));
    }

    @Test
    void deleteCarShouldRollBackWhenExceptionThrown() {
        Stream<ParkingSpace> build = Stream.<ParkingSpace>builder().add(new ParkingSpace()).build();
        given(entityManager.getTransaction()).willReturn(entityTransaction);
        given(entityManager.createQuery(any(String.class), eq(ParkingSpace.class))).willReturn(parkingSpaceTypedQuery);
        given(parkingSpaceTypedQuery.setParameter(any(String.class), any())).willReturn(parkingSpaceTypedQuery);
        given(parkingSpaceTypedQuery.getResultStream()).willReturn(build);
        given(entityManager.contains(any(Car.class))).willReturn(true);
        willThrow(InvalidParameterException.class).given(entityManager).persist(any());
        underTest.setEntityManager(entityManager);
        Car car = new Car(1, "ABC-123", CAR, 1500);
        underTest.deleteCar(car);
        then(entityManager).should(times(3)).getTransaction();
        then(entityManager).should().createQuery(any(String.class), eq(ParkingSpace.class));
        then(parkingSpaceTypedQuery).should().setParameter(any(String.class), any());
        then(parkingSpaceTypedQuery).should().getResultStream();
        then(entityTransaction).should().rollback();
    }

    @Test
    void saveParkingSpaceShouldSaveParkingSpace() {
        given(entityManager.getTransaction()).willReturn(entityTransaction);
        underTest.setEntityManager(entityManager);
        ParkingSpace a = new ParkingSpace();
        underTest.saveParkingSpace(a);
        then(entityManager).should().persist(a);
    }

    @Test
    void saveParkingSpaceShouldRollbackWhenExceptionThrown() {
        given(entityManager.getTransaction()).willReturn(entityTransaction);
        willThrow(IllegalArgumentException.class).given(entityManager).persist(any());
        underTest.setEntityManager(entityManager);
        ParkingSpace a = new ParkingSpace();
        underTest.saveParkingSpace(a);
        then(entityTransaction).should().rollback();
    }
}