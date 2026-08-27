package meow;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T][" + super.getStatusIcon() + "] " + super.getDescription();
    }

    @Override
    public String toFileString() {
        return "T | " + super.getStatusForFile() + " | " + super.getDescription();
    }
}
