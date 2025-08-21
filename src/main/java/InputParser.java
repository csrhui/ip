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

    private static final Pattern INCOMPLETE_MARK_PATTERN =
            Pattern.compile("^\\s*mark\\s*$", Pattern.CASE_INSENSITIVE);
    private static final Pattern INCOMPLETE_UNMARK_PATTERN =
            Pattern.compile("^\\s*unmark\\s*$", Pattern.CASE_INSENSITIVE);
    private static final Pattern INCOMPLETE_TODO_PATTERN =
            Pattern.compile("^\\s*todo\\s*$", Pattern.CASE_INSENSITIVE);
    private static final Pattern INCOMPLETE_DEADLINE_PATTERN =
            Pattern.compile("^\\s*deadline\\s*(.*?)\\s*$", Pattern.CASE_INSENSITIVE);
    private static final Pattern INCOMPLETE_EVENT_PATTERN =
            Pattern.compile("^\\s*event\\s*(.*?)\\s*$", Pattern.CASE_INSENSITIVE);
    private static final Pattern INVALID_MARK_PATTERN =
            Pattern.compile("^\\s*mark\\s+(\\S+)\\s*$", Pattern.CASE_INSENSITIVE);
    private static final Pattern INVALID_UNMARK_PATTERN =
            Pattern.compile("^\\s*unmark\\s+(\\S+)\\s*$", Pattern.CASE_INSENSITIVE);

    public static Command parseInput(String input) throws PennyException {
        if (input == null || input.trim().isEmpty()) {
            throw new PennyException("Please enter a command.");
        }

        String trimmedInput = input.trim();

        // Handle bye and list commands
        if (trimmedInput.equalsIgnoreCase("bye")) {
            return new Command(Logic.CommandType.BYE, null);
        }

        if (trimmedInput.equalsIgnoreCase("list")) {
            return new Command(Logic.CommandType.LIST, null);
        }

        // Check for incomplete mark command
        if (INCOMPLETE_MARK_PATTERN.matcher(trimmedInput).matches()) {
            throw new PennyException("Please specify a task number to mark. Usage: mark <task number>");
        }

        // Check for invalid mark command (non-integer)
        Matcher invalidMarkMatcher = INVALID_MARK_PATTERN.matcher(trimmedInput);
        if (invalidMarkMatcher.matches()) {
            try {
                Integer.parseInt(invalidMarkMatcher.group(1));
            } catch (NumberFormatException e) {
                throw new PennyException("Task number must be an integer. Usage: mark <task number>");
            }
        }

        // Check for valid mark command
        Matcher markMatcher = MARK_PATTERN.matcher(trimmedInput);
        if (markMatcher.matches()) {
            String taskNumber = markMatcher.group(1);
            try {
                Integer.parseInt(taskNumber);
            } catch (NumberFormatException e) {
                throw new PennyException("Task number must be an integer. Usage: mark <task number>");
            }
            return new Command(Logic.CommandType.MARK,
                    Map.of("taskNumber", markMatcher.group(1)));
        }

        // Check for incomplete unmark command
        if (INCOMPLETE_UNMARK_PATTERN.matcher(trimmedInput).matches()) {
            throw new PennyException("Please specify a task number to unmark. Usage: unmark <task number>");
        }

        // Check for invalid unmark command (non-integer)
        Matcher invalidUnmarkMatcher = INVALID_UNMARK_PATTERN.matcher(trimmedInput);
        if (invalidUnmarkMatcher.matches()) {
            try {
                Integer.parseInt(invalidUnmarkMatcher.group(1));
            } catch (NumberFormatException e) {
                throw new PennyException("Task number must be an integer. Usage: unmark <task number>");
            }
        }

        // Check for valid unmark command
        Matcher unmarkMatcher = UNMARK_PATTERN.matcher(trimmedInput);
        if (unmarkMatcher.matches()) {
            String taskNumber = unmarkMatcher.group(1);
            try {
                Integer.parseInt(taskNumber);
            } catch (NumberFormatException e) {
                throw new PennyException("Task number must be an integer. Usage: unmark <task number>");
            }
            return new Command(Logic.CommandType.UNMARK,  // Fixed: was MARK, should be UNMARK
                    Map.of("taskNumber", unmarkMatcher.group(1)));
        }

        // Check for incomplete usertodo command
        if (INCOMPLETE_TODO_PATTERN.matcher(trimmedInput).matches()) {
            throw new PennyException("Please provide a description for the todo task. Usage: todo <description>");
        }

        // Check for valid usertodo command
        Matcher todoMatcher = TODO_PATTERN.matcher(trimmedInput);
        if (todoMatcher.matches()) {
            String description = todoMatcher.group(1).trim();
            if (description.isEmpty()) {
                throw new PennyException("Please provide a description for the todo task. Usage: todo <description>");
            }
            return new Command(Logic.CommandType.TODO,
                    Map.of("description", description));
        }

        // Check for incomplete deadline command
        Matcher incompleteDeadlineMatcher = INCOMPLETE_DEADLINE_PATTERN.matcher(trimmedInput);
        if (incompleteDeadlineMatcher.matches()) {
            String remainder = incompleteDeadlineMatcher.group(1);
            if (remainder == null || remainder.trim().isEmpty()) {
                throw new PennyException("Please provide a description and deadline. Usage: deadline <description> /by <date>");
            } else if (!remainder.contains("/by")) {
                throw new PennyException("Please specify a deadline using '/by'. Usage: deadline <description> /by <date>");
            } else {
                // Has /by but doesn't match the complete pattern
                String[] parts = remainder.split("/by", 2);
                if (parts.length < 2 || parts[0].trim().isEmpty()) {
                    throw new PennyException("Please provide a description for the deadline. Usage: deadline <description> /by <date>");
                } else if (parts[1].trim().isEmpty()) {
                    throw new PennyException("Please specify a deadline date after '/by'. Usage: deadline <description> /by <date>");
                }
            }
        }

        // Check for valid deadline command
        Matcher deadlineMatcher = DEADLINE_PATTERN.matcher(trimmedInput);
        if (deadlineMatcher.matches()) {
            String description = deadlineMatcher.group(1).trim();
            String by = deadlineMatcher.group(2).trim();

            if (description.isEmpty()) {
                throw new PennyException("Please provide a description for the deadline. Usage: deadline <description> /by <date>");
            }
            if (by.isEmpty()) {
                throw new PennyException("Please specify a deadline date after '/by'. Usage: deadline <description> /by <date>");
            }

            return new Command(Logic.CommandType.DEADLINE,
                    Map.of("description", description, "by", by));
        }

        // Check for incomplete event command
        Matcher incompleteEventMatcher = INCOMPLETE_EVENT_PATTERN.matcher(trimmedInput);
        if (incompleteEventMatcher.matches()) {
            String remainder = incompleteEventMatcher.group(1);
            if (remainder == null || remainder.trim().isEmpty()) {
                throw new PennyException("Please provide event details. Usage: event <description> /from <start> /to <end>");
            } else if (!remainder.contains("/from") || !remainder.contains("/to")) {
                throw new PennyException("Please specify event timing using '/from' and '/to'. Usage: event <description> /from <start> /to <end>");
            } else {
                // Has /from and /to but doesn't match the complete pattern
                String[] fromSplit = remainder.split("/from", 2);
                if (fromSplit.length < 2 || fromSplit[0].trim().isEmpty()) {
                    throw new PennyException("Please provide a description for the event. Usage: event <description> /from <start> /to <end>");
                }

                String[] toSplit = fromSplit[1].split("/to", 2);
                if (toSplit.length < 2 || toSplit[0].trim().isEmpty()) {
                    throw new PennyException("Please specify a start time after '/from'. Usage: event <description> /from <start> /to <end>");
                } else if (toSplit[1].trim().isEmpty()) {
                    throw new PennyException("Please specify an end time after '/to'. Usage: event <description> /from <start> /to <end>");
                }
            }
        }

        // Check for valid event command
        Matcher eventMatcher = EVENT_PATTERN.matcher(trimmedInput);
        if (eventMatcher.matches()) {
            String description = eventMatcher.group(1).trim();
            String from = eventMatcher.group(2).trim();
            String to = eventMatcher.group(3).trim();

            if (description.isEmpty()) {
                throw new PennyException("Please provide a description for the event. Usage: event <description> /from <start> /to <end>");
            }
            if (from.isEmpty()) {
                throw new PennyException("Please specify a start time after '/from'. Usage: event <description> /from <start> /to <end>");
            }
            if (to.isEmpty()) {
                throw new PennyException("Please specify an end time after '/to'. Usage: event <description> /from <start> /to <end>");
            }

            return new Command(Logic.CommandType.EVENT,
                    Map.of("description", description, "from", from, "to", to));
        }

        // If none of the patterns match, it's an unknown command
        throw new PennyException();
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