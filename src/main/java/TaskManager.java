public class TaskManager {
    // add task - deadline, td, event
    // mark task as done
    // unmark task as done
    // list tasks
    protected TaskList taskList;

    public TaskManager() {
        this.taskList = new TaskList();
    }

    public void addUserTask(Task task) {
        this.taskList.addTask(task);
    }

    public Task deleteUserTask(int taskIndex) {
        if (taskIndex < 0 || taskIndex >= taskList.getTaskCount()) {
            throw new IndexOutOfBoundsException("Task index out of bounds.");
        }
        return taskList.deleteTask(taskIndex);
    }

    public Task markTaskAsDone(int taskIndex) {
        if (taskIndex < 0 || taskIndex >= taskList.getTaskCount()) {
            throw new IndexOutOfBoundsException("Task index out of bounds.");
        }
        return taskList.getTask(taskIndex).markAsDone();
    }

    public Task unmarkTaskAsDone(int taskIndex) {
        if (taskIndex < 0 || taskIndex >= taskList.getTaskCount()) {
            throw new IndexOutOfBoundsException("Task index out of bounds.");
        }
        return taskList.getTask(taskIndex).unmarkAsDone();
    }

    public String listTasks() {
        int taskCount = taskList.getTaskCount();

        if (taskCount == 0) {
            return "You have no tasks.";
        }

        StringBuilder taskListString = new StringBuilder("Your tasks:\n");
        for (int i = 0; i < taskCount; i++) {
            Task task = taskList.getTask(i);
            taskListString.append(i + 1).append(". ").append(task.toString()).append("\n");
        }

        return taskListString.toString();
    }
}
