import java.util.Scanner;

/**
 * Main system class that orchestrates the parking lot application.
 * Following Single Responsibility Principle - Only handles user interaction.
 * Following Dependency Inversion Principle - Depends on abstractions not implementations.
 */
public class ParkingLotSystem {
    private static CommandInvoker commandInvoker;
    private static ParkingLotFactory parkingLotFactory;
    
    public static void main(String[] args) {
        initialize();
        
        Scanner scanner = new Scanner(System.in);
        String input;
        
        System.out.println("Welcome to Automated Parking Lot System");
        System.out.println("Enter commands or 'exit' to quit:");
        
        while (true) {
            System.out.print("> ");
            input = scanner.nextLine().trim();
            
            if (input.equals("exit")) {
                break;
            }
            
            String output = commandInvoker.executeCommand(input);
            System.out.println(output);
        }
        
        scanner.close();
        System.out.println("Exiting Parking Lot System. Goodbye!");
    }
    
    private static void initialize() {
        commandInvoker = new CommandInvoker();
        parkingLotFactory = new ParkingLotFactory();
        
        // Register commands
        commandInvoker.register("create_parking_lot", new CreateParkingLotCommand(parkingLotFactory));
        
        // These commands will be registered after parking lot is created in CreateParkingLotCommand
        commandInvoker.register("park", new ParkCommand(null) {
            @Override
            public String execute(String[] args) {
                ParkingLot parkingLot = parkingLotFactory.getCurrentParkingLot();
                if (parkingLot == null) {
                    return "Parking lot not initialized";
                }
                return new ParkCommand(parkingLot).execute(args);
            }
        });
        
        commandInvoker.register("leave", new LeaveCommand(null) {
            @Override
            public String execute(String[] args) {
                ParkingLot parkingLot = parkingLotFactory.getCurrentParkingLot();
                if (parkingLot == null) {
                    return "Parking lot not initialized";
                }
                return new LeaveCommand(parkingLot).execute(args);
            }
        });
        
        commandInvoker.register("status", new StatusCommand(null) {
            @Override
            public String execute(String[] args) {
                ParkingLot parkingLot = parkingLotFactory.getCurrentParkingLot();
                if (parkingLot == null) {
                    return "Parking lot not initialized";
                }
                return new StatusCommand(parkingLot).execute(args);
            }
        });
        
        commandInvoker.register("registration_numbers_for_cars_with_colour", 
            new RegistrationNumbersForColorCommand(null) {
                @Override
                public String execute(String[] args) {
                    ParkingLot parkingLot = parkingLotFactory.getCurrentParkingLot();
                    if (parkingLot == null) {
                        return "Parking lot not initialized";
                    }
                    return new RegistrationNumbersForColorCommand(parkingLot).execute(args);
                }
            });
        
        commandInvoker.register("slot_number_for_registration_number", 
            new SlotNumberForRegistrationCommand(null) {
                @Override
                public String execute(String[] args) {
                    ParkingLot parkingLot = parkingLotFactory.getCurrentParkingLot();
                    if (parkingLot == null) {
                        return "Parking lot not initialized";
                    }
                    return new SlotNumberForRegistrationCommand(parkingLot).execute(args);
                }
            });
        
        commandInvoker.register("slot_numbers_for_cars_with_colour", 
            new SlotNumbersForColorCommand(null) {
                @Override
                public String execute(String[] args) {
                    ParkingLot parkingLot = parkingLotFactory.getCurrentParkingLot();
                    if (parkingLot == null) {
                        return "Parking lot not initialized";
                    }
                    return new SlotNumbersForColorCommand(parkingLot).execute(args);
                }
            });
    }
}
