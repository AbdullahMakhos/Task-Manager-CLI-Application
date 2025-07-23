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
        command = command.trim();
        int firstSpace = command.indexOf(' ');
        String cmd = (firstSpace == -1) ? command.toLowerCase() : command.substring(0, firstSpace).toLowerCase();
        String arg = (firstSpace == -1) ? "" : command.substring(firstSpace + 1).trim();
        
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
            case "done":
                setDone(arg);
                break;
            case "undone":
                setUnDone(arg);
                break;
            default:
                System.out.println("Unknown command: " + cmd);
        }
    }
    
    private void setUnDone(String input) {
    	try {
            int index = Integer.parseInt(input) - 1;
            if (index >= 0 && index < tasks.size()) {
                Task target = tasks.get(index);
                tasks.get(index).setDone(false);
                System.out.println("Marked incomplete: " + target.getDescription());
            } else {
                System.out.println("Error : Invalid task number");
            }
        } catch (NumberFormatException e) {
               System.out.println("Error :  Invalid task number");
                
        }
        
	}

	private void setDone(String input) {
		try {
            int index = Integer.parseInt(input) - 1;
            if (index >= 0 && index < tasks.size()) {
                Task target = tasks.get(index);
                tasks.get(index).setDone(true);
                System.out.println("Marked complete: " + target.getDescription());
            } else {
                System.out.println("Error : Invalid task number");
            }
        } catch (NumberFormatException e) {
               System.out.println("Error : Invalid task number");
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
	    description = description.trim();
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
                System.out.println("Error : Invalid task number");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error : Invalid task number");
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
