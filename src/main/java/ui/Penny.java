package ui;

import java.io.IOException;
import java.util.Scanner;


import command.Command;
import parser.InputParser;
import storage.Storage;


public class Penny {
//    Scanner scanner = new Scanner(System.in);
//    InputParser inputParser = new InputParser();
//    String logo =
//            """
//                     ____
//                    |  _ \\ ___ _ __  _ __  _   _\s
//                    | |_) / _ \\ '_ \\| '_ \\| | | |
//                    |  __/  __/ | | | | | | |_| |
//                    |_|   \\___|_| |_|_| |_|\\__, |
//                                           |___/
//                    """;

    public String getResponse(String userInput) {
        do {
            Command command = InputParser.parseCommand(userInput);
            return command.respond();
        } while (!Command.shouldExit);
    }

//    public static void main(String[] args) {
//        Penny penny = new Penny();
//        System.out.printf("Hello! I'm\n%s\nWhat can I do for you?\n%n", bot.logo);
//        penny.getResponse();
//        Storage storage = new Storage(Command.taskList);
//        try {
//            storage.saveTasks();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
}