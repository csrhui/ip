package command;

import taskmodule.ToDoTask;
import taskmodule.Task;

public class ToDoCommand extends Command{
    public static final String COMMAND_WORD = "todo";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a todo task to the task list.\n"
            + "Parameters: DESCRIPTION\n"
            + "Example: " + COMMAND_WORD + " read book";
    private final String description;

    public ToDoCommand(String description) {
        this.description = description;
    }

    public Task addUserTask() {
        Task todoTask = new ToDoTask(this.description);
        taskList.addTask(todoTask);
        return todoTask;
    }

    @Override
    public String respond() {
        return "Got it. I've added this task:\n"
                + this.addUserTask() + "\n"
                + taskList;
    }
}
