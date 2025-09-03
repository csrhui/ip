package command;

import taskmodule.Task;

public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the task identified by the index number used in the displayed task list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";
    private final int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    public Task deleteUserTask() {
        if (this.taskIndex < 0 || this.taskIndex >= taskList.getTaskCount()) {
            throw new IndexOutOfBoundsException("taskmodule.Task index out of bounds.");
        }
        return taskList.deleteTask(this.taskIndex);
    }

    @Override
    public String respond() {
        return "Noted, I've removed this task:\n"
                + this.deleteUserTask() + "\n"
                + taskList;
    }
}
