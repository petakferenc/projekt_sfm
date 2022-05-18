package hu.inf.unideb;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import static hu.inf.unideb.Car.Type.CAR;
import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class JPA_DAOTest {
    @Mock
    private EntityManager entityManager;
    @Mock
    private EntityManagerFactory entityManagerFactory;
    private JPA_DAO underTest;

    @BeforeEach //@Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveCar() {
        Car car = new Car(1, "ABC-123", CAR, 1500);
        underTest.saveCar(car);
    }
}