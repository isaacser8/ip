package meow;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate byDate;
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
