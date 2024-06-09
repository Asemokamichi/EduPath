package com.asemokamichi.kz.edupath.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UserAlreadyExists extends RuntimeException {
    public UserAlreadyExists(String name) {
        super(String.format("Пользователь с именем %s уже существует.", name));
    }
}
