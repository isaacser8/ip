public class Ui {
    String line = "____________________________________________________________";
    String banner =   "███   ███ ███████  █████  ██     ██\n"
                    + "████ ████ ██      ██   ██ ██     ██\n"
                    + "██ ███ ██ █████   ██   ██ ██  █  ██\n"
                    + "██     ██ ██      ██   ██ ██ ███ ██\n"
                    + "██     ██ ███████  █████   ███ ███\n";

    String greeting = "Meow! Welcome back. \n"
            + "Start yapping, I'm all ears!";

    public void showGreeting() {
        System.out.println(line);
        System.out.println(banner);
        System.out.println(greeting);
        System.out.println(line);
    }

    public void showFarewell() {
        System.out.println(line);
        System.out.println("Marvellous yap session. Let's catch up soon meow!");
        System.out.println(line);
    }

    public void showError(String e) {
        System.out.println(line);
        System.out.println(e);
        System.out.println(line);
    }

    public void showTaskList(Task[] task, int totalTaskCount) {
        System.out.println(line);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < totalTaskCount; i++) {
            System.out.println(i + 1 + ". " + task[i]);
        }
        System.out.println(line);
    }

    public void showTaskMarked(Task task) {
        System.out.println(line);
        System.out.println("Meow! I've marked this task as done:");
        System.out.println(task);
        System.out.println(line);
    }

    public void showTaskUnmarked(Task task) {
        System.out.println(line);
        System.out.println("Meow! I've marked this task as not done yet :");
        System.out.println(task);
        System.out.println(line);
    }

    public void showTaskAdded(Task task, int taskCount) {
        System.out.println(line);
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        System.out.println(line);
    }
}
