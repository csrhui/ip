import java.util.Scanner;
import java.util.ArrayList;


public class Greeter {
    static String botName = "Penny";
    ArrayList<String> userTasks = new ArrayList<>();

    public void sayHi() {
        System.out.println("Hi! I'm " + Greeter.botName + ".\nWhat can I do for you?\n");
    }

    public void sayBye() {
        System.out.println("Bye. Hope to see you again soon!\n");
    }

    private void listUserTasks() {
        int taskCount = userTasks.size();
        if (taskCount == 0) {
            System.out.println("You have no tasks.");
        } else {
            StringBuilder taskList = new StringBuilder("Your tasks:\n");
            for (int i = 0; i < taskCount; i++) {
                taskList.append(i + 1).append(". ").append(userTasks.get(i)).append("\n");
            }
            System.out.println(taskList.toString());
        }
    }

    private void addUserTask(String task) {
        userTasks.add(task);
        System.out.println("Added: " + task);
    }

    public void startConversation() {
        Scanner scanner = new Scanner(System.in);

        Greeter greeter = new Greeter();
        greeter.sayHi();

        while(true) {
            String userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("bye")) {
                greeter.sayBye();
                break;
            }

            if (userInput.equalsIgnoreCase("list")) {
                greeter.listUserTasks();
                continue;
            }

            greeter.addUserTask(userInput);
        }
    }

    public static void main(String[] args) {
        Greeter greeter = new Greeter();
        greeter.startConversation();
    }
}
