package com.baldasso.schoolregistration.unit.controller;

import com.baldasso.schoolregistration.controller.CourseController;
import com.baldasso.schoolregistration.service.CourseService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static com.baldasso.schoolregistration.unit.data.CourseDataTest.validJavaCourse;
import static com.baldasso.schoolregistration.unit.data.CourseDataTest.validSetOfCourses;
import static com.baldasso.schoolregistration.unit.data.StudentDataTest.validCollectionOfStudents;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CourseController.class)
public class CourseControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CourseService courseService;

    @Test
    public void givenValidCourse_WhenGetCourseById_ThenReturnCorrectJson() throws Exception {

        given(courseService.findById(1L)).willReturn(validJavaCourse());
        mvc.perform(get("/course/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("name").exists());
    }

    @Test
    public void givenValidCollection_WhenGetCourseByStudentId_ThenReturnCorrectJson() throws Exception {
        given(courseService.findCourseByStudent(1L)).willReturn(validSetOfCourses());
        mvc.perform(get("/course/student/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(3)))
                .andExpect(jsonPath("$[0].id").exists())
                .andExpect(jsonPath("$[0].name").exists());
    }

    @Test
    public void givenValidCollection_WhenGetStudentWithNoCourse_ThenReturnValidStudentList() throws Exception {
        given(courseService.findAllCoursesWithNoStudent()).willReturn(validSetOfCourses());
        mvc.perform(get("/course/no-register")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(3)))
                .andExpect(jsonPath("$[0].id").exists())
                .andExpect(jsonPath("$[0].name").exists());
    }
}
