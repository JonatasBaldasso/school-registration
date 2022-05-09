package com.baldasso.schoolregistration.model.input;


import lombok.Data;

@Data
public class PostRegisterStudentOnCourseInput {
    private Long studentId;
    private Long courseId;
}
