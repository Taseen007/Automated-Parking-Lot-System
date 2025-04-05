/**
 * Defines the interfaces for parking lot operations.
 * Following Interface Segregation Principle - Separating interfaces by responsibility.
 */

/**
 * Core parking operations interface
 */
interface ParkingService {
    int parkCar(Car car);
    boolean leaveCar(int slotNumber);
    String getStatus();
}

/**
 * Color-based query operations interface
 */
interface ColorQueryService {
    String getRegistrationNumbersForColor(String color);
    String getSlotNumbersForColor(String color);
}

/**
 * Registration-based query operations interface
 */
interface RegistrationQueryService {
    String getSlotNumberForRegistrationNumber(String registrationNumber);
}
