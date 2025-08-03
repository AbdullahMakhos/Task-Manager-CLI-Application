import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class TaskTest {
    private Task task;
    
    @BeforeEach
    void setUp() {
        task = new Task("Buy milk");
    }
    
    @Test
    void testTaskCreation() {
        assertEquals("Buy milk", task.getDescription());
        assertFalse(task.isDone());
        assertEquals(LocalDate.now(), task.getDate());
    }
    
    @Test
    void testMarkDone() {
        task.setDone(true);
        assertTrue(task.isDone());
    }
    
    @Test
    void testToString() {
        task.setDone(true);
        String taskString = task.toString();
        assertTrue(taskString.contains("[X] Buy milk"));
        assertTrue(taskString.contains(LocalDate.now().toString()));
    }
    
    @Test
    void testToStringWhenNotDone() {
        // Explicitly set to false (though it's false by default)
        task.setDone(false);
        String taskString = task.toString();
        assertTrue(taskString.contains("[ ] Buy milk"));
        assertTrue(taskString.contains(LocalDate.now().toString()));
    }
}
