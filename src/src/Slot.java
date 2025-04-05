/**
 * Represents a parking slot.
 * Following Single Responsibility Principle - Slot only manages its state.
 */
public class Slot {
    private final int slotNumber;
    private boolean isAvailable;
    private Car car;
    
    public Slot(int slotNumber) {
        this.slotNumber = slotNumber;
        this.isAvailable = true;
        this.car = null;
    }
    
    public boolean isAvailable() {
        return isAvailable;
    }
    
    public void assignCar(Car car) {
        this.car = car;
        this.isAvailable = false;
    }
    
    public void removeCar() {
        this.car = null;
        this.isAvailable = true;
    }
    
    public Car getCar() {
        return car;
    }
    
    public int getSlotNumber() {
        return slotNumber;
    }
}
