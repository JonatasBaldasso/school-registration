package com.baldasso.schoolregistration.controller;

import com.baldasso.schoolregistration.model.input.PostRegisterStudentOnCourseInput;
import com.baldasso.schoolregistration.service.RegisterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
@Slf4j
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @PostMapping
    public void registerStudent(@RequestBody PostRegisterStudentOnCourseInput registerStudentOnCourseInput) {
        log.info("Call registerStudent - student: " + registerStudentOnCourseInput.getStudentId() +
                " course: " + registerStudentOnCourseInput.getCourseId());
        registerService.registerStudent(registerStudentOnCourseInput);
        log.info("Finished registerStudent - student: " + registerStudentOnCourseInput.getStudentId() +
                " course: " + registerStudentOnCourseInput.getCourseId());
    }
}
