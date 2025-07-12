
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;

public class Task {
    private String description;
    private boolean isDone;
    private LocalDate date;
    
    @JsonCreator
    public Task(@JsonProperty("description") String description) {
        this.description = description;
        this.isDone = false;
        this.date = LocalDate.now();
    }
    
    // Required for Jackson deserialization
    public Task() {
        this.date = LocalDate.now();
    }

    // Getters and setters
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public boolean isDone() { return isDone; }
    public void setDone(boolean isDone) { this.isDone = isDone; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    
    @Override
    public String toString() {
        return (isDone ? "[X] " : "[ ] ") + description + " (" + date + ")";
    }
}