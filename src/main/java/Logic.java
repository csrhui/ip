import java.util.Map;
import java.lang.StringBuilder;

public class Logic {
    public static TaskManager taskManager;
    public String botName;
    public enum CommandType {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, UNKNOWN
    }
    private static final Map<CommandType, String> COMMAND_FORMATS = Map.of(
            CommandType.TODO, "todo <description>",
            CommandType.DEADLINE, "deadline <description> /by <date>",
            CommandType.EVENT, "event <description> /from <start time> /to <end time>",
            CommandType.MARK, "mark <task number>",
            CommandType.UNMARK, "unmark <task number>",
            CommandType.LIST, "list",
            CommandType.BYE, "bye"
    );

    public Logic(String botName) {
        taskManager = new TaskManager();
        this.botName = botName;
    }

    public String onStart() {
        return String.format("Hi! I'm %s.\nWhat can I do for you?\n", this.botName);
    }

    public Response botResponse(String userInput) {
        if (userInput == null || userInput.equals("")) {
            String messageEmpty = "Please enter a valid command.";
            return new Response(messageEmpty, false);
        }

        InputParser parser = new InputParser();
        InputParser.Command command = parser.parseInput(userInput);

        switch (command.getCommandType()) {
            case BYE:
                String messageBye = "Bye. Hope to see you again soon!\n";
                return new Response(messageBye, true);

            case LIST:
                String messageList = taskManager.listTasks();
                return new Response(messageList, false);

            case MARK:
                int taskIndex_mark = Integer.parseInt(command.getArguments().get("taskNumber")) - 1;
                Task task_mark = taskManager.markTaskAsDone(taskIndex_mark);
                String messageMark = "Nice! I've marked this task as done:\n" + task_mark;
                return new Response(messageMark, false);

            case UNMARK:
                int taskIndex_unmark = Integer.parseInt(command.getArguments().get("taskNumber")) - 1;
                Task task_unmark = taskManager.unmarkTaskAsDone(taskIndex_unmark);
                String messageUnmark = "OK, I've marked this task as not done yet:\n" + task_unmark;
                return new Response(messageUnmark, false);

            case TODO:
                String description_todo = command.getArguments().get("description");
                if (description_todo == null || description_todo.isEmpty()) {
                    String messageInvalidTodo = "The input format should be: todo <description>";
                    return new Response(messageInvalidTodo, false);
                }
                Task task_todo = new ToDoTask(description_todo);
                taskManager.addUserTask(task_todo);
                String messageTodo = "Got it. I've added this task:\n"
                        + task_todo + "\n"
                        + taskManager.taskList.toString();
                return new Response(messageTodo, false);

            case DEADLINE:
                Map<String, String> deadlineArgs = command.getArguments();
                String description_deadline = deadlineArgs.get("description");
                String by = deadlineArgs.get("by");
                if (description_deadline == null || description_deadline.isEmpty() ||
                        by == null || by.isEmpty()) {
                    String messageInvalidDeadline = "The input format should be: deadline <description> /by <date>";
                    return new Response(messageInvalidDeadline, false);
                }
                Task task_deadline = new DeadlineTask(description_deadline, by);
                taskManager.addUserTask(task_deadline);
                String messageDeadline = "Got it. I've added this task:\n"
                        + task_deadline + "\n"
                        + taskManager.taskList.toString();
                return new Response(messageDeadline, false);

            case EVENT:
                Map<String, String> eventArgs = command.getArguments();
                String description_event = eventArgs.get("description");
                String from = eventArgs.get("from");
                String to = eventArgs.get("to");
                if (description_event == null || description_event.isEmpty() ||
                        from == null || from.isEmpty() ||
                        to == null || to.isEmpty()) {
                    String messageInvalidEvent =
                            "The input format should be: event <description> /from <start time> /to <end time>";
                    return new Response(messageInvalidEvent, false);
                }
                Task task_event = new EventTask(description_event, from, to);
                taskManager.addUserTask(task_event);
                String messageEvent = "Got it. I've added this task:\n"
                        + task_event + "\n"
                        + taskManager.taskList.toString();
                return new Response(messageEvent, false);

            default:
                StringBuilder messageDefault = new StringBuilder(
                        "I'm sorry, but I don't understand that command. Please use one of the following commands:\n");
                COMMAND_FORMATS.forEach((commandType, commandFormat) -> {
                    messageDefault.append(commandFormat + "\n");
                });
                return new Response(messageDefault.toString(),false);
        }
    }
}
