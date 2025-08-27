package taskmodule;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return this.description;
    }

    public Task markAsDone() {
        this.isDone = true;
        return this;
    }

    public Task unmarkAsDone() {
        this.isDone = false;
        return this;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    public String toDataString() {
        return String.format("%d | %s", this.isDone ? 1 : 0, this.description);
    }
}
