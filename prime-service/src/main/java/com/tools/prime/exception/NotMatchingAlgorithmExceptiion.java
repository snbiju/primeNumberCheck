package com.tools.prime.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotMatchingAlgorithmExceptiion extends Exception{
    public NotMatchingAlgorithmExceptiion(String message){
        super(message);
    }
}
