package parser;

import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import command.Command;
import command.ByeCommand;
import command.DeadlineCommand;
import command.DeleteCommand;
import command.EventCommand;
import command.ListCommand;
import command.MarkCommand;
import command.ToDoCommand;
import command.UnmarkCommand;


public class InputParser {
    public static final Pattern TASK_INDEX_ARGS_FORMAT = Pattern.compile("(?<targetIndex>.+)");
    public static final Pattern TODO_ARGS_FORMAT =
            Pattern.compile("(?<description>.+)"); // everything after "rtodo" is description
    public static final Pattern DEADLINE_ARGS_FORMAT =
            Pattern.compile("(?<description>[^/]+) /by (?<time>.+)");
    public static final Pattern EVENT_ARGS_FORMAT =
            Pattern.compile("(?<description>[^/]+) /from (?<startTime>.+) /to (?<endTime>.+)");

    public static final Pattern BASIC_COMMAND_FORMAT =
            Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    public Command parseCommand(String userInput) {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            // todo: direct to IncorrectMessageCommand
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        switch (commandWord) {
            case ByeCommand.COMMAND_WORD:
                return new ByeCommand();

            case ListCommand.COMMAND_WORD:
                return new ListCommand();

            case MarkCommand.COMMAND_WORD:
                return prepareMark(arguments);

            case UnmarkCommand.COMMAND_WORD:
                return prepareUnmark(arguments);

            case DeleteCommand.COMMAND_WORD:
                return prepareDelete(arguments);

            case ToDoCommand.COMMAND_WORD:
                return prepareToDo(arguments);

            case DeadlineCommand.COMMAND_WORD:
                return prepareDeadline(arguments);

            case EventCommand.COMMAND_WORD:
                return prepareEvent(arguments);
        }
    }

    private Command prepareMark(String args) {
        try {
            final int targetIndex = parseArgsAsDisplayedIndex(args);
            return new MarkCommand(targetIndex);
        } catch (ParseException pe) {
            // todo: handle exception
        } catch (NumberFormatException nfe) {
            // todo: handle exception
        }
    }

    private Command prepareUnmark(String args) {
        try {
            final int targetIndex = parseArgsAsDisplayedIndex(args);
            return new UnmarkCommand(targetIndex);
        } catch (ParseException pe) {
            // todo: handle exception
        } catch (NumberFormatException nfe) {
            // todo: handle exception
        }
    }

    private Command prepareDelete(String args) {
        try {
            final int targetIndex = parseArgsAsDisplayedIndex(args);
            return new DeleteCommand(targetIndex);
        } catch (ParseException pe) {
            // todo: handle exception
        } catch (NumberFormatException nfe) {
            // todo: handle exception
        }
    }

    private Command prepareToDo(String args) {
        final Matcher matcher = TODO_ARGS_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            // todo: direct to IncorrectMessageCommand
        }
        return new ToDoCommand(matcher.group("description"));
    }

    private Command prepareDeadline(String args) {
        final Matcher matcher = DEADLINE_ARGS_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            // todo: direct to IncorrectMessageCommand
        }
        return new DeadlineCommand(matcher.group("description"), matcher.group("time"));
    }

    private Command prepareEvent(String args) {
        final Matcher matcher = EVENT_ARGS_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            // todo: direct to IncorrectMessageCommand
        }
        return new EventCommand(matcher.group("description"), matcher.group("startTime"),
                matcher.group("endTime"));
    }

    private int parseArgsAsDisplayedIndex(String args) throws ParseException, NumberFormatException {
        final Matcher matcher = TASK_INDEX_ARGS_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            throw new ParseException("Could not find index number to parse.", 0);
        }
        return Integer.parseInt(matcher.group("targetIndex"));
    }
}
