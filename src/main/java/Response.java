public class Response {
    private String responseMessage;
    private boolean shouldExit;

    public Response(String responseMessage, boolean shouldExit) {
        this.responseMessage = responseMessage;
        this.shouldExit = shouldExit;
    }

    public String getResponseMessage() {
        return this.responseMessage;
    }

    public boolean shouldExit() {
        return this.shouldExit;
    }
}
