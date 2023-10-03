package by.it_akademy.fitness.exception;

public class SingleErrorResponse {

    private String logref;
    private String message;

    public String getLogref() {
        return logref;
    }
    public void setLogref(String logref) {
        this.logref = logref;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
