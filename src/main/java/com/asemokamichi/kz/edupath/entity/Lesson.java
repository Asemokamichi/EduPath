package com.asemokamichi.kz.edupath.entity;

import com.asemokamichi.kz.edupath.dto.LessonDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "lessons")
@Data
@NoArgsConstructor
public class Lesson {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @OneToMany(mappedBy = "lesson")
    @JsonIgnore
    private List<Progress> progres;

    public Lesson(LessonDTO lessonDTO, Course course) {
        title = lessonDTO.getTitle();
        content = lessonDTO.getContent();
        this.course = course;
    }
}
