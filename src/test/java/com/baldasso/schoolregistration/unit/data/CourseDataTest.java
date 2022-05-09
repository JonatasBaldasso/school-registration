package com.baldasso.schoolregistration.unit.data;

import com.baldasso.schoolregistration.entities.Course;
import com.baldasso.schoolregistration.entities.Student;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class CourseDataTest {
    public static Course validJavaCourse() {
        Course course = new Course();
        course.setId(1L);
        course.setName("Introduction of Java");
        return course;
    }

    public static Course validPythonCourse() {
        Course course = new Course();
        course.setId(2L);
        course.setName("Introduction of Python");
        return course;
    }

    public static Course validKotlinCourse() {
        Course course = new Course();
        course.setId(3L);
        course.setName("Introduction of Kotlin");
        return course;
    }

    public static Set<Course> validSetOfCourses() {
        Set<Course> courseSet = new HashSet<>();
        courseSet.add(validJavaCourse());
        courseSet.add(validPythonCourse());
        courseSet.add(validKotlinCourse());
        return courseSet;
    }
}
