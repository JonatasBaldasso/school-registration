package com.baldasso.schoolregistration.unit.data;

import com.baldasso.schoolregistration.entities.Course;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static com.baldasso.schoolregistration.unit.data.StudentDataTest.setOf50Students;

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

    public static Set<Course> setOf5Courses() {
        Set<Course> courseSet = new HashSet<>();
        for(int i = 1; i <= 5 ; i++) {
            Course course = new Course();
            course.setId(Integer.toUnsignedLong(i));
            course.setName("Course Test");
            courseSet.add(course);
        }
        return courseSet;
    }

    public static Course validKotlinCourse() {
        Course course = new Course();
        course.setId(3L);
        course.setName("Introduction of Kotlin");
        return course;
    }

    public static Course courseFull() {
        Course course = new Course();
        course.setId(4L);
        course.setName("Introduction of Javascript");
        course.setStudents(setOf50Students());
        return course;
    }

    public static Set<Course> validSetOfCourses() {
        Set<Course> courseSet = new HashSet<>();
        courseSet.add(validJavaCourse());
        courseSet.add(validPythonCourse());
        courseSet.add(validKotlinCourse());
        return courseSet;
    }

    public static Optional<Course> optionalCourse() {
        return Optional.of(validJavaCourse());
    }

    public static Optional<Course> emptyOptional() {
        return Optional.empty();
    }
}
