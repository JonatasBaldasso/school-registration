package com.baldasso.schoolregistration.controller;

import com.baldasso.schoolregistration.dto.StudentDTO;
import com.baldasso.schoolregistration.entities.Student;
import com.baldasso.schoolregistration.model.input.PostStudentRegisterCourseInput;
import com.baldasso.schoolregistration.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/student")
@Slf4j
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/{studentId}")
    public Student findById(@PathVariable("studentId") Long studentId) {
        log.info("Call StudentController.findById " + studentId);
        Student student = studentService.findById(studentId);
        log.info("Finished StudentController.findById " + studentId);
        return student;
    }

    @GetMapping("/course/{courseId}")
    public Set<Student> findStudentsByCourse (@PathVariable("courseId") Long course) {
        return studentService.findStudentsByCourse(course);
    }

    @GetMapping("/no-course")
    public List<Student> findStudentsWithNoCourse () {
        return studentService.findAllStudentsWithNoCourse();
    }

    @PostMapping("/{studentId}/course")
    public void registerStudent(@PathVariable("studentId") Long studentId, @RequestBody PostStudentRegisterCourseInput course) {
        studentService.registerStudent(studentId, course);
    }

    @PostMapping
    public void createUser(@RequestBody StudentDTO studentDTO) {
        studentService.createStudent(studentDTO);
    }

    @DeleteMapping("/{studentId}")
    public void deleteUser(@PathVariable("studentId") Long studentId) {
        studentService.deleteStudent(studentId);
    }
}
