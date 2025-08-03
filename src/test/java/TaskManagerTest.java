import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class TaskManagerTest {
    private TaskManager taskManager;// test instance
    
    @BeforeEach
    void setUp() {
        taskManager = new TaskManager();
    }
    
    @Test
    void testAddTask() {
        taskManager.processCommand("add Buy milk");
        assertEquals(1, taskManager.getTasks().size());
        assertEquals("Buy milk", taskManager.getTasks().get(0).getDescription());
    }
    
    @Test
    void testAddEmptyTask() {
        taskManager.processCommand("add ");
        assertTrue(taskManager.getTasks().isEmpty());
    }
    
    @Test
    void testRemoveTask() {
        taskManager.processCommand("add Task 1");
        taskManager.processCommand("add Task 2");
        taskManager.processCommand("remove 1");
        assertEquals(1, taskManager.getTasks().size());
        assertEquals("Task 2", taskManager.getTasks().get(0).getDescription());
    }
    
    @Test
    void testRemoveInvalidTask() {
        taskManager.processCommand("add Task 1");
        taskManager.processCommand("remove 2"); // Invalid index
        assertEquals(1, taskManager.getTasks().size());
    }
    
    @Test
    void testMarkTaskDone() {
        taskManager.processCommand("add Task 1");
        taskManager.processCommand("done 1");
        assertTrue(taskManager.getTasks().get(0).isDone());
    }
    
    @Test
    void testMarkTaskUndone() {
        taskManager.processCommand("add Task 1");
        taskManager.processCommand("done 1");
        taskManager.processCommand("undone 1");
        assertFalse(taskManager.getTasks().get(0).isDone());
    }
    
    @Test
    void testClearTasks() {
        taskManager.processCommand("add Task 1");
        taskManager.processCommand("add Task 2");
        taskManager.processCommand("clear");
        assertTrue(taskManager.getTasks().isEmpty());
    }
    
    @Test
    void testShowTasksWhenEmpty() {
        // This would test console output - might need to mock System.out
        taskManager.processCommand("show");
    }
    
    @Test
    void testInvalidCommand() {
        taskManager.processCommand("invalid command");
        // Should show error message
    }
}