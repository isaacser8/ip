import java.util.Scanner;

public class Meow {
    public static void main(String[] args) {
        Ui ui = new Ui();
        ui.showGreeting();

        Parser parser = new Parser();
        Scanner sc = new Scanner(System.in);
        TaskList tasks = new TaskList();

        while (true) {
            String input = sc.nextLine();

            try {
                if (input.equals("bye")) {
                    break;

                } else if (input.equals("list")) {
                    ui.showTaskList(tasks);
                } else if (input.equals("mark")) {
                    throw new MeowException("Meow! Please specify a task number.");
                } else if (input.startsWith("mark ")) {

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

                    int taskIndex = taskNumber - 1;
                    tasks.getTask(taskIndex).markAsDone();
                    ui.showTaskMarked(tasks.getTask(taskIndex));

                } else if (input.equals("unmark")) {
                    throw new MeowException("Meow! Please specify a task number.");

                } else if (input.startsWith("unmark ")) {

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

                    int taskIndex = taskNumber - 1;
                    tasks.getTask(taskIndex).markAsNotDone();
                    ui.showTaskUnmarked(tasks.getTask(taskIndex));

                } else if (input.startsWith("todo ")
                        || input.startsWith("deadline ")
                        || input.startsWith("event ")) {

                    Task task = parser.parseTask(input);
                    tasks.add(task);
                    ui.showTaskAdded(task, tasks.size());

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
            }
        }
        ui.showFarewell();
    }
}
