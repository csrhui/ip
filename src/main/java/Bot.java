import command.Command;
import parser.InputParser;

import java.io.IOException;
import java.util.Scanner;


public class Bot {
    Scanner scanner = new Scanner(System.in);
    InputParser inputParser = new InputParser();
    String logo =
            """
                     ____
                    |  _ \\ ___ _ __  _ __  _   _\s
                    | |_) / _ \\ '_ \\| '_ \\| | | |
                    |  __/  __/ | | | | | | |_| |
                    |_|   \\___|_| |_|_| |_|\\__, |
                                           |___/
                    """;

    public void startConversation() {
        do {
            String userInput = scanner.nextLine();
            Command command = inputParser.parseCommand(userInput);
            System.out.println(command.respond());
        } while (!Command.shouldExit);
    }

    public static void main(String[] args) {
        Bot bot = new Bot();
        System.out.println(String.format("Hello! I'm\n%s\nWhat can I do for you?\n", bot.logo));
        bot.startConversation();
        Storage storage = new Storage(Command.taskList);
        try {
            storage.saveTasks();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}