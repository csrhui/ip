package command;

import taskmodule.Task;

public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    public static final boolean shouldExit = false;
    private int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    public Task deleteUserTask() {
        if (this.taskIndex < 0 || this.taskIndex >= taskList.getTaskCount()) {
            throw new IndexOutOfBoundsException("taskmodule.Task index out of bounds.");
        }
        return taskList.deleteTask(this.taskIndex);
    }

    public String respond() {
        return "Noted, I've removed this task:\n"
                + this.deleteUserTask() + "\n"
                + taskList;
    }

    @Override
    public void execute() {
        System.out.println(this.respond());
    }
}
