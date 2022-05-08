package com.baldasso.schoolregistration.unit;


import com.baldasso.schoolregistration.entities.Student;

import java.util.Optional;

public class StudentDataTest {
    public static Student validStudent() {
        Student student = new Student();
        student.setId(1L);
        student.setName("Joao");
        return student;
    }

    public static Optional<Student> validOptionalStudent() {
        return Optional.of(validStudent());
    }

    public static Optional<Student> emptyStudent() {
        return Optional.empty();
    }
}
