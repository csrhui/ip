public class PennyException extends Exception {
    public PennyException() {
        super("Invalid user input. Please refer to the list of valid commands.\n" +
                Logic.createCommandList());
    }

    public PennyException(String errorMessage) {
        super(errorMessage);
    }
}
