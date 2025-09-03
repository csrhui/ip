package command;

import java.time.LocalDate;

import taskmodule.DeadlineTask;
import taskmodule.Task;

public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds a deadline task to the task list.\n"
            + "Parameters: DESCRIPTION /by DATE_TIME\n"
            + "Example: " + COMMAND_WORD + " return book /by 2024-09-15 18:00";
    private final String description;
    private final LocalDate by;

    public DeadlineCommand(String description, LocalDate by) {
        this.description = description;
        this.by = by;
    }

    public Task addUserTask() {
        Task deadlineTask = new DeadlineTask(this.description, this.by);
        taskList.addTask(deadlineTask);
        return deadlineTask;
    }

    @Override
    public String respond() {
        return "Got it. I've added this task:\n"
                + this.addUserTask() + "\n"
                + taskList;
    }
}
