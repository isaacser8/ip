package meow;

public class Event extends Task{

    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public String toString() {
        return "[E][" + super.getStatusIcon() + "] "
                + super.getDescription()
                + " (from: " + from
                + " to: " + to + ")";
    }

    @Override
    public String toFileString() {
        return "E | " + super.getStatusForFile() + " | " + super.getDescription()
                + " | " + from + " | " + to;
    }
}
