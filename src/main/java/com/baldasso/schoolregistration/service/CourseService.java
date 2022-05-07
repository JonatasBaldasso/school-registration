package com.baldasso.schoolregistration.service;

import com.baldasso.schoolregistration.dto.CourseDTO;
import com.baldasso.schoolregistration.entities.Course;
import com.baldasso.schoolregistration.exepctions.CourseNotFound;
import com.baldasso.schoolregistration.repository.CourseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
