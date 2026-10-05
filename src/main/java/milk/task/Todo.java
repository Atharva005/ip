package milk.task;

/**
 * Represents a task without a deadline or "from" and "to" values.
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String getTaskIcon() {
        return "[T]";
    }

    @Override
    public String toString() {
        return getTaskIcon() + getStatusIcon() + " " + super.toString();
    }
}
