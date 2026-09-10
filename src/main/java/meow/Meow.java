package meow;

import java.io.IOException;
import java.util.Scanner;

/**
 * Represents the Meow chatbot and coordinates user interaction,
 * command parsing, task storage, and persistence.
 */
public class Meow {
    private final Ui ui;
    private final Parser parser;
    private final Storage storage;
    private TaskList tasks;
    private boolean lastResponseWasError;
    private String startupErrorMessage;

    /**
     * Creates a Meow chatbot and loads previously saved tasks.
     */
    public Meow() {
        this.ui = new Ui();
        this.parser = new Parser();
        this.storage = new Storage();
        try {
            tasks = storage.loadTasks();
        } catch (IOException e) {
            startupErrorMessage =
                    "Meow! I couldn't load your saved tasks because the data file is invalid. "
                            + "Please fix or remove data/meow.txt and restart Meow.";
            ui.showError(startupErrorMessage);
            tasks = new TaskList();
        }
    }

    /**
     * Runs the chatbot command loop until the user exits.
     */
    public void run() {
        ui.showGreeting();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();
            System.out.println(getResponse(input));

            if (input.strip().equals("bye")) {
                break;
            }
        }
    }

    /**
     * Processes a user command and returns the chatbot's response.
     *
     * @param input the user command
     * @return the chatbot's response
     */
    public String getResponse(String input) {
        input = input.strip().replaceFirst("\\s+", " ");
        lastResponseWasError = false;

        try {
            if (input.equals("bye")) {
                return ui.getFarewellMessage();

            } else if (input.equals("list")) {
                return ui.getTaskListMessage(tasks);

            } else if (input.equals("find")) {
                throw new MeowException("Meow! I need a keyword to sniff out those tasks.");

            } else if (input.startsWith("find ")) {
                return findTasks(input);

            } else if (input.equals("sort")) {
                return sortTasks();

            } else if (input.equals("mark")) {
                throw new MeowException("Oops, I need a task number for that.");

            } else if (input.startsWith("mark ")) {
                return markTask(input);

            } else if (input.equals("unmark")) {
                throw new MeowException("Oops, I need a task number for that.");

            } else if (input.startsWith("unmark ")) {
                return unmarkTask(input);

            } else if (input.startsWith("todo ")
                    || input.startsWith("deadline ")
                    || input.startsWith("event ")) {
                return addTask(input);

            } else if (input.equals("delete")) {
                throw new MeowException("Oops, I need a task number for that.");

            } else if (input.startsWith("delete ")) {
                return deleteTask(input);

            } else if (input.equals("todo")) {
                throw new MeowException("Meow! A todo needs a description.");

            } else if (input.equals("deadline")) {
                throw new MeowException(
                        "Meow! A deadline needs a description and a /by date.");

            } else if (input.equals("event")) {
                throw new MeowException(
                        "Meow! An event needs a description, a /from date and a /to date.");

            } else {
                throw new MeowException(
                        "Meow! I'm sorry, but I don't know what that means.");
            }

        } catch (MeowException e) {
            lastResponseWasError = true;
            return e.getMessage();
        } catch (IOException e) {
            lastResponseWasError = true;
            return "Meow! Something went wrong while saving the tasks.";
        }
    }

    /**
     * Starts the Meow chatbot.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        new Meow().run();
    }

    /**
     * Converts the task number in a user command into a zero-based task index.
     *
     * @param input the user command containing the task number
     * @return the zero-based task index
     * @throws MeowException if the task number is invalid
     */
    private int getTaskIndex(String input) throws MeowException {
        String[] parts = input.trim().split("\\s+");

        if (parts.length < 2) {
            throw new MeowException("Oops, I need a task number for that.");
        }

        if (parts.length > 2) {
            throw new MeowException(
                    "Meow! Please use the format: " + parts[0] + " TASK_NUMBER.");
        }

        int taskNumber;

        try {
            taskNumber = Integer.parseInt(parts[1]);
        } catch (NumberFormatException e) {
            throw new MeowException(
                    "That doesn't look like a task number to me, meow.");
        }

        if (taskNumber <= 0) {
            throw new MeowException("Meow! Task number must be positive.");
        }

        if (taskNumber > tasks.size()) {
            throw new MeowException(
                    "Meow! I can't find task number " + taskNumber + " in your list.");
        }

        return taskNumber - 1;
    }

    /**
     * Finds tasks that match the keyword in the user command.
     *
     * @param input the find command
     * @return the formatted list of matching tasks
     * @throws MeowException if the keyword is invalid
     */
    private String findTasks(String input) throws MeowException {
        String keyword = parser.parseFindKeyword(input);
        TaskList matches = tasks.findTasks(keyword);
        return ui.getMatchingTasksMessage(matches);
    }

    /**
     * Marks the task specified by the user as completed.
     *
     * @param input the mark command
     * @return the confirmation message
     * @throws MeowException if the task number is invalid
     * @throws IOException if the updated task list cannot be saved
     */
    private String markTask(String input) throws MeowException, IOException {
        ensureStorageReady();
        int taskIndex = getTaskIndex(input);
        Task task = tasks.getTask(taskIndex);

        if (task.isDone()) {
            throw new MeowException("Meow! This task is already marked as done.");
        }

        task.markAsDone();
        storage.saveTasks(tasks);

        return ui.getTaskMarkedMessage(task);
    }

    /**
     * Marks the specified task as not completed.
     *
     * @param input the unmark command
     * @return the confirmation message
     * @throws MeowException if the task number is invalid
     * @throws IOException if the task list cannot be saved
     */
    private String unmarkTask(String input) throws MeowException, IOException {
        ensureStorageReady();
        int taskIndex = getTaskIndex(input);
        Task task = tasks.getTask(taskIndex);

        if (!task.isDone()) {
            throw new MeowException("Meow! This task is already unmarked.");
        }

        task.markAsNotDone();
        storage.saveTasks(tasks);

        return ui.getTaskUnmarkedMessage(task);
    }

    /**
     * Adds a task from the user command.
     *
     * @param input the task command
     * @return the confirmation message
     * @throws MeowException if the task command is invalid
     * @throws IOException if the task list cannot be saved
     */
    private String addTask(String input) throws MeowException, IOException {
        ensureStorageReady();
        Task task = parser.parseTask(input);

        tasks.add(task);
        storage.saveTasks(tasks);

        return ui.getTaskAddedMessage(task, tasks.size());
    }

    /**
     * Deletes the task specified by the user.
     *
     * @param input the delete command
     * @return the confirmation message
     * @throws MeowException if the task number is invalid
     * @throws IOException if the task list cannot be saved
     */
    private String deleteTask(String input) throws MeowException, IOException {
        ensureStorageReady();
        int taskIndex = getTaskIndex(input);
        Task deletedTask = tasks.delete(taskIndex);

        storage.saveTasks(tasks);

        return ui.getTaskDeletedMessage(deletedTask, tasks.size());
    }

    /**
     * Sorts tasks chronologically and saves the updated order.
     *
     * @return the confirmation message
     * @throws MeowException if the saved task data could not be loaded
     * @throws IOException if the task list cannot be saved
     */
    private String sortTasks() throws MeowException, IOException {
        ensureStorageReady();
        tasks.sortChronologically();
        storage.saveTasks(tasks);

        return ui.getTasksSortedMessage();
    }

    /**
     * Checks whether the most recent response was caused by an error.
     *
     * @return true if the most recent response was an error, false otherwise
     */
    public boolean wasLastResponseError() {
        return lastResponseWasError;
    }

    /**
     * Returns the error encountered while loading saved tasks.
     *
     * @return the startup error message, or null if loading succeeded
     */
    public String getStartupErrorMessage() {
        return startupErrorMessage;
    }

    /**
     * Ensures that task data was loaded successfully before allowing modifications.
     *
     * @throws MeowException if the saved task data could not be loaded
     */
    private void ensureStorageReady() throws MeowException {
        if (startupErrorMessage != null) {
            throw new MeowException(
                    "Meow! I can't modify tasks while the saved data file is invalid. "
                            + "Please fix or remove data/meow.txt and restart Meow.");
        }
    }
}
