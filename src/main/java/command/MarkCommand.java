package command;

import taskmodule.Task;

public class MarkCommand extends Command {
    public static final String COMMAND_WORD = "mark";
    public static final boolean shouldExit = false;
    private final int taskIndex;

    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    public Task markTaskAsDone(int taskIndex) {
        if (taskIndex < 0 || taskIndex >= taskList.getTaskCount()) {
            throw new IndexOutOfBoundsException("taskmodule.Task index out of bounds.");
        }
        return taskList.getTask(taskIndex).markAsDone();
    }

    public String respond() {
        return "Nice! I've marked this task as done:\n  [X] " + this.markTaskAsDone(this.taskIndex);
    }

    @Override
    public void execute() {
        System.out.println(this.respond());
    }
}
