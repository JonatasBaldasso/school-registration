package com.baldasso.schoolregistration.controller;

import com.baldasso.schoolregistration.dto.CourseDTO;
import com.baldasso.schoolregistration.entities.Course;
import com.baldasso.schoolregistration.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/course")
@Slf4j
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/{courseId}")
    public Course findById(@PathVariable("courseId") Long courseId) {
        log.info("Call findById - ID: " + courseId);
        Course course = courseService.findById(courseId);
        log.info("Finished findById " + courseId);
        return course;
    }

    @GetMapping("/student/{studentId}")
    public Collection<Course> findCourseByStudent (@PathVariable("studentId") Long studentId) {
        log.info("Call findCourseByStudent - studentId: " + studentId);
        Collection<Course> courses = courseService.findCourseByStudent(studentId);
        log.info("Finished findCourseByStudent - studentId: " + studentId);
        return courses;
    }

    @GetMapping("/no-register")
    public Collection<Course> findCourseWithNoStudent () {
        log.info("Call findCourseWithNoStudent");
        Collection<Course> courses = courseService.findAllCoursesWithNoStudent();
        log.info("Finished findCourseWithNoStudent - course found: " + courses.size());
        return courses;
    }

    @PostMapping
    public Course createCourse(@RequestBody CourseDTO courseDTO) {
        log.info("Call createCourse - course: " + courseDTO.getName());
        Course course = courseService.createCourse(courseDTO);
        log.info("Finished createCourse - course: " + courseDTO.getName());
        return course;
    }

    @PutMapping("/{courseId}")
    public void updateCourse(@PathVariable("courseId") Long courseId,
                           @RequestBody CourseDTO course) {
        log.info("Call updateCourse - courseId: " + courseId);
        courseService.updateCourse(courseId, course);
        log.info("Finished updateCourse - courseId: " + courseId);
    }

    @DeleteMapping("/{courseId}")
    public void deleteCourse(@PathVariable("courseId") Long courseId) {
        log.info("Call deleteCourse - studentId: " + courseId);
        courseService.deleteCourse(courseId);
        log.info("Finished deleteCourse - studentId: " + courseId);
    }
}
