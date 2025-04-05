/**
 * Command pattern implementation for handling different parking lot commands.
 * Following Single Responsibility Principle - Each command handler has one responsibility.
 * Following Open/Closed Principle - New commands can be added without modifying existing code.
 */

// Abstract command interface
interface Command {
    String execute(String[] args);
}

// Command Handler for creating parking lot
class CreateParkingLotCommand implements Command {
    private ParkingLotFactory parkingLotFactory;
    
    public CreateParkingLotCommand(ParkingLotFactory parkingLotFactory) {
        this.parkingLotFactory = parkingLotFactory;
    }
    
    @Override
    public String execute(String[] args) {
        if (args.length != 2) {
            return "Invalid command format";
        }
        
        try {
            int capacity = Integer.parseInt(args[1]);
            ParkingLot parkingLot = parkingLotFactory.createParkingLot(capacity);
            return "Created a parking lot with " + capacity + " slots";
        } catch (NumberFormatException e) {
            return "Invalid capacity format";
        }
    }
}

// Command Handler for parking a car
class ParkCommand implements Command {
    private ParkingService parkingService;
    
    public ParkCommand(ParkingService parkingService) {
        this.parkingService = parkingService;
    }
    
    @Override
    public String execute(String[] args) {
        if (args.length != 3) {
            return "Invalid command format";
        }
        
        String registrationNumber = args[1];
        String color = args[2];
        Car car = new Car(registrationNumber, color);
        int slotNumber = parkingService.parkCar(car);
        
        if (slotNumber == -1) {
            return "Sorry, parking lot is full";
        } else {
            return "Allocated slot number: " + slotNumber;
        }
    }
}

// Command Handler for leaving a slot
class LeaveCommand implements Command {
    private ParkingService parkingService;
    
    public LeaveCommand(ParkingService parkingService) {
        this.parkingService = parkingService;
    }
    
    @Override
    public String execute(String[] args) {
        if (args.length != 2) {
            return "Invalid command format";
        }
        
        try {
            int slotNumber = Integer.parseInt(args[1]);
            boolean success = parkingService.leaveCar(slotNumber);
            
            if (success) {
                return "Slot number " + slotNumber + " is free";
            } else {
                return "Invalid slot number";
            }
        } catch (NumberFormatException e) {
            return "Invalid slot number format";
        }
    }
}

// Additional command handlers for other operations
class StatusCommand implements Command {
    private ParkingService parkingService;
    
    public StatusCommand(ParkingService parkingService) {
        this.parkingService = parkingService;
    }
    
    @Override
    public String execute(String[] args) {
        return parkingService.getStatus();
    }
}

class RegistrationNumbersForColorCommand implements Command {
    private ColorQueryService colorQueryService;
    
    public RegistrationNumbersForColorCommand(ColorQueryService colorQueryService) {
        this.colorQueryService = colorQueryService;
    }
    
    @Override
    public String execute(String[] args) {
        if (args.length != 2) {
            return "Invalid command format";
        }
        
        return colorQueryService.getRegistrationNumbersForColor(args[1]);
    }
}

class SlotNumberForRegistrationCommand implements Command {
    private RegistrationQueryService registrationQueryService;
    
    public SlotNumberForRegistrationCommand(RegistrationQueryService registrationQueryService) {
        this.registrationQueryService = registrationQueryService;
    }
    
    @Override
    public String execute(String[] args) {
        if (args.length != 2) {
            return "Invalid command format";
        }
        
        return registrationQueryService.getSlotNumberForRegistrationNumber(args[1]);
    }
}

class SlotNumbersForColorCommand implements Command {
    private ColorQueryService colorQueryService;
    
    public SlotNumbersForColorCommand(ColorQueryService colorQueryService) {
        this.colorQueryService = colorQueryService;
    }
    
    @Override
    public String execute(String[] args) {
        if (args.length != 2) {
            return "Invalid command format";
        }
        
        return colorQueryService.getSlotNumbersForColor(args[1]);
    }
}
