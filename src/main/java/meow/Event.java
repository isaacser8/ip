package meow;

/**
 * Represents an event task with a start and end time.
 */
public class Event extends Task {
    static final String STORAGE_TYPE = "E";

    protected String fromDate;
    protected String toDate;

    /**
     * Creates an event task with the specified description and time period.
     *
     * @param description the description of the event
     * @param fromDate the start date or time of the event
     * @param toDate the end date or time of the event
     */
    public Event(String description, String fromDate, String toDate) {
        super(description);
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    @Override
    public String toString() {
        return "[E][" + super.getStatusForDisplay() + "] "
                + super.getDescription()
                + " (from: " + fromDate
                + " to: " + toDate + ")";
    }

    @Override
    public String toFileString() {
        return STORAGE_TYPE + " | " + super.getStatusForFile() + " | " + super.getDescription()
                + " | " + fromDate + " | " + toDate;
    }
}
