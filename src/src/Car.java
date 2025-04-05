/**
 * Represents a car in the parking system.
 * Following Single Responsibility Principle - Car class only represents a car entity.
 */
public class Car {
    private final String registrationNumber;
    private final String color;
    
    public Car(String registrationNumber, String color) {
        this.registrationNumber = registrationNumber;
        this.color = color;
    }
    
    public String getRegistrationNumber() {
        return registrationNumber;
    }
    
    public String getColor() {
        return color;
    }
    
    @Override
    public String toString() {
        return "Car{" +
                "registrationNumber='" + registrationNumber + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
