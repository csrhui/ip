package taskmodule;

import java.util.List;
import java.util.ArrayList;


public class TaskList {
    private List<Task> taskStore;
    private int taskCount;

    public TaskList() {
        this.taskStore = new ArrayList<>();
        this.taskCount = 0;
    }

    public int getTaskCount() {
        return this.taskCount;
    }

    public Task getTask(int index) {
        if (index < 0 || index >= this.taskCount) {
            throw new IndexOutOfBoundsException("Task.Task index out of bounds.");
        }
        return this.taskStore.get(index);
    }

    public void addTask(Task task) {
        this.taskStore.add(task);
        this.taskCount++;
    }

    public Task deleteTask(int index) {
        if (index < 0 || index >= this.taskCount) {
            throw new IndexOutOfBoundsException("Task.Task index out of bounds.");
        }
        this.taskCount--;
        return this.taskStore.remove(index);
    }

    public String toString() {
        if (this.taskCount == 0) {
            return "You have no tasks.";
        }
        return String.format("Now you have %d tasks in the list.", this.taskCount);
    }
}
