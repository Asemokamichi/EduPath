package com.asemokamichi.kz.edupath.dto;

import com.asemokamichi.kz.edupath.entity.Lesson;
import lombok.Data;

@Data
public class LessonDTO {
    private Long id;
    private String title;
    private String content;
    private Long course_id;

    public LessonDTO(Lesson lesson) {
        id = lesson.getId();
        title = lesson.getTitle();
        content = lesson.getContent();
        course_id = lesson.getCourse().getId();
    }
}
