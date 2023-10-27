package com.tools.prime.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class CustomizedResponseExceptionHandler {

    private static final  String  INCORRECT_REQUEST = "Invalid Range: Please enter valid range";
    private static final String NOT_MATCH_ALGORITHMS ="Algorithm does not match.";
    private static final  String  NUMBER_FORMAT = "Invalid Number";
    private static final String  INVALID_ENTRY= "Input only allows numerical values.";
    private static final String RESOURCE_NOT_FOUND ="Resource not found";

    @ExceptionHandler(InvalidPrimeNumberRangeException.class)
    public final ResponseEntity<ErrorResponse> handleInvalidParameterException
            (InvalidPrimeNumberRangeException ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse(LocalDateTime.now(), INCORRECT_REQUEST, details);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(NotMatchingAlgorithmException.class)
    public final ResponseEntity<ErrorResponse> handleNotMatchingAlgorithmException
            (NotMatchingAlgorithmException ex, WebRequest request) {
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

    @ExceptionHandler(value = NumberFormatException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleResourceNumberFormatException(NumberFormatException ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(INVALID_ENTRY);
        ErrorResponse error = new ErrorResponse(LocalDateTime.now(), NUMBER_FORMAT, details);
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }
}
