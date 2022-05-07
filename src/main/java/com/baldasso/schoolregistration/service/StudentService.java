package com.baldasso.schoolregistration.service;

import com.baldasso.schoolregistration.dto.StudentDTO;
import com.baldasso.schoolregistration.entities.Course;
import com.baldasso.schoolregistration.entities.Student;
import com.baldasso.schoolregistration.exepctions.CourseFullError;
import com.baldasso.schoolregistration.exepctions.StudentAlreadyRegistered;
import com.baldasso.schoolregistration.exepctions.StudentCoursesFullError;
import com.baldasso.schoolregistration.exepctions.StudentNotFound;
import com.baldasso.schoolregistration.model.input.PostStudentRegisterCourseInput;
import com.baldasso.schoolregistration.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseService courseService;

    public Student findById(Long studentId) {
        Optional<Student> maybeStudent = studentRepository.findById(studentId);
        if(maybeStudent.isEmpty()) {
            log.warn("Student not found for studentId = " + studentId);
            throw new StudentNotFound("Student not found");
        }
        return maybeStudent.get();
    }

    public Set<Student> findStudentsByCourse(Long courseId) {
        Course course = courseService.findById(courseId);
        return course.getStudents();
    }

    public void createStudent(StudentDTO student) {
        Student studentCreated = studentRepository.save(student.toStudent());
        System.out.println(studentCreated.getId());
    }

    public void deleteStudent(Long studentId) {
        Student student = findById(studentId);
        studentRepository.delete(student);
    }

    public void registerStudent(Long studentId, PostStudentRegisterCourseInput registerCourse) {
        Course course = courseService.findById(registerCourse.getId());
        Student student = findById(studentId);

        assertRegisterStudentsData(student, course);

        student.getCourses().add(course);
        studentRepository.save(student);
    }

    public List<Student> findAllStudentsWithNoCourse() {
        return (List<Student>) studentRepository.findByCourseIsNull();
    }

    private void assertRegisterStudentsData(Student student, Course course) {
        if (student.getCourses().size() >= 5) {
            throw new StudentCoursesFullError();
        }

        if (course.getStudents().size() >= 50) {
            throw new CourseFullError();
        }

        if(student.getCourses().contains(course)) {
            throw new StudentAlreadyRegistered();
        }
    }

    public Student updateStudent(Long studentId, StudentDTO studentDTO) {
        Student student = findById(studentId);
        if(!studentDTO.getName().isEmpty()) {
            student.setName(studentDTO.getName());
        }
        return studentRepository.save(student);
    }

}
