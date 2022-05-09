package com.baldasso.schoolregistration.unit.service;

import com.baldasso.schoolregistration.entities.Course;
import com.baldasso.schoolregistration.exepctions.CourseNotFound;
import com.baldasso.schoolregistration.repository.CourseRepository;
import com.baldasso.schoolregistration.service.CourseService;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static com.baldasso.schoolregistration.unit.data.CourseDataTest.emptyOptional;
import static com.baldasso.schoolregistration.unit.data.CourseDataTest.optionalCourse;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseServiceTest {

    @Autowired
    private CourseService courseService;

    @MockBean
    private CourseRepository courseRepository;

    @Rule
    public final ExpectedException exception = ExpectedException.none();


    @Test
    public void givenValidCourse_WhenFindById_ThenReturnValidCourse() {
        Mockito.when(courseRepository.findById(any())).thenReturn(optionalCourse());
        Course course = courseService.findById(1L);
        assertThat(course.getId()).isEqualTo(1L);
    }

    @Test
    public void givenEmptyCourse_WhenFindById_ThenReturnException() {
        Mockito.when(courseRepository.findById(any())).thenReturn(emptyOptional());
        exception.expect(CourseNotFound.class);
        assertThrows(CourseNotFound.class, () -> courseService.findById(1L));
    }
}
