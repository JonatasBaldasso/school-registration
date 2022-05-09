package com.baldasso.schoolregistration.repository;

import com.baldasso.schoolregistration.entities.CourseRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRegistrationRepository extends JpaRepository<CourseRegistration, Long> {
}
