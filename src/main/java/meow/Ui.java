package meow;

/**
 * Handles all user output for the chatbot.
 */
public class Ui {
    private final String line = "____________________________________________________________";

    /**
     * Displays the chatbot greeting message.
     */
    public void showGreeting() {
        System.out.println(line);
        String banner = "███   ███ ███████  █████  ██     ██\n"
                      + "████ ████ ██      ██   ██ ██     ██\n"
                      + "██ ███ ██ █████   ██   ██ ██  █  ██\n"
                      + "██     ██ ██      ██   ██ ██ ███ ██\n"
                      + "██     ██ ███████  █████   ███ ███\n";
        System.out.println(banner);
        String greeting = "Meow! Welcome back. \n" + "Start yapping, I'm all ears!";
        System.out.println(greeting);
        System.out.println(line);
    }

    /**
     * Displays the chatbot farewell message.
     */
    public void showFarewell() {
        System.out.println(line);
        System.out.println("Marvellous yap session. Let's catch up soon meow!");
        System.out.println(line);
    }

    /**
     * Displays an error message to the user.
     *
     * @param e the error message to display
     */
    public void showError(String e) {
        System.out.println(line);
        System.out.println(e);
        System.out.println(line);
    }

    /**
     * Displays all tasks in the task list.c
     *
     * @param taskList the task list to display
     */
    public void showTaskList(TaskList taskList) {
        System.out.println(line);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(i + 1 + ". " + taskList.getTask(i));
        }
        System.out.println(line);
    }

    /**
     * Displays a message confirming that a task was marked as completed.
     *
     * @param task the task that was marked
     */
    public void showTaskMarked(Task task) {
        System.out.println(line);
        System.out.println("Meow! I've marked this task as done:");
        System.out.println(task);
        System.out.println(line);
    }

    /**
     * Displays a message confirming that a task was marked as not completed.
     *
     * @param task the task that was unmarked
     */
    public void showTaskUnmarked(Task task) {
        System.out.println(line);
        System.out.println("Meow! I've marked this task as not done yet:");
        System.out.println(task);
        System.out.println(line);
    }

    /**
     * Displays a message confirming that a task was added.
     *
     * @param task the task that was added
     * @param taskCount the total number of tasks after the addition
     */
    public void showTaskAdded(Task task, int taskCount) {
        System.out.println(line);
        System.out.println("Meow! I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        System.out.println(line);
    }

    /**
     * Displays a message confirming that a task was deleted.
     *
     * @param task the task that was deleted
     * @param taskCount the total number of tasks after the deletion
     */
    public void showTaskDeleted(Task task, int taskCount) {
        System.out.println(line);
        System.out.println("Meow! I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        System.out.println(line);
    }
}
