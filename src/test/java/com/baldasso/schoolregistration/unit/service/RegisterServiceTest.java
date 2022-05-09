package com.baldasso.schoolregistration.unit.service;

import com.baldasso.schoolregistration.exepctions.CourseFullError;
import com.baldasso.schoolregistration.exepctions.StudentAlreadyRegistered;
import com.baldasso.schoolregistration.exepctions.StudentFullError;
import com.baldasso.schoolregistration.repository.CourseRegistrationRepository;
import com.baldasso.schoolregistration.service.CourseService;
import com.baldasso.schoolregistration.service.RegisterService;
import com.baldasso.schoolregistration.service.StudentService;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static com.baldasso.schoolregistration.unit.data.CourseDataTest.courseFull;
import static com.baldasso.schoolregistration.unit.data.CourseDataTest.validJavaCourse;
import static com.baldasso.schoolregistration.unit.data.RegisterDataTest.validRegister;
import static com.baldasso.schoolregistration.unit.data.RegisterDataTest.validRegisterInput;
import static com.baldasso.schoolregistration.unit.data.StudentDataTest.*;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RegisterServiceTest {

    @Autowired
    private RegisterService registerService;

    @MockBean
    private CourseRegistrationRepository courseRegistrationRepository;

    @MockBean
    private StudentService studentService;

    @MockBean
    private CourseService courseService;

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void givenValidInputs_WhenRegisterStudent_ThenReturnNoError() {
        Mockito.when(courseRegistrationRepository.save(any())).thenReturn(validRegister());
        Mockito.when(studentService.findById(any())).thenReturn(validStudent());
        Mockito.when(courseService.findById(any())).thenReturn(validJavaCourse());
        registerService.registerStudent(validRegisterInput());
    }

    @Test
    public void givenFullStudent_WhenRegisterStudent_ThenReturnStudentFullError() {
        Mockito.when(courseRegistrationRepository.save(any())).thenReturn(validRegister());
        Mockito.when(studentService.findById(any())).thenReturn(studentFull());
        Mockito.when(courseService.findById(any())).thenReturn(validJavaCourse());
        exception.expect(StudentFullError.class);
        assertThrows(StudentFullError.class, () -> registerService.registerStudent(validRegisterInput()));
    }

    @Test
    public void givenFullCourse_WhenRegisterStudent_ThenReturnCourseFullError() {
        Mockito.when(courseRegistrationRepository.save(any())).thenReturn(validRegister());
        Mockito.when(studentService.findById(any())).thenReturn(validStudent());
        Mockito.when(courseService.findById(any())).thenReturn(courseFull());
        exception.expect(CourseFullError.class);
        assertThrows(CourseFullError.class, () -> registerService.registerStudent(validRegisterInput()));
    }

    @Test
    public void givenStudentWithJavaCourse_WhenRegisterStudent_ThenReturnStudentAlreadyRegisteredError() {
        Mockito.when(courseRegistrationRepository.save(any())).thenReturn(validRegister());
        Mockito.when(studentService.findById(any())).thenReturn(studentWithJavaCourse());
        Mockito.when(courseService.findById(any())).thenReturn(validJavaCourse());
        exception.expect(StudentAlreadyRegistered.class);
        assertThrows(StudentAlreadyRegistered.class, () -> registerService.registerStudent(validRegisterInput()));
    }
}
