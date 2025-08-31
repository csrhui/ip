package command;

import taskmodule.TaskList;

public class Command {
    public static final TaskList taskList = new TaskList();
    public static boolean shouldExit = false;

    public String respond() {
        return "";
    }
}
