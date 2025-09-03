package parser;

import command.ByeCommand;
import command.Command;
import command.IncorrectCommand;
import command.MarkCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InputParserTest {
    @Test
    public void parseCommand_bye_success() {
        assertInstanceOf(ByeCommand.class, InputParser.parseCommand("bye"));
    }

    @Test
    public void parseCommand_mark_success() {
        Command command = InputParser.parseCommand("mark 2");
        assertInstanceOf(MarkCommand.class, command);

        MarkCommand markCommand = (MarkCommand) command;
        assertEquals(1, markCommand.taskIndex);
    }

    @Test
    public void parseCommand_mark_invalidFormat() {
        Command command = InputParser.parseCommand("mark");
        assertInstanceOf(IncorrectCommand.class, command);

        IncorrectCommand incorrectCommand = (IncorrectCommand) command;
        assertEquals("This is an invalid command format.\n" + MarkCommand.MESSAGE_USAGE,
                incorrectCommand.respond());
    }

    @Test
    public void parseCommand_mark_numberFormatException() {
        Command command = InputParser.parseCommand("mark hello");
        assertInstanceOf(IncorrectCommand.class, command);

        IncorrectCommand incorrectCommand = (IncorrectCommand) command;
        assertEquals("The task index provided is invalid.\n" + MarkCommand.MESSAGE_USAGE,
                incorrectCommand.respond());
    }
}

