package user;


public enum ErrorMessage {
    INVALID_REQUEST("username, password, email, and role"),
    RESOURCE_NOT_FOUND("user");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
