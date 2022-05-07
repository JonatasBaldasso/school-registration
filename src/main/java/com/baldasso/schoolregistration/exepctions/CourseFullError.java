package com.baldasso.schoolregistration.exepctions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class CourseFullError extends RuntimeException {
    public CourseFullError() {
        super();
    }
}
