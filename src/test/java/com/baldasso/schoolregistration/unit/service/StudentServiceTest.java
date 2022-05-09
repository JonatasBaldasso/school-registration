package com.baldasso.schoolregistration.unit.service;


import com.baldasso.schoolregistration.entities.Student;
import com.baldasso.schoolregistration.exepctions.StudentNotFound;
import com.baldasso.schoolregistration.repository.StudentRepository;
import com.baldasso.schoolregistration.service.StudentService;
import com.baldasso.schoolregistration.unit.data.StudentDataTest;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentServiceTest {

    @Autowired
    private StudentService studentService;

    @MockBean
    private StudentRepository studentRepository;

    @Rule
    public final ExpectedException exception = ExpectedException.none();


    @Test
    public void givenValidStudent_WhenFindById_ThenReturnValidStudent() {
        Mockito.when(studentRepository.findById(any())).thenReturn(StudentDataTest.validOptionalStudent());
        Student student = studentService.findById(1L);
        assertThat(student.getId()).isEqualTo(1L);
    }

    @Test
    public void givenEmptyStudent_WhenFindById_ThenReturnException() {
        Mockito.when(studentRepository.findById(any())).thenReturn(StudentDataTest.emptyStudent());
        exception.expect(StudentNotFound.class);
        assertThrows(StudentNotFound.class, () -> studentService.findById(1L));
    }
}
