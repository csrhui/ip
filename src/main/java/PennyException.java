public class PennyException extends Exception {
    public PennyException() {
        super("Invalid user input. Please refer to the list of valid commands.\n");
    }

    public PennyException(String errorMessage) {
        super(errorMessage);
    }
}
