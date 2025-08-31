package command;

import taskmodule.TaskList;

public class Command {
    public static final TaskList taskList = new TaskList();

    public void execute() {
        // to be overridden by subclasses
    }
}
