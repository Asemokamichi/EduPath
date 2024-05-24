package com.asemokamichi.kz.edupath.dto;

import com.asemokamichi.kz.edupath.entity.Course;
import lombok.Data;

@Data
public class CourseDTO {
    private Long id;
    private String title;
    private String description;
    private Long instructor_id;

    public CourseDTO(Course course) {
        id = course.getId();
        title = course.getTitle();
        description = course.getDescription();
        instructor_id = course.getUser().getId();
    }
}
