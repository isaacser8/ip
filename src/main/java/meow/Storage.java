package meow;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Scanner;

public class Storage {
    private final Path filePath;

    public Storage() {
        this.filePath = Paths.get("data", "meow.txt");
    }

    public void saveTasks(TaskList tasks) throws IOException {
        Files.createDirectories(filePath.getParent());

        FileWriter writer = new FileWriter(filePath.toFile());

        for (int i = 0; i < tasks.size(); i++) {
            writer.write(tasks.getTask(i).toFileString());
            writer.write(System.lineSeparator());
        }

        writer.close();
    }

    public TaskList loadTasks() throws IOException {
        TaskList taskList = new TaskList();
        if (!Files.exists(filePath)) {
            return taskList;
        }
        Scanner scanner = new Scanner(filePath.toFile());
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split("\\|");

            Task task = getTask(parts);
            taskList.add(task);
        }
        scanner.close();
        return taskList;
    }

    private static Task getTask(String[] parts) {
        String type = parts[0].trim();
        String status = parts[1].trim();
        String description = parts[2].trim();

        Task task;
        if (type.equals("T")) {
            task = new Todo(description);
        } else if (type.equals("D")) {
            String by = parts[3].trim();
            LocalDate byDate = LocalDate.parse(by);
            task = new Deadline(description, byDate);
        } else {
            String from = parts[3].trim();
            String to = parts[4].trim();
            task = new Event(description, from, to);
        }

        if (status.equals("1")) {
            task.markAsDone();
        }
        return task;
    }
}
