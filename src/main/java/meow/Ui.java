package meow;

/**
 * Handles all user output for the chatbot.
 */
public class Ui {
    private static final String SEPARATOR = "____________________________________________________________";

    /**
     * Displays the chatbot greeting message.
     */
    public void showGreeting() {
        System.out.println(SEPARATOR);
        String banner = "███   ███ ███████  █████  ██     ██\n"
                + "████ ████ ██      ██   ██ ██     ██\n"
                + "██ ███ ██ █████   ██   ██ ██  █  ██\n"
                + "██     ██ ██      ██   ██ ██ ███ ██\n"
                + "██     ██ ███████  █████   ███ ███\n";
        System.out.println(banner);
        String greeting = "Meow! Welcome back. \n" + "Start yapping, I'm all ears!";
        System.out.println(greeting);
        System.out.println(SEPARATOR);
    }

    /**
     * Displays the chatbot farewell message.
     */
    public void showFarewell() {
        System.out.println(SEPARATOR);
        System.out.println("Marvellous yap session. Let's catch up soon meow!");
        System.out.println(SEPARATOR);
    }

    /**
     * Displays an error message to the user.
     *
     * @param message the error message to display
     */
    public void showError(String message) {
        System.out.println(SEPARATOR);
        System.out.println(message);
        System.out.println(SEPARATOR);
    }

    /**
     * Displays all tasks in the task list.
     *
     * @param taskList the task list to display
     */
    public void showTaskList(TaskList taskList) {
        System.out.println(SEPARATOR);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(i + 1 + ". " + taskList.getTask(i));
        }
        System.out.println(SEPARATOR);
    }

    /**
     * Displays a message confirming that a task was marked as completed.
     *
     * @param task the task that was marked
     */
    public void showTaskMarked(Task task) {
        System.out.println(SEPARATOR);
        System.out.println("Meow! I've marked this task as done:");
        System.out.println(task);
        System.out.println(SEPARATOR);
    }

    /**
     * Displays a message confirming that a task was marked as not completed.
     *
     * @param task the task that was unmarked
     */
    public void showTaskUnmarked(Task task) {
        System.out.println(SEPARATOR);
        System.out.println("Meow! I've marked this task as not done yet:");
        System.out.println(task);
        System.out.println(SEPARATOR);
    }

    /**
     * Displays a message confirming that a task was added.
     *
     * @param task the task that was added
     * @param taskCount the total number of tasks after the addition
     */
    public void showTaskAdded(Task task, int taskCount) {
        System.out.println(SEPARATOR);
        System.out.println("Meow! I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        System.out.println(SEPARATOR);
    }

    /**
     * Displays a message confirming that a task was deleted.
     *
     * @param task the task that was deleted
     * @param taskCount the total number of tasks after the deletion
     */
    public void showTaskDeleted(Task task, int taskCount) {
        System.out.println(SEPARATOR);
        System.out.println("Meow! I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        System.out.println(SEPARATOR);
    }

    /**
     * Displays tasks whose descriptions match the search keyword.
     *
     * @param matches the task list containing matching tasks.
     */
    public void showMatchingTasks(TaskList matches) {
        System.out.println(line);
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < matches.size(); i++) {
            System.out.println(i + 1 + ". " + matches.getTask(i));
        }
        System.out.println(line);
    }
}
