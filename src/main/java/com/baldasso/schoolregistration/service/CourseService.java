package com.baldasso.schoolregistration.service;

import com.baldasso.schoolregistration.dto.CourseDTO;
import com.baldasso.schoolregistration.entities.Course;
import com.baldasso.schoolregistration.exepctions.CourseNotFound;
import com.baldasso.schoolregistration.repository.CourseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
@Slf4j
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;


    public Course findById(Long courseId) {
        Optional<Course> maybeCourse = courseRepository.findById(courseId);
        if(maybeCourse.isEmpty()) {
            log.warn("aaa");
            throw new CourseNotFound();
        }
        return maybeCourse.get();
    }

    public Course createCourse(CourseDTO courseDTO) {
        return courseRepository.save(courseDTO.toCourse());
    }

    public void deleteCourse(Long courseId) {
        Course course = findById(courseId);
        courseRepository.delete(course);
    }

    public void updateCourse(Long courseId, CourseDTO courseDTO) {
        Course course = findById(courseId);
        if(courseDTO.getName() != null && !courseDTO.getName().isEmpty()) {
            course.setName(courseDTO.getName());
            courseRepository.save(course);
        } else {
            log.warn("Warn updateCourse no field to update - courseId:" + courseId);
        }
    }

    public Collection<Course> findAllCoursesWithNoStudent() {
        return courseRepository.findCourseWithNoStudents();
    }

    public Collection<Course> findCourseByStudent(Long studentId) {
        return courseRepository.findByStudents_Id(studentId);
    }
}
