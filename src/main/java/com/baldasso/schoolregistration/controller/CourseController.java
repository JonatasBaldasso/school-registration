package com.baldasso.schoolregistration.controller;

import com.baldasso.schoolregistration.dto.CourseDTO;
import com.baldasso.schoolregistration.service.CourseService;
import com.sun.istack.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/course")
@Slf4j
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping
    public void createCourse(@RequestBody @NotNull CourseDTO courseDTO) {
        log.info("My message");
        courseService.createCourse(courseDTO);
    }
}
