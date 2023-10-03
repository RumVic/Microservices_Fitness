package by.it_akademy.fitness.security.costom;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;


@Getter
public class JwtAuthenticationException extends AuthenticationException {
    private HttpStatus httpStatus;
    private String message;

    public JwtAuthenticationException(String msg, HttpStatus httpStatus) {
        super(msg);
        //this.message = msg;
        this.httpStatus = httpStatus;
    }
    public JwtAuthenticationException(String msg) {
        super(msg);
    }
}
