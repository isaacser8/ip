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
        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                break;
            }
            System.out.println(line);
            System.out.println(input);
            System.out.println(line);
        }

        System.out.println(line);
        System.out.println(farewell);
        System.out.println(line);


    }
}
