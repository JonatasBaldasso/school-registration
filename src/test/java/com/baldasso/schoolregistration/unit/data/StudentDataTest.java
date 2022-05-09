package com.baldasso.schoolregistration.unit.data;


import com.baldasso.schoolregistration.entities.Student;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static com.baldasso.schoolregistration.unit.data.CourseDataTest.setOf5Courses;
import static com.baldasso.schoolregistration.unit.data.CourseDataTest.validSetOfCourses;

public class StudentDataTest {

    public static Student validStudent() {
        Student student = new Student();
        student.setId(1L);
        student.setName("Joao");
        return student;
    }

    public static Student studentWithJavaCourse() {
        Student student = new Student();
        student.setId(2L);
        student.setName("John");
        student.setCourses(validSetOfCourses());
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
        studentSet.add(studentWithJavaCourse());
        studentSet.add(validStudent3());
        return studentSet;
    }

    public static Student studentFull() {
        Student student = new Student();
        student.setId(4L);
        student.setName("Amy");
        student.setCourses(setOf5Courses());
        return student;
    }

    public static Set<Student> setOf50Students() {
        Set<Student> studentSet = new HashSet<>();
        for(int i = 1; i <= 50 ; i++) {
            Student student = new Student();
            student.setId(Integer.toUnsignedLong(i));
            student.setName("Student Test");
            studentSet.add(student);
        }
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
