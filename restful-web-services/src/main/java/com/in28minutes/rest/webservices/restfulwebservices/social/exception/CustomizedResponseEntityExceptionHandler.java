package com.in28minutes.rest.webservices.restfulwebservices.social.exception;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

import static java.util.stream.Collectors.joining;
import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorDetails> handleAllExceptions(Exception ex, WebRequest request) {
        var errorDetails = ErrorDetails.builder()
                .message(ex.getMessage())
                .details(request.getDescription(false))
                .timeStamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(errorDetails, INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<ErrorDetails> handleUserNotFoundException(Exception ex, WebRequest request) {
        var errorDetails = ErrorDetails.builder()
                .message(ex.getMessage())
                .details(request.getDescription(false))
                .timeStamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(errorDetails, NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        String fieldErrors = ex.getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(joining(" | "));

        var errorDetails = ErrorDetails.builder()
                .message(fieldErrors)
                .details(request.getDescription(false))
                .timeStamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(errorDetails, BAD_REQUEST);
    }
}
