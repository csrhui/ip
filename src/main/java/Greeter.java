public class Greeter {
    static String botName = "Penny";

    public void sayHi() {
        System.out.println("Hi! I'm " + Greeter.botName + ".\nWhat can I do for you?\n");
    }

    public void sayBye() {
        System.out.println("Bye. Hope to see you again soon!\n");
    }

    public static void main(String[] args) {
        Greeter greeter = new Greeter();
        greeter.sayHi();
        greeter.sayBye();
    }
}
