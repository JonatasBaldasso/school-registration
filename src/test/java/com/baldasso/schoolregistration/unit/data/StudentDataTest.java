package com.baldasso.schoolregistration.unit.data;


import com.baldasso.schoolregistration.entities.Course;
import com.baldasso.schoolregistration.entities.Student;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static com.baldasso.schoolregistration.unit.data.CourseDataTest.validJavaCourse;
import static org.springframework.boot.context.properties.bind.Bindable.listOf;
import static org.springframework.boot.context.properties.bind.Bindable.setOf;

public class StudentDataTest {

    public static Student validStudent() {
        Student student = new Student();
        student.setId(1L);
        student.setName("Joao");
        return student;
    }

    public static Student validStudent2() {
        Student student = new Student();
        student.setId(2L);
        student.setName("John");
        return student;
    }

    public static Student validStudent3() {
        Student student = new Student();
        student.setId(3L);
        student.setName("Marry");
        return student;
    }

    public static Set<Student> validSetOfStudent() {
        Set<Student> studentSet = new HashSet<>();
        studentSet.add(validStudent());
        studentSet.add(validStudent2());
        studentSet.add(validStudent3());
        return studentSet;
    }

    public static Collection<Student> validCollectionOfStudents() {
        return validSetOfStudent();
    }

    public static Optional<Student> validOptionalStudent() {
        return Optional.of(validStudent());
    }

    public static Optional<Student> emptyStudent() {
        return Optional.empty();
    }
}
