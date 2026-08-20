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
        int taskCount = 0;

        while (true) {
            String input = sc.nextLine();

            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                System.out.println(line);
                for (int i = 0; i < taskCount; i++) {
                    System.out.println(i + 1 + ". " + tasks[i].toString());
                }
                System.out.println(line);
            } else if (input.startsWith("mark ")) {
                String[] parts = input.split(" ");
                int taskNumber = Integer.parseInt(parts[1]);
                int taskIndex = taskNumber - 1;
                tasks[taskIndex].markAsDone();
                System.out.println("Meow! I've mark this task as done");
                System.out.println(tasks[taskIndex].toString());
            } else if (input.startsWith("unmark ")) {
                String[] parts = input.split(" ");
                int taskNumber = Integer.parseInt(parts[1]);
                int taskIndex = taskNumber - 1;
                tasks[taskIndex].markAsNotDone();
                System.out.println("Meow! I've mark this task as not done yet");
                System.out.println(tasks[taskIndex].toString());
            } else {
                Task task = new Task(input);
                tasks[taskCount] = task;
                taskCount++;
                System.out.println(line);
                System.out.println("added: " + input);
                System.out.println(line);
            }
        }

        System.out.println(line);
        System.out.println(farewell);
        System.out.println(line);


    }
}
