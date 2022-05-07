package com.baldasso.schoolregistration.exepctions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class StudentNotFound extends RuntimeException {
    public StudentNotFound() {
        super();
    }

    public StudentNotFound(String message) {
        super(message);
    }
}
