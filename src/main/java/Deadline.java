public class Deadline extends Task{
    protected String by;
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D][" + super.getStatusIcon() + "] "
                + super.getDescription()
                + " (by: " + by + ")";
    }

    @Override
    public String toFileString() {
        return "D | " + super.getStatusForFile() + " | " + super.getDescription() + " | " + by;
    }
}
