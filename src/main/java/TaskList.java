import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>(100);

    public void add(Task task) {
        tasks.add(task);
    }

    public Task delete(int taskIndex) {
        return tasks.remove(taskIndex);
    }

    public int size() {
        return tasks.size();
    }

    public Task getTask(int taskIndex) {
        return tasks.get(taskIndex);
    }
}
