package course;


public enum ErrorMessage {
    INVALID_REQUEST("title, description, and instructor ID"),
    RESOURCE_NOT_FOUND("course");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
