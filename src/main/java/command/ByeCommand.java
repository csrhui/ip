package command;

public class ByeCommand extends Command {
    public static final String COMMAND_WORD = "bye";
    public static final String REPLY = "Bye. Hope to see you again soon!";
    public static final boolean shouldExit = true;

    @Override
    public String respond() {
        return REPLY;
    }
}
