
import java.util.ArrayList; 

public class SavePoint {
	
	private ArrayList<Task> tasks;
	
	public SavePoint() {
		// TODO Auto-generated constructor stub
	}
	
	public SavePoint(ArrayList<Task> tasks) {
		this.tasks = tasks;
	}

	public ArrayList<Task> getTasks() {
		return tasks;
	}

	public void setTasks(ArrayList<Task> tasks) {
		this.tasks = tasks;
	}
	
}
