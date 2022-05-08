package com.baldasso.schoolregistration.repository;

import com.baldasso.schoolregistration.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("SELECT c FROM Course c LEFT JOIN CourseRegistration cr ON c.id = cr.course WHERE cr.id is null")
    public Collection<Course> findCourseWithNoStudents();


    Collection<Course> findByStudents_Id(Long studentId);
}
