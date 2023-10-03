package by.it_akademy.fitness.security.costom;

import org.springframework.http.HttpStatus;

public class SignatureException extends java.security.SignatureException {

    private HttpStatus httpStatus;

    public SignatureException(String msg, HttpStatus httpStatus) {
        super(msg);
        this.httpStatus = httpStatus;
    }
    public SignatureException(String msg) {
        super(msg);
    }
}
