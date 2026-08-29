public class Task {
    private String description;
    private boolean marked;

    public Task(String description) {
        this.description = description;
        this.marked = false;
    }

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
}
