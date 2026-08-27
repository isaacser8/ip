package meow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskListTest {
    @Test
    void findTasks_matchingKeyword_returnsMatchingTasks() {
        TaskList tasks = new TaskList();
        tasks.add(new Todo("read book"));
        tasks.add(new Todo("buy groceries"));
        tasks.add(new Todo("return BOOK"));

        TaskList matches = tasks.findTasks("book");

        assertEquals(2, matches.size());
        assertEquals("read book", matches.getTask(0).getDescription());
        assertEquals("return BOOK", matches.getTask(1).getDescription());
    }
}

