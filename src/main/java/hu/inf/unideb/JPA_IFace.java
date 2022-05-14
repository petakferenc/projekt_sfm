package hu.inf.unideb;

public interface JPA_IFace extends AutoCloseable{

    public void saveCar(Car a);
    public void deletCar(Car a);
    public void saveParkingSpace(ParkingSpace a);
    public void deletParkingSpace(ParkingSpace a);
    public void close() throws Exception;
    public void flushAndClear();
    public Car findCarByLicense(String license);
    public ParkingSpace findPSByLicense(String license);
    public BlackList findBlacListByLicense(String license);
    public ParkingSpace findPSStatusByType(ParkingSpace.Status status, ParkingSpace.Type type);
    public long GetFreeSpaces();
    public void saveBlackList(BlackList a);
    public void deletBlackList(BlackList a);
}