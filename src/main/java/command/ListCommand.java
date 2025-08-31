package command;

import taskmodule.Task;

public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";
    public static final boolean shouldExit = false;

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

    @Override
    public String respond() {
        return this.listTasks();
    }
}
