package com.baldasso.schoolregistration.service;

import com.baldasso.schoolregistration.dto.StudentDTO;
import com.baldasso.schoolregistration.entities.Student;
import com.baldasso.schoolregistration.exepctions.StudentNotFound;
import com.baldasso.schoolregistration.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

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
            log.error("Error: maybeStudent Student not found - studentId = " + studentId);
            throw new StudentNotFound("Student not found");
        }
        return maybeStudent.get();
    }

    public Collection<Student> findStudentsByCourse(Long courseId) {
        return studentRepository.findByCourses_Id(courseId);
    }

    public Student createStudent(StudentDTO student) {
        return studentRepository.save(student.toStudent());
    }

    public void deleteStudent(Long studentId) {
        Student student = findById(studentId);
        studentRepository.delete(student);
    }



    public Collection<Student> findAllStudentsWithNoCourse() {
        return studentRepository.findByCourseIsNull();
    }

    public void updateStudent(Long studentId, StudentDTO studentDTO) {
        Student student = findById(studentId);
        if(studentDTO.getName() != null && !studentDTO.getName().isEmpty()) {
            student.setName(studentDTO.getName());
            studentRepository.save(student);
        } else {
            log.warn("Warn updateStudent no field to update - studentId:" + studentId);
        }
    }
}
