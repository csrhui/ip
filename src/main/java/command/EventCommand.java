package command;

import taskmodule.EventTask;
import taskmodule.Task;

public class EventCommand extends Command {
    public static final String COMMAND_WORD = "event";
    public static final boolean shouldExit = false;
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
