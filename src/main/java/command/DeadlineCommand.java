package command;

import taskmodule.DeadlineTask;
import taskmodule.Task;

public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";
    public static final boolean shouldExit = false;
    private final String description;
    private final String by;

    public DeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    public Task addUserTask() {
        Task deadlineTask = new DeadlineTask(this.description, this.by);
        taskList.addTask(deadlineTask);
        return deadlineTask;
    }

    public String respond() {
        return "Got it. I've added this task:\n"
                + this.addUserTask() + "\n"
                + taskList;
    }

    @Override
    public void execute() {
        System.out.println(this.respond());
    }
}
