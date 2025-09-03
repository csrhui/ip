package command;

import taskmodule.Task;

public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all tasks in the task list."
            + "Example: " + COMMAND_WORD;

    public String listTasks() {
        int taskCount = taskList.getTaskCount();

        if (taskCount == 0) {
            return "You have no tasks.";
        }

        StringBuilder taskListString = new StringBuilder("Your tasks:\n");
        for (int i = 0; i < taskCount; i++) {
            Task task = taskList.getTask(i);
            taskListString.append(i + 1).append(". ").append(task).append("\n");
        }

        return taskListString.toString();
    }

    @Override
    public String respond() {
        return this.listTasks();
    }
}
