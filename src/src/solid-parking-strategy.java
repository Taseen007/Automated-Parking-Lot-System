/**
 * Defines the strategy for finding available parking slots.
 * Following Open/Closed Principle - We can extend with new strategies without modifying existing code.
 */
interface ParkingStrategy {
    int getNextSlot(Slot[] slots);
}

/**
 * Implementation that finds the nearest slot to the entrance.
 */
class NearestSlotStrategy implements ParkingStrategy {
    @Override
    public int getNextSlot(Slot[] slots) {
        // Find the closest available slot (lowest slot number)
        for (int i = 1; i < slots.length; i++) {
            if (slots[i].isAvailable()) {
                return i;
            }
        }
        return -1; // No available slots
    }
}
