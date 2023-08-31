package com.tools.prime.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class CustomizedResponseExceptionHandler extends ResponseEntityExceptionHandler {

    private static final  String  INCORRECT_REQUEST = "Invalid Range: Please enter valid range";
    private static final String NOT_MATCH_ALGORITHMS ="Algorithm is not match";

    private static final String RESOURCE_NOT_FOUND ="Resource not found";

    @ExceptionHandler(InvalidPrimeNumberRangeException.class)
    public final ResponseEntity<ErrorResponse> handleInvalidParameterException
            (InvalidPrimeNumberRangeException ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse(LocalDateTime.now(), INCORRECT_REQUEST, details);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(NotMatchingAlgorithmExceptiion.class)
    public final ResponseEntity<ErrorResponse> handleNotMatchingAlgorithmException
            (NotMatchingAlgorithmExceptiion ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse(LocalDateTime.now(), NOT_MATCH_ALGORITHMS, details);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse(LocalDateTime.now(), RESOURCE_NOT_FOUND, details);
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }
}
