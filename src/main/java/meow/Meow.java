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
            ui.showError("Meow! Something went wrong while loading the tasks.");
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

            if (input.equals("bye")) {
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
        try {
            if (input.equals("bye")) {
                return ui.getFarewellMessage();

            } else if (input.equals("list")) {
                return ui.getTaskListMessage(tasks);

            } else if (input.equals("find")) {
                throw new MeowException("Meow! Please specify a keyword.");

            } else if (input.startsWith("find ")) {
                return findTasks(input);

            } else if (input.equals("mark")) {
                throw new MeowException("Meow! Please specify a task number.");

            } else if (input.startsWith("mark ")) {
                return markTask(input);

            } else if (input.equals("unmark")) {
                throw new MeowException("Meow! Please specify a task number.");

            } else if (input.startsWith("unmark ")) {
                return unmarkTask(input);

            } else if (input.startsWith("todo ")
                    || input.startsWith("deadline ")
                    || input.startsWith("event ")) {
                return addTask(input);

            } else if (input.equals("delete")) {
                throw new MeowException("Meow! Please specify a task number.");

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
            return e.getMessage();
        } catch (IOException e) {
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
        String[] parts = input.split(" ");
        int taskNumber;

        try {
            taskNumber = Integer.parseInt(parts[1]);
        } catch (NumberFormatException e) {
            throw new MeowException("Meow! Task number must be a number.");
        }
        if (taskNumber <= 0) {
            throw new MeowException("Meow! Task number must be positive.");
        }
        if (taskNumber > tasks.size()) {
            throw new MeowException("Meow! There is no task number " + taskNumber + ".");
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
        int taskIndex = getTaskIndex(input);
        Task task = tasks.getTask(taskIndex);

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
        int taskIndex = getTaskIndex(input);
        Task task = tasks.getTask(taskIndex);

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
        int taskIndex = getTaskIndex(input);
        Task deletedTask = tasks.delete(taskIndex);

        storage.saveTasks(tasks);

        return ui.getTaskDeletedMessage(deletedTask, tasks.size());
    }
}
