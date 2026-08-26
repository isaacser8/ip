package meow;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    protected LocalDate by;
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    public LocalDate getByDate() {
        return this.by;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "[D][" + super.getStatusIcon() + "] "
                + super.getDescription()
                + " (by: " + by.format(formatter) + ")";
    }

    @Override
    public String toFileString() {
        return "D | " + super.getStatusForFile() + " | " + super.getDescription() + " | " + by;
    }
}
