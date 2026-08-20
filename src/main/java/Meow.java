import java.util.Scanner;

public class Meow {
    public static void main(String[] args) {
        String banner =
                  "███   ███ ███████  █████  ██     ██\n"
                + "████ ████ ██      ██   ██ ██     ██\n"
                + "██ ███ ██ █████   ██   ██ ██  █  ██\n"
                + "██     ██ ██      ██   ██ ██ ███ ██\n"
                + "██     ██ ███████  █████   ███ ███\n";

        String greeting = "Meow! Welcome back. \n"
                + "Start yapping, I'm all ears!";

        String farewell = "Marvellous yap session. Let's catch up soon meow!";

        String line = "____________________________________________________________";

        System.out.println(line);
        System.out.println(banner);
        System.out.println(greeting);
        System.out.println(line);

        Scanner sc = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int totalTaskCount = 0;

        while (true) {
            String input = sc.nextLine();

            try {
                if (input.equals("bye")) {
                    break;

                } else if (input.equals("list")) {

                    System.out.println(line);
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < totalTaskCount; i++) {
                        System.out.println(i + 1 + ". " + tasks[i]);
                    }
                    System.out.println(line);
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
                    if (taskNumber > totalTaskCount) {
                        throw new MeowException("Meow! There is no task number " + taskNumber + ".");
                    }

                    int taskIndex = taskNumber - 1;
                    tasks[taskIndex].markAsDone();

                    System.out.println(line);
                    System.out.println("Meow! I've marked this task as done:");
                    System.out.println(tasks[taskIndex]);
                    System.out.println(line);

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
                    if (taskNumber > totalTaskCount) {
                        throw new MeowException("Meow! There is no task number " + taskNumber + ".");
                    }

                    int taskIndex = taskNumber - 1;
                    tasks[taskIndex].markAsNotDone();

                    System.out.println(line);
                    System.out.println("Meow! I've marked this task as not done yet");
                    System.out.println(tasks[taskIndex].toString());
                    System.out.println(line);

                } else if (input.equals("todo")) {
                    throw new MeowException("Meow! A todo needs a description.");
                } else if (input.startsWith("todo ")) {

                    String content = input.substring(5);
                    if (content.isBlank()) {
                        throw new MeowException("Meow! A todo needs a description.");
                    }

                    Task task = new Todo(content);
                    totalTaskCount = addTask(line, tasks, totalTaskCount, task);

                } else if (input.equals("deadline")) {
                    throw new MeowException("Meow! A deadline needs a description and a /by date.");
                } else if (input.startsWith("deadline ")) {

                    String content = input.substring(9);
                    if (content.isBlank()) {
                        throw new MeowException("Meow! A deadline needs a description and a /by date.");
                    }

                    int byIndex = content.indexOf("/by");
                    if (byIndex == -1) {
                        throw new MeowException("Meow! A deadline needs a /by date.");
                    }

                    String description = content.substring(0, byIndex).trim();
                    String by = content.substring(byIndex + 3).trim();
                    if (description.isBlank()) {
                        throw new MeowException("Meow! A deadline needs a description.");
                    }
                    if (by.isBlank()) {
                        throw new MeowException("Meow! A deadline needs a /by date.");
                    }

                    Task task = new Deadline(description, by);
                    totalTaskCount = addTask(line, tasks, totalTaskCount, task);

                } else if (input.equals("event")) {
                    throw new MeowException("Meow! An event needs a description, a /from date and a /to date.");
                } else if (input.startsWith("event ")) {

                    String content = input.substring(6).trim();
                    if (content.isBlank()) {
                        throw new MeowException("Meow! An event needs a description, a /from date and a /to date.");
                    }

                    int fromIndex = content.indexOf("/from");
                    int toIndex = content.indexOf("/to");
                    if (fromIndex == -1) {
                        throw new MeowException("Meow! An event needs a /from date.");
                    }
                    if (toIndex == -1) {
                        throw new MeowException("Meow! An event needs a /to date.");
                    }

                    String description = content.substring(0, fromIndex).trim();
                    String from = content.substring(fromIndex + 5, toIndex).trim();
                    String to = content.substring(toIndex + 3).trim();
                    if (description.isBlank()) {
                        throw new MeowException("Meow! An event needs a description.");
                    }
                    if (from.isBlank()) {
                        throw new MeowException("Meow! An event needs a /from date.");
                    }
                    if (to.isBlank()) {
                        throw new MeowException("Meow! An event needs a /to date.");
                    }

                    Task task = new Event(description, from, to);
                    totalTaskCount = addTask(line, tasks, totalTaskCount, task);
                } else {
                    throw new MeowException("Meow! I'm sorry, but I don't know what that means.");
                }
            } catch (MeowException e) {
                System.out.println(line);
                System.out.println(e.getMessage());
                System.out.println(line);
            }
        }
        System.out.println(line);
        System.out.println(farewell);
        System.out.println(line);
    }

    private static int addTask(String line, Task[] tasks, int taskCount, Task task) {
        tasks[taskCount] = task;
        taskCount++;

        System.out.println(line);
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        System.out.println(line);
        return taskCount;
    }
}
