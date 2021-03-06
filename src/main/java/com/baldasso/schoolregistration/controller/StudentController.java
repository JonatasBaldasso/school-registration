package com.baldasso.schoolregistration.controller;

import com.baldasso.schoolregistration.dto.StudentDTO;
import com.baldasso.schoolregistration.entities.Student;
import com.baldasso.schoolregistration.model.input.PostRegisterStudentOnCourseInput;
import com.baldasso.schoolregistration.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/student")
@Slf4j
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/{studentId}")
    public Student findById(@PathVariable("studentId") Long studentId) {
        log.info("Call findById - ID: " + studentId);
        Student student = studentService.findById(studentId);
        log.info("Finished findById " + studentId);
        return student;
    }

    @GetMapping("/course/{courseId}")
    public Collection<Student> findStudentsByCourse (@PathVariable("courseId") Long courseId) {
        log.info("Call findStudentsByCourse - CourseId: " + courseId);
        Collection<Student> students = studentService.findStudentsByCourse(courseId);
        log.info("Finished findStudentsByCourse - CourseId: " + courseId);
        return students;
    }

    @GetMapping("/no-register")
    public Collection<Student> findStudentsWithNoCourse () {
        log.info("Call findStudentsWithNoCourse");
        Collection<Student> students = studentService.findAllStudentsWithNoCourse();
        log.info("Finished findStudentsWithNoCourse - students found: " +students.size());
        return students;
    }

//    @PostMapping("/{studentId}/course")
//    public void registerStudent(@PathVariable("studentId") Long studentId,
//                                @RequestBody PostRegisterStudentOnCourseInput course) {
//        log.info("Call registerStudent - student: " + studentId + " course: " + course.getId());
//        studentService.registerStudent(studentId, course);
//        log.info("Finished registerStudent - student: " + studentId + " course: " + course.getId());
//    }

    @PostMapping
    public Student createUser(@RequestBody StudentDTO studentDTO) {
        log.info("Call createUser - student: " + studentDTO.getName());
        Student student = studentService.createStudent(studentDTO);
        log.info("Finished createUser - student: " + studentDTO.getName());
        return student;
    }

    @PutMapping("/{studentId}")
    public void updateUser(@PathVariable("studentId") Long studentId,
                           @RequestBody StudentDTO student) {
        log.info("Call updateUser - studentId: " + studentId);
        studentService.updateStudent(studentId, student);
        log.info("Finished updateUser - student: " + studentId);
    }

    @DeleteMapping("/{studentId}")
    public void deleteUser(@PathVariable("studentId") Long studentId) {
        log.info("Call deleteUser - studentId: " + studentId);
        studentService.deleteStudent(studentId);
        log.info("Finished deleteUser - studentId: " + studentId);
    }
}
