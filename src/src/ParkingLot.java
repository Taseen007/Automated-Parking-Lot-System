/**
 * Main parking lot implementation that follows SOLID principles.
 * - Single Responsibility: Each method has a single responsibility
 * - Open/Closed: Can be extended without modification via strategy pattern
 * - Liskov Substitution: Implementations honor interface contracts
 * - Interface Segregation: Methods grouped by client needs
 * - Dependency Inversion: Depends on abstractions rather than concrete classes
 */
public class ParkingLot implements ParkingService, ColorQueryService, RegistrationQueryService {
    private final int capacity;
    private final Slot[] slots;
    private final ParkingStrategy parkingStrategy;
    
    public ParkingLot(int capacity, ParkingStrategy parkingStrategy) {
        this.capacity = capacity;
        this.slots = new Slot[capacity + 1]; // +1 because slots are 1-indexed
        this.parkingStrategy = parkingStrategy;
        
        // Initialize all slots
        for (int i = 1; i <= capacity; i++) {
            slots[i] = new Slot(i);
        }
    }
    
    // Factory method - adheres to Dependency Inversion Principle
    public static ParkingLot createWithDefaultStrategy(int capacity) {
        return new ParkingLot(capacity, new NearestSlotStrategy());
    }
    
    @Override
    public int parkCar(Car car) {
        int slotNumber = parkingStrategy.getNextSlot(slots);
        
        if (slotNumber != -1) {
            slots[slotNumber].assignCar(car);
        }
        
        return slotNumber;
    }
    
    @Override
    public boolean leaveCar(int slotNumber) {
        if (slotNumber < 1 || slotNumber > capacity) {
            return false; // Invalid slot number
        }
        
        slots[slotNumber].removeCar();
        return true;
    }
    
    @Override
    public String getStatus() {
        StringBuilder status = new StringBuilder("Slot No. Registration No. Colour\n");
        
        for (int i = 1; i <= capacity; i++) {
            if (!slots[i].isAvailable()) {
                Car car = slots[i].getCar();
                status.append(i).append(" ")
                      .append(car.getRegistrationNumber()).append(" ")
                      .append(car.getColor()).append("\n");
            }
        }
        
        return status.toString();
    }
    
    @Override
    public String getRegistrationNumbersForColor(String color) {
        StringBuilder registrationNumbers = new StringBuilder();
        boolean found = false;
        
        for (int i = 1; i <= capacity; i++) {
            if (!slots[i].isAvailable() && slots[i].getCar().getColor().equalsIgnoreCase(color)) {
                if (found) {
                    registrationNumbers.append(", ");
                }
                registrationNumbers.append(slots[i].getCar().getRegistrationNumber());
                found = true;
            }
        }
        
        return found ? registrationNumbers.toString() : "Not found";
    }
    
    @Override
    public String getSlotNumberForRegistrationNumber(String registrationNumber) {
        for (int i = 1; i <= capacity; i++) {
            if (!slots[i].isAvailable() && 
                slots[i].getCar().getRegistrationNumber().equals(registrationNumber)) {
                return String.valueOf(i);
            }
        }
        
        return "Not found";
    }
    
    @Override
    public String getSlotNumbersForColor(String color) {
        StringBuilder slotNumbers = new StringBuilder();
        boolean found = false;
        
        for (int i = 1; i <= capacity; i++) {
            if (!slots[i].isAvailable() && slots[i].getCar().getColor().equalsIgnoreCase(color)) {
                if (found) {
                    slotNumbers.append(", ");
                }
                slotNumbers.append(i);
                found = true;
            }
        }
        
        return found ? slotNumbers.toString() : "Not found";
    }
}
