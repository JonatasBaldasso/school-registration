package com.baldasso.schoolregistration.repository;

import com.baldasso.schoolregistration.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {


    @Query("SELECT s FROM Student s LEFT JOIN CourseRegistration cr ON s.id = cr.student WHERE cr.id is null")
    public Collection<Student> findByCourseIsNull();
}
