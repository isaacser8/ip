package meow;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class StorageTest {
    @Test
    void saveAndLoadTasks_validTasks_preservesTasks() throws IOException {
        Path tempFile = Files.createTempFile("meow-test", ".txt");
        Storage storage = new Storage(tempFile);
        TaskList tasks = new TaskList();

        // Set up tasks with different types and statuses
        tasks.add(new Todo("read book"));
        tasks.add(new Deadline("submit assignment", LocalDate.of(2026, 8, 28)));
        tasks.getTask(0).markAsDone();

        // Save the tasks and load them back from storage
        storage.saveTasks(tasks);
        TaskList loadedTasks = storage.loadTasks();

        // Verify the task count and task types are preserved
        assertEquals(2, loadedTasks.size());
        assertInstanceOf(Todo.class, loadedTasks.getTask(0));
        assertInstanceOf(Deadline.class, loadedTasks.getTask(1));

        // Verify task details and completion status are preserved
        assertEquals("read book", loadedTasks.getTask(0).getDescription());
        assertEquals("submit assignment", loadedTasks.getTask(1).getDescription());
        assertEquals("X", loadedTasks.getTask(0).getStatusIcon());

        // Verify the deadline date is preserved as a LocalDate
        Deadline deadline = (Deadline) loadedTasks.getTask(1);
        assertEquals(
                LocalDate.of(2026, 8, 28),
                deadline.getByDate()
        );

        Files.deleteIfExists(tempFile);
    }
}
