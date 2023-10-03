package by.it_akademy.fitness.exception;


import by.it_akademy.fitness.odto.OutputResponseError;
import by.it_akademy.fitness.security.costom.JwtAuthenticationException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.naming.AuthenticationException;
import javax.persistence.OptimisticLockException;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class ExceptionAdvice extends ResponseEntityExceptionHandler {


    @ExceptionHandler(OptimisticLockException.class)
    public ResponseEntity<SingleErrorResponse> processingException (OptimisticLockException e) {
        SingleErrorResponse response = new SingleErrorResponse();
        response.setLogref("error");
        response.setMessage(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(LockException.class)
    public ResponseEntity<String> processingException (LockException l){
        return new ResponseEntity<>(l.getMessage(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<SingleErrorResponse> processingException (IllegalStateException i){
        SingleErrorResponse response = new SingleErrorResponse();
        response.setLogref("error");
        response.setMessage(i.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<SingleErrorResponse> processingException (NoSuchElementException n){
        SingleErrorResponse response = new SingleErrorResponse();
        response.setLogref("error");
        response.setMessage(n.getMessage());
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ServletException.class)
    public ResponseEntity<SingleErrorResponse> jwtException (ServletException s){
        SingleErrorResponse response = new SingleErrorResponse();
        response.setLogref("error");
        response.setMessage(s.getMessage());
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<SingleErrorResponse> security (AuthenticationException s){
        SingleErrorResponse response = new SingleErrorResponse();
        response.setLogref("error");
        response.setMessage(s.getMessage());
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<SingleErrorResponse> handleException(IOException e) {
        SingleErrorResponse response = new SingleErrorResponse();
        response.setLogref("error");
        response.setMessage("The server couldn't process request . Please contact to admin ");
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(JwtAuthenticationException.class)
    public ResponseEntity <SingleErrorResponse> jwtAuthExpn (JwtAuthenticationException e) {
        SingleErrorResponse response = new SingleErrorResponse();
        response.setLogref("error");
        response.setMessage("The server couldn't process request . Please contact to admin");
        return new ResponseEntity<>(response,HttpStatus.UNAUTHORIZED);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception e, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        SingleErrorResponse response = new SingleErrorResponse();
        response.setLogref("error");
        response.setMessage("The server couldn't process request . Please contact to admin");
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(MissingRequestHeaderException.class)
    public OutputResponseError handleMissingRequestHeaderException(MissingRequestHeaderException e) {
        return new OutputResponseError(HttpStatus.FORBIDDEN.toString(), e.getMessage());
    }
    

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        MultipleErrorResponse response = new MultipleErrorResponse();
        response.setLogref("structured_error");
        List<SingleError> list = new ArrayList<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            SingleError singleError = new SingleError();
            singleError.setField(fieldError.getField());
            singleError.setMessage(fieldError.getDefaultMessage());
            list.add(singleError);
        }
        response.setErrors(list);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}





