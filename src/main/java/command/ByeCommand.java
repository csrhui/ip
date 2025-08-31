package command;

public class ByeCommand extends Command {
    public static final String COMMAND_WORD = "bye";
    public static final String BYE_REPLY = "Bye. Hope to see you again soon!";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Exits the program.\n"
            + "Example: " + COMMAND_WORD;

    public ByeCommand() {
        shouldExit = true;
    }

    @Override
    public String respond() {
        return BYE_REPLY;
    }
}
