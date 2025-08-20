import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class InputParser {
    private static final Pattern MARK_PATTERN =
            Pattern.compile("^\\s*mark\\s+(\\d+)\\s*$", Pattern.CASE_INSENSITIVE);
    private static final Pattern UNMARK_PATTERN =
            Pattern.compile("^\\s*unmark\\s+(\\d+)\\s*$", Pattern.CASE_INSENSITIVE);
    private static final Pattern TODO_PATTERN =
            Pattern.compile("^\\s*todo\\s+(.+)\\s*$", Pattern.CASE_INSENSITIVE);
    private static final Pattern DEADLINE_PATTERN =
            Pattern.compile("^\\s*deadline\\s+(.+)\\s+/by\\s+(.+)\\s*$", Pattern.CASE_INSENSITIVE);
    private static final Pattern EVENT_PATTERN =
            Pattern.compile("^\\s*event\\s+(.+)\\s+/from\\s+(.+)\\s+/to\\s+(.+)\\s*$", Pattern.CASE_INSENSITIVE);

    public static Command parseInput(String input) {
        if (input == null || input.trim().isEmpty()) {
            return new Command(Logic.CommandType.UNKNOWN, null);
        }

        String trimmedInput = input.trim();
        if (trimmedInput.equalsIgnoreCase("bye")) {
            return new Command(Logic.CommandType.BYE, null);
        }

        if (trimmedInput.equalsIgnoreCase("list")) {
            return new Command(Logic.CommandType.LIST, null);
        }

        Matcher markMatcher = MARK_PATTERN.matcher(trimmedInput);
        if (markMatcher.matches()) {
            return new Command(Logic.CommandType.MARK,
                    Map.of("taskNumber", markMatcher.group(1)));
        }

        Matcher unmarkMatcher = UNMARK_PATTERN.matcher(trimmedInput);
        if (unmarkMatcher.matches()) {
            return new Command(Logic.CommandType.UNMARK,
                    Map.of("taskNumber", unmarkMatcher.group(1)));
        }

        Matcher todoMatcher = TODO_PATTERN.matcher(trimmedInput);
        if (todoMatcher.matches()) {
            return new Command(Logic.CommandType.TODO,
                    Map.of("description", todoMatcher.group(1)));
        }

        Matcher deadlineMatcher = DEADLINE_PATTERN.matcher(trimmedInput);
        if (deadlineMatcher.matches()) {
            return new Command(Logic.CommandType.DEADLINE,
                    Map.of("description", deadlineMatcher.group(1),
                            "by", deadlineMatcher.group(2)));
        }

        Matcher eventMatcher = EVENT_PATTERN.matcher(trimmedInput);
        if (eventMatcher.matches()) {
            return new Command(Logic.CommandType.EVENT,
                    Map.of("description", eventMatcher.group(1),
                            "from", eventMatcher.group(2),
                            "to", eventMatcher.group(3)));
        }

        // if none of the patterns match, return UNKNOWN command
        return new Command(Logic.CommandType.UNKNOWN, null);
    }

    public static class Command {
        private final Map<String, String> arguments;
        private final Logic.CommandType commandType;

        public Command(Logic.CommandType commandType, Map<String, String> arguments) {
            this.commandType = commandType;
            this.arguments = arguments;
        }

        public Logic.CommandType getCommandType() {
            return commandType;
        }

        public Map<String, String> getArguments() {
            return arguments;
        }
    }
}
