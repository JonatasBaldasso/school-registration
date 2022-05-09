package com.baldasso.schoolregistration.unit.controller;

import com.baldasso.schoolregistration.controller.StudentController;
import com.baldasso.schoolregistration.entities.Student;
import com.baldasso.schoolregistration.service.StudentService;
import com.baldasso.schoolregistration.unit.data.StudentDataTest;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Collection;

import static com.baldasso.schoolregistration.unit.data.StudentDataTest.validCollectionOfStudents;
import static com.baldasso.schoolregistration.unit.data.StudentDataTest.validStudent;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(StudentController.class)
public class StudentControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private StudentService studentService;

    @Test
    public void givenValidStudent_WhenGetStudentById_ThenReturnCorrectJson() throws Exception {

        given(studentService.findById(1L)).willReturn(validStudent());
        mvc.perform(get("/student/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("name").exists());
    }

    @Test
    public void givenValidCollection_WhenGetStudentByCourseId_ThenReturnCorrectJson() throws Exception {

        given(studentService.findStudentsByCourse(1L)).willReturn(validCollectionOfStudents());
        mvc.perform(get("/student/course/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(3)))
                .andExpect(jsonPath("$[0].id").exists())
                .andExpect(jsonPath("$[0].name").exists());
    }

    @Test
    public void givenEmpty_WhenGetStudentByCourseId_ThenReturnEmptyList() throws Exception {
        Collection<Student> students = new ArrayList<>();
        given(studentService.findStudentsByCourse(1L)).willReturn(students);
        mvc.perform(get("/student/course/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(0)));
    }

    @Test
    public void givenValidCollection_WhenGetStudentWithNoCourse_ThenReturnValidStudentList() throws Exception {
        given(studentService.findAllStudentsWithNoCourse()).willReturn(validCollectionOfStudents());
        mvc.perform(get("/student/no-course")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(3)))
                .andExpect(jsonPath("$[0].id").exists())
                .andExpect(jsonPath("$[0].name").exists());
    }

    @Test
    public void givenValidStudent_WhenPostCreateUser_ThenReturnValidStudent() throws Exception {
        given(studentService.createStudent(any())).willReturn(validStudent());
        mvc.perform(post("/student")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Mary\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("name").exists());
    }
}
