package command;

import taskmodule.Task;

public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Searches for tasks that contain the keyword in their descriptions.\n"
            + "Parameters: KEYWORD\n"
            + "Example: " + COMMAND_WORD + " book";

    public final String keyword;

    public FindCommand(String keyword) {
        assert keyword != null : "Keyword should not be null";

        this.keyword = keyword;
    }

    public String listMatchingTasks() {
        int taskCount = taskList.getTaskCount();

        if (taskCount == 0) {
            return "You have no tasks.";
        }

        StringBuilder matchingTasks = new StringBuilder("Here are the matching tasks in your list:\n");
        for (int i = 0; i < taskCount; i++) {
            Task task = taskList.getTask(i);
            if (task.getDescription().contains(this.keyword)) {
                matchingTasks.append(i + 1).append(". ").append(task).append("\n");
            }
        }

        return matchingTasks.toString();
    }

    @Override
    public String respond() {
        return this.listMatchingTasks();
    }
}
