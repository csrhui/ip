package command;

public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    @Override
    public String respond() {
        return "Here are the available commands:\n\n"
                + "\n\n" + ToDoCommand.MESSAGE_USAGE
                + "\n\n" + DeadlineCommand.MESSAGE_USAGE
                + "\n\n" + EventCommand.MESSAGE_USAGE
                + "\n\n" + ListCommand.MESSAGE_USAGE
                + "\n\n" + MarkCommand.MESSAGE_USAGE
                + "\n\n" + UnmarkCommand.MESSAGE_USAGE
                + "\n\n" + DeleteCommand.MESSAGE_USAGE
                + "\n\n" + HelpCommand.MESSAGE_USAGE
                + "\n\n" + ByeCommand.MESSAGE_USAGE;
    }
}
