package meow;

import java.util.ArrayList;

/**
 * Manages the collection of tasks in the chatbot.
 */
public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>(100);

    /**
     * Adds a task to the task list.
     *
     * @param task the task to add
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Removes the task at the specified index from the task list.
     *
     * @param taskIndex the index of the task to remove
     * @return the removed task
     */
    public Task delete(int taskIndex) {
        return tasks.remove(taskIndex);
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return the number of tasks
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the task at the specified index.
     *
     * @param taskIndex the index of the task
     * @return the task at the specified index
     */
    public Task getTask(int taskIndex) {
        return tasks.get(taskIndex);
    }
}
