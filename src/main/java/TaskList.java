public class TaskList {
    private Task[] tasks = new Task[100];
    private int totalTaskCount = 0;

    public void add(Task task) {
        tasks[totalTaskCount] = task;
        totalTaskCount++;
    }

    public int size() {
        return this.totalTaskCount;
    }

    public Task getTask(int taskIndex) {
        return tasks[taskIndex];
    }
}
