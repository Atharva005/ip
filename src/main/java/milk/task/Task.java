package milk.task;

/**
 * Parent class inherited by the various task classes.
 */
public class Task {
    protected String description;
    protected boolean marked;

    public Task(String description) {
        this.description = description;
        this.marked = false;
    }

    /**
     * Gets the status icon of the ask (whether it is marked or unamrked).
     * @return Status icon.
     */
    public String getStatusIcon() {
        return (marked ? "[X]" : "[ ]");
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isMarked() {
        return marked;
    }

    public void setMarked(boolean marked) {
        this.marked = marked;
    }

    /**
     * A placeholder method that is overridden by Task's children.
     * @return Placeholder icon.
     */
    public String getTaskIcon() {
        return "[ ]";
    }

    @Override
    public String toString() {
        return description;
    }
}
