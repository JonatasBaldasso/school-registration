package com.baldasso.schoolregistration.service;

import com.baldasso.schoolregistration.entities.Course;
import com.baldasso.schoolregistration.entities.CourseRegistration;
import com.baldasso.schoolregistration.entities.Student;
import com.baldasso.schoolregistration.exepctions.CourseFullError;
import com.baldasso.schoolregistration.exepctions.StudentAlreadyRegistered;
import com.baldasso.schoolregistration.exepctions.StudentCoursesFullError;
import com.baldasso.schoolregistration.model.input.PostRegisterStudentOnCourseInput;
import com.baldasso.schoolregistration.repository.CourseRegistrationRepository;
import com.baldasso.schoolregistration.repository.CourseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RegisterService {

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private CourseRegistrationRepository courseRegistrationRepository;

    public void registerStudent(PostRegisterStudentOnCourseInput registerCourse) {
        Course course = courseService.findById(registerCourse.getCourseId());
        Student student = studentService.findById(registerCourse.getStudentId());

        assertRegisterStudentsData(student, course);
        courseRegistrationRepository.save(toCourseRegistration(student, course));
    }

    private CourseRegistration toCourseRegistration(Student student, Course course) {
        CourseRegistration courseRegistration = new CourseRegistration();
        courseRegistration.setCourse(course);
        courseRegistration.setStudent(student);
        return courseRegistration;
    }

    private void assertRegisterStudentsData(Student student, Course course) {
        if (student.getCourses().size() >= 5) {
            log.error("Error assertRegisterStudentsData studentFull - studentId:" + student.getId());
            throw new StudentCoursesFullError();
        }

        if (course.getStudents().size() >= 50) {
            log.error("Error assertRegisterStudentsData CourseFull - courseId:" + course.getId());
            throw new CourseFullError();
        }

        if(student.getCourses().contains(course)) {
            log.error("Error assertRegisterStudentsData StudentAlreadyRegistered - studentId:" + student.getId());
            throw new StudentAlreadyRegistered();
        }
    }
}
