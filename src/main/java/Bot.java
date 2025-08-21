import java.util.Scanner;


public class Bot {
    public final String botName = "Penny";
    private final Logic logic;

    public Bot() {
        this.logic = new Logic(this.botName);
    }

    public void startConversation() {
        Scanner scanner = new Scanner(System.in);

        System.out.println(logic.onStart());

        while(true) {
            String userInput = scanner.nextLine();

            Response response = logic.botResponse(userInput);
            System.out.println(response.getResponseMessage() + "\n");

            if (response.shouldExit()) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        Bot bot = new Bot();
        bot.startConversation();
    }
}