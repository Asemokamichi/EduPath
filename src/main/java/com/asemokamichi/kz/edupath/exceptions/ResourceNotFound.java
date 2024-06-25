package com.asemokamichi.kz.edupath.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFound extends RuntimeException {
    public ResourceNotFound(String name) {
        super(String.format("No %s found with the provided ID.", name));
    }
}
