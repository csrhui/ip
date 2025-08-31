package command;

import taskmodule.Task;

public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "unmark";
    public static final boolean shouldExit = false;
    public final int taskIndex;

    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    public Task unmarkTaskAsDone() {
        if (taskIndex < 0 || taskIndex >= taskList.getTaskCount()) {
            throw new IndexOutOfBoundsException("taskmodule.Task index out of bounds.");
        }
        return taskList.getTask(taskIndex).markAsDone();
    }

    @Override
    public String respond() {
        return "OK, I've marked this task as not done yet:\n" + this.unmarkTaskAsDone();
    }
}
