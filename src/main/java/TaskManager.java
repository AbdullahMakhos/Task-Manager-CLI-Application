import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private final List<Task> tasks;
    
    public TaskManager() {
        this.tasks = new ArrayList<>();
    }
    
    @JsonCreator
    public TaskManager(@JsonProperty("tasks") List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }
    
    public void processCommand(String command) {
        String[] parts = command.trim().split(" ", 2);
        String cmd = parts[0].toLowerCase();
        String arg = parts.length > 1 ? parts[1] : "";
        
        switch (cmd) {
            case "show":
                showTasks();
                break;
            case "add":
                addTask(arg);
                break;
            case "remove":
                removeTask(arg);
                break;
            case "clear":
                clearTasks();
                break;
            default:
                System.out.println("Unknown command: " + cmd);
        }
    }
    
    private void showTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks found.");
            return;
        }
        
        System.out.println("TASKS:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }
    
    private void addTask(String description) {
        if (description.isEmpty()) {
            System.out.println("Error: Task description cannot be empty");
            return;
        }
        tasks.add(new Task(description));
        System.out.println("Added: " + description);
    }
    
    private void removeTask(String input) {
        try {
            int index = Integer.parseInt(input) - 1;
            if (index >= 0 && index < tasks.size()) {
                Task removed = tasks.remove(index);
                System.out.println("Removed: " + removed.getDescription());
            } else {
                System.out.println("Invalid task number");
            }
        } catch (NumberFormatException e) {
            // Try to remove by description if not a number
            boolean removed = tasks.removeIf(task -> 
                task.getDescription().equalsIgnoreCase(input));
            System.out.println(removed ? "Removed: " + input : "Task not found: " + input);
        }
    }
    
    private void clearTasks() {
        tasks.clear();
        System.out.println("All tasks cleared");
    }
    
    public List<Task> getTasks() {
        return new ArrayList<>(tasks);
    }
}
