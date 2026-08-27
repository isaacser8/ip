package meow;

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

    /**
     * Finds tasks whose descriptions contain the specified keyword.
     *
     * @param keyword the keyword to search for.
     * @return a task list containing the matching tasks.
     */
    public TaskList findTasks(String keyword) {
        TaskList matches = new TaskList();
        String lowerKeyword = keyword.toLowerCase();
        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(lowerKeyword)) {
                matches.add(task);
            }
        }
        return matches;
    }
}
