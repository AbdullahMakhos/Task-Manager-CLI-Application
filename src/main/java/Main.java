import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static final String SAVE_FILE = "tasks.json";
    private static TaskManager taskManager;
    private static final ObjectMapper mapper = new ObjectMapper()
        .registerModule(new JavaTimeModule());
    
    public static void main(String[] args) {
        taskManager = loadTasks();
        
        System.out.println("Task Manager - Commands: show, add <task>, remove <number/name>, clear, exit");
        
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.print("> ");
                String input = scanner.nextLine().trim();
                
                if (input.equalsIgnoreCase("exit")) {
                    saveTasks();
                    break;
                }
                
                taskManager.processCommand(input);
                saveTasks();
            }
        }
    }
    
    private static TaskManager loadTasks() {
        File file = new File(SAVE_FILE);
        if (file.exists()) {
            try {
                return mapper.readValue(file, TaskManager.class);
            } catch (IOException e) {
                System.out.println("Error loading tasks: " + e.getMessage());
            }
        }
        return new TaskManager();
    }
    
    private static void saveTasks() {
        try {
            mapper.writerWithDefaultPrettyPrinter()
                 .writeValue(new File(SAVE_FILE), taskManager);
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }
}
