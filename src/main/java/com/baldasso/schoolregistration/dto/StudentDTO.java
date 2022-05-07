package com.baldasso.schoolregistration.dto;


import com.baldasso.schoolregistration.entities.Student;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
public class StudentDTO {
    Long id;

    @NotNull
    String name;

    public Student toStudent() {
        Student student = new Student();
        student.setName(this.name);
        return student;
    }
}
