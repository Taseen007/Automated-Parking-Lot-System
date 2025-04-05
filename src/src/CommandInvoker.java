import java.util.HashMap;
import java.util.Map;

/**
 * Command Invoker that maps command names to their handlers.
 * Following Single Responsibility Principle - Only handles command routing.
 */
public class CommandInvoker {
    private Map<String, Command> commandMap = new HashMap<>();
    
    public void register(String commandName, Command command) {
        commandMap.put(commandName, command);
    }
    
    public String executeCommand(String commandLine) {
        String[] parts = commandLine.split(" ");
        String commandName = parts[0];
        
        Command command = commandMap.get(commandName);
        if (command == null) {
            return "Invalid command";
        }
        
        return command.execute(parts);
    }
}
