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

            try {
                if (input.equals("bye")) {
                    break;

                } else if (input.equals("list")) {
                    ui.showTaskList(tasks);
                } else if (input.equals("mark")) {
                    throw new MeowException("Meow! Please specify a task number.");
                } else if (input.startsWith("mark ")) {

                    int taskIndex = getTaskIndex(input);
                    tasks.getTask(taskIndex).markAsDone();
                    storage.saveTasks(tasks);
                    ui.showTaskMarked(tasks.getTask(taskIndex));

                } else if (input.equals("unmark")) {
                    throw new MeowException("Meow! Please specify a task number.");

                } else if (input.startsWith("unmark ")) {

                    int taskIndex = getTaskIndex(input);
                    tasks.getTask(taskIndex).markAsNotDone();
                    storage.saveTasks(tasks);
                    ui.showTaskUnmarked(tasks.getTask(taskIndex));

                } else if (input.startsWith("todo ")
                        || input.startsWith("deadline ")
                        || input.startsWith("event ")) {

                    Task task = parser.parseTask(input);
                    tasks.add(task);
                    storage.saveTasks(tasks);
                    ui.showTaskAdded(task, tasks.size());

                } else if (input.equals("delete")) {
                    throw new MeowException("Meow! Please specify a task number.");
                } else if (input.startsWith("delete ")) {

                    int taskIndex = getTaskIndex(input);
                    Task deletedTask = tasks.delete(taskIndex);
                    storage.saveTasks(tasks);
                    ui.showTaskDeleted(deletedTask, tasks.size());

                } else if (input.equals("todo")) {
                    throw new MeowException("Meow! A todo needs a description.");
                } else if (input.equals("deadline")) {
                    throw new MeowException("Meow! A deadline needs a description and a /by date.");
                } else if (input.equals("event")) {
                    throw new MeowException("Meow! An event needs a description, a /from date and a /to date.");
                } else {
                    throw new MeowException("Meow! I'm sorry, but I don't know what that means.");
                }
            } catch (MeowException e) {
                ui.showError(e.getMessage());
            } catch (IOException e) {
                ui.showError("Meow! Something went wrong while saving the tasks.");
            }
        }

        ui.showFarewell();
    }

    /**
     * Starts the Meow chatbot.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        new Meow().run();
    }

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
}
