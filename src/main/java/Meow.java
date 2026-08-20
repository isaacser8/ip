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

                } else if (input.startsWith("mark ")) {

                    String[] parts = input.split(" ");
                    int taskNumber = Integer.parseInt(parts[1]);
                    int taskIndex = taskNumber - 1;
                    tasks[taskIndex].markAsDone();

                    System.out.println(line);
                    System.out.println("Meow! I've marked this task as done");
                    System.out.println(tasks[taskIndex].toString());
                    System.out.println(line);

                } else if (input.startsWith("unmark ")) {

                    String[] parts = input.split(" ");
                    int taskNumber = Integer.parseInt(parts[1]);
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

                } else if (input.startsWith("deadline ")) {

                    String content = input.substring(9);
                    String[] parts = content.split(" /by ", 2);

                    String description = parts[0];
                    String by = parts[1];

                    Task task = new Deadline(description, by);
                    totalTaskCount = addTask(line, tasks, totalTaskCount, task);

                } else if (input.startsWith("event ")) {

                    String content = input.substring(6);

                    String[] fromParts = content.split(" /from ", 2);
                    String description = fromParts[0];

                    String[] toParts = fromParts[1].split(" /to ", 2);
                    String from = toParts[0];
                    String to = toParts[1];

                    Task task = new Event(description, from, to);
                    totalTaskCount = addTask(line, tasks, totalTaskCount, task);
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
