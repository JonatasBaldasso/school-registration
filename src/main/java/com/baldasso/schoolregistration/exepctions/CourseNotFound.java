package com.baldasso.schoolregistration.exepctions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CourseNotFound extends RuntimeException {
    public CourseNotFound() {
        super();
    }

    public CourseNotFound(String message) {
        super(message);
    }
}
