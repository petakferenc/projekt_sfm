package hu.inf.unideb;

public interface JPA_IFace extends AutoCloseable{

    public void saveCar(Car a);
    public void deletCar(Car a);
    public void saveParkingSpace(ParkingSpace a);
    public void deletParkingSpace(ParkingSpace a);
    public void close() throws Exception;
    public Car findCarLicense(String license);
}
