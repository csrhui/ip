import java.util.Scanner;


public class Greeter {
    static String botName = "Penny";

    public void sayHi() {
        System.out.println("Hi! I'm " + Greeter.botName + ".\nWhat can I do for you?\n");
    }

    public void sayBye() {
        System.out.println("Bye. Hope to see you again soon!\n");
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

            System.out.println(userInput);
        }
    }

    public static void main(String[] args) {
        Greeter greeter = new Greeter();
        greeter.startConversation();
    }
}
