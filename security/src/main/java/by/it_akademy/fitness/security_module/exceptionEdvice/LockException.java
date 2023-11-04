package by.it_akademy.fitness.security_module.exceptionEdvice;

public class LockException extends Exception {


    public LockException() {
    }
    public LockException(String message) {
        super(message);
    }

    public LockException(String message, Exception e) {
        super(message, e);
    }

    public LockException(Exception e) {
        super(e);
    }
}
