/**
 * Factory class for creating parking lot instances.
 * Following Dependency Inversion Principle - High-level modules depend on abstractions.
 */
public class ParkingLotFactory {
    private static ParkingLot parkingLot;
    
    public ParkingLot createParkingLot(int capacity) {
        ParkingStrategy strategy = new NearestSlotStrategy();
        parkingLot = new ParkingLot(capacity, strategy);
        return parkingLot;
    }
    
    public ParkingLot getCurrentParkingLot() {
        return parkingLot;
    }
}
