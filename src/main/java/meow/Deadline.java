package meow;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task with a due date.
 */
public class Deadline extends Task {
    protected LocalDate byDate;

    /**
     * Creates a deadline task with the specified description and due date.
     *
     * @param description the description of the task
     * @param byDate the due date of the task
     */
    public Deadline(String description, LocalDate byDate) {
        super(description);
        this.byDate = byDate;
    }

    public LocalDate getByDate() {
        return byDate;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "[D][" + super.getStatusIcon() + "] "
                + super.getDescription()
                + " (by: " + byDate.format(formatter) + ")";
    }

    @Override
    public String toFileString() {
        return "D | " + super.getStatusForFile() + " | " + super.getDescription() + " | " + byDate;
    }
}
