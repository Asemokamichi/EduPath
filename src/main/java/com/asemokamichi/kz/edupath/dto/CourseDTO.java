package com.asemokamichi.kz.edupath.dto;

import com.asemokamichi.kz.edupath.Enum.Role;
import com.asemokamichi.kz.edupath.entity.Course;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CourseDTO {
    private Long id;
    private String title;
    private String description;
    private Long instructor_id;

    public CourseDTO(Course course) {
        id = course.getId();
        title = course.getTitle();
        description = course.getDescription();
        if (course.getUser() != null) instructor_id = course.getUser().getId();
    }

    public CourseDTO(String title, String description, Long instructor_id) {
        this.title = title;
        this.description = description;
        this.instructor_id = instructor_id;
    }

    public boolean checkValidation() {
        return title != null && description != null;
    }
}
