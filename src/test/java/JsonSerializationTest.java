import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class JsonSerializationTest {
    private final ObjectMapper mapper = new ObjectMapper()
        .registerModule(new JavaTimeModule());
    private final String TEST_FILE = "test_tasks.json";
    
    @Test
    void testSaveAndLoadTasks() throws IOException {
        // Setup
        TaskManager original = new TaskManager();
        original.processCommand("add Task 1");
        original.processCommand("add Task 2");
        original.processCommand("done 1");
        
        // Save
        mapper.writerWithDefaultPrettyPrinter()
             .writeValue(new File(TEST_FILE), original);
        
        // Load
        TaskManager loaded = mapper.readValue(new File(TEST_FILE), TaskManager.class);
        
        // Verify
        List<Task> originalTasks = original.getTasks();
        List<Task> loadedTasks = loaded.getTasks();
        
        assertEquals(originalTasks.size(), loadedTasks.size());
        assertEquals(originalTasks.get(0).getDescription(), loadedTasks.get(0).getDescription());
        assertEquals(originalTasks.get(0).isDone(), loadedTasks.get(0).isDone());
        assertEquals(originalTasks.get(0).getDate(), loadedTasks.get(0).getDate());
        
        // Cleanup
        new File(TEST_FILE).delete();
    }
}