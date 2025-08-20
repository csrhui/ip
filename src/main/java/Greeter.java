import java.util.Scanner;
import java.util.ArrayList;


public class Greeter {
    static String botName = "Penny";
    ArrayList<Task> userTasks = new ArrayList<>();

    public void sayHi() {
        System.out.println("Hi! I'm " + Greeter.botName + ".\nWhat can I do for you?\n");
    }

    public void sayBye() {
        System.out.println("Bye. Hope to see you again soon!\n");
    }

    private void listUserTasks() {
        int taskCount = this.userTasks.size();
        if (taskCount == 0) {
            System.out.println("You have no tasks.");
        } else {
            StringBuilder taskList = new StringBuilder("Your tasks:\n");
            for (int i = 0; i < taskCount; i++) {
                taskList.append(i + 1).append(". ").append(this.userTasks.get(i)).append("\n");
            }
            System.out.println(taskList);
        }
    }

    private void addUserTask(Task task) {
        userTasks.add(task);
        System.out.println("Added: " + task.getDescription());
    }

    private void markUserTask(int taskIndex) {
        if (taskIndex < 0 || taskIndex >= userTasks.size()) {
            System.out.println("Invalid task number.");
            return;
        }
        Task task = userTasks.get(taskIndex);
        task.markAsDone();
        System.out.println("Nice! I've marked this task as done:\n" + task);
    }

    private static int isMarkPattern(String input) {
        /*
                This method checks if the input matches the pattern "mark <number>".
                If it does, it returns the task number as an integer.
                If not, it returns -1.
        */
        if (input.matches("(?i)mark \\d+")) {
            String number = input.split(" ")[1];
            return Integer.parseInt(number);
        }
        return -1;
    }

    private void unmarkUserTask(int taskIndex) {
        if (taskIndex < 0 || taskIndex >= userTasks.size()) {
            System.out.println("Invalid task number.");
            return;
        }
        this.userTasks.get(taskIndex).unmarkAsDone();
        System.out.println("OK, I've marked this task as not done yet:\n" + this.userTasks.get(taskIndex));
    }

    private static int isUnmarkPattern(String input) {
        /*
                This method checks if the input matches the pattern "mark <number>".
                If it does, it returns the task number as an integer.
                If not, it returns -1.
        */
        if (input.matches("(?i)unmark \\d+")) {
            String number = input.split(" ")[1];
            return Integer.parseInt(number);
        }
        return -1;
    }

    public void startConversation() {
        Scanner scanner = new Scanner(System.in);

        this.sayHi();

        while(true) {
            String userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("bye")) {
                this.sayBye();
                break;
            }

            if (userInput.equalsIgnoreCase("list")) {
                this.listUserTasks();
                continue;
            }

            if (isMarkPattern(userInput) > 0) {
                int taskIndex = isMarkPattern(userInput) - 1;
                this.markUserTask(taskIndex);
                continue;
            }

            if (isUnmarkPattern(userInput) > 0) {
                int taskIndex = isUnmarkPattern(userInput) - 1;
                this.unmarkUserTask(taskIndex);
                continue;
            }

            this.addUserTask(new Task(userInput));
        }
    }

    public static void main(String[] args) {
        Greeter greeter = new Greeter();
        greeter.startConversation();
    }
}
