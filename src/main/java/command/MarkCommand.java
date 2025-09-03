package command;

import taskmodule.Task;

public class MarkCommand extends Command {
    public static final String COMMAND_WORD = "mark";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Marks a task in the task list.\n"
            + "Parameters: TASK_INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";
    public final int taskIndex;

    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    public Task markTaskAsDone(int taskIndex) {
        if (taskIndex < 0 || taskIndex >= taskList.getTaskCount()) {
            throw new IndexOutOfBoundsException("taskmodule.Task index out of bounds.");
        }
        return taskList.getTask(taskIndex).markAsDone();
    }

    @Override
    public String respond() {
        return "Nice! I've marked this task as done:\n" + this.markTaskAsDone(this.taskIndex);
    }
}
