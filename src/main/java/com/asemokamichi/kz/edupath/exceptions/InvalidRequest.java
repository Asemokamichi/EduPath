package com.asemokamichi.kz.edupath.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidRequest extends RuntimeException {
    public InvalidRequest(String message) {
        super(String.format("The request body is invalid. Please provide a valid %s.", message));
    }
}
