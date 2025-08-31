package command;

import taskmodule.EventTask;
import taskmodule.Task;

public class EventCommand extends Command {
    public static final String COMMAND_WORD = "event";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an event task to the task list.\n"
            + "Parameters: DESCRIPTION /from START_TIME /to END_TIME\n"
            + "Example: " + COMMAND_WORD + " project meeting /from 2pm /to 4pm";
    private final String description;
    private final String from;
    private final String to;

    public EventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    public Task addUserTask() {
        Task eventTask = new EventTask(this.description, this.from, this.to);
        taskList.addTask(eventTask);
        return eventTask;
    }

    @Override
    public String respond() {
        return "Got it. I've added this task:\n"
                + this.addUserTask() + "\n"
                + taskList;
    }
}
