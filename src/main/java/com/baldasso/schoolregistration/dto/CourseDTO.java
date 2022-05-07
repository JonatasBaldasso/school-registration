package com.baldasso.schoolregistration.dto;

import com.baldasso.schoolregistration.entities.Course;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CourseDTO {
    Long id;

    @NotNull
    String name;

    public Course toCourse() {
        Course course = new Course();
        course.setName(this.name);
        return course;
    }
}
