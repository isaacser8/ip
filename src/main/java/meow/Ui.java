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
        System.out.println(SEPARATOR);
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < matches.size(); i++) {
            System.out.println(i + 1 + ". " + matches.getTask(i));
        }
        System.out.println(SEPARATOR);
    }

    /**
     * Returns the greeting message.
     *
     * @return the greeting message
     */
    public String getGreetingMessage() {
        return "Meow! Welcome back.\nStart yapping, I'm all ears!";
    }

    /**
     * Returns the farewell message.
     *
     * @return the farewell message
     */
    public String getFarewellMessage() {
        return "Marvellous yap session. Let's catch up soon meow!";
    }

    /**
     * Returns the task list as a formatted message.
     *
     * @param taskList the task list to display
     * @return the formatted task list
     */
    public String getTaskListMessage(TaskList taskList) {
        return formatTaskList("Here are the tasks in your list:", taskList);
    }

    /**
     * Returns a message confirming that a task was marked as completed.
     *
     * @param task the task that was marked
     * @return the confirmation message
     */
    public String getTaskMarkedMessage(Task task) {
        return "Meow! I've marked this task as done:\n" + task;
    }

    /**
     * Returns a message confirming that a task was marked as not completed.
     *
     * @param task the task that was unmarked
     * @return the confirmation message
     */
    public String getTaskUnmarkedMessage(Task task) {
        return "Meow! I've marked this task as not done yet:\n" + task;
    }

    /**
     * Returns a message confirming that a task was added.
     *
     * @param task the task that was added
     * @param taskCount the total number of tasks
     * @return the confirmation message
     */
    public String getTaskAddedMessage(Task task, int taskCount) {
        return "Meow! I've added this task:\n"
                + task
                + "\nNow you have "
                + taskCount
                + " tasks in the list.";
    }

    /**
     * Returns a message confirming that a task was deleted.
     *
     * @param task the task that was deleted
     * @param taskCount the total number of tasks
     * @return the confirmation message
     */
    public String getTaskDeletedMessage(Task task, int taskCount) {
        return "Meow! I've removed this task:\n"
                + task
                + "\nNow you have "
                + taskCount
                + " tasks in the list.";
    }

    /**
     * Returns matching tasks as a formatted message.
     *
     * @param matches the matching tasks
     * @return the formatted matching task list
     */
    public String getMatchingTasksMessage(TaskList matches) {
        return formatTaskList("Here are the matching tasks in your list:", matches);
    }

    /**
     * Formats a task list with the given heading.
     *
     * @param heading the heading to display
     * @param taskList the task list to format
     * @return the formatted message
     */
    private String formatTaskList(String heading, TaskList taskList) {
        StringBuilder message = new StringBuilder(heading);

        for (int i = 0; i < taskList.size(); i++) {
            message.append("\n")
                    .append(i + 1)
                    .append(". ")
                    .append(taskList.getTask(i));
        }

        return message.toString();
    }
}
