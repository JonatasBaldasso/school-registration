package com.baldasso.schoolregistration.unit.data;

import com.baldasso.schoolregistration.entities.CourseRegistration;
import com.baldasso.schoolregistration.model.input.PostRegisterStudentOnCourseInput;

import static com.baldasso.schoolregistration.unit.data.CourseDataTest.validJavaCourse;
import static com.baldasso.schoolregistration.unit.data.StudentDataTest.validStudent;

public class RegisterDataTest {
    public static CourseRegistration validRegister() {
        CourseRegistration courseRegistration = new CourseRegistration();
        courseRegistration.setId(1L);
        courseRegistration.setCourse(validJavaCourse());
        courseRegistration.setStudent(validStudent());
        return courseRegistration;
    }

    public static PostRegisterStudentOnCourseInput validRegisterInput() {
        PostRegisterStudentOnCourseInput registerInput = new PostRegisterStudentOnCourseInput();
        registerInput.setCourseId(1L);
        registerInput.setStudentId(1L);
        return registerInput;
    }
}
