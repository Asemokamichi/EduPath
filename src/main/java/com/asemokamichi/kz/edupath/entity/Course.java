package com.asemokamichi.kz.edupath.entity;

import com.asemokamichi.kz.edupath.dto.CourseDTO;
import com.asemokamichi.kz.edupath.dto.UserDTO;
import com.asemokamichi.kz.edupath.exceptions.InvalidRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "courses")
@Data
@NoArgsConstructor
public class Course {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private User user;

    @OneToMany(mappedBy = "course")
    @JsonIgnore
    private List<Lesson> lessons;

    @OneToMany(mappedBy = "course")
    @JsonIgnore
    private List<Enrollment> enrollments;

    public Course(CourseDTO courseDTO, User user) {
        title = courseDTO.getTitle();
        description = courseDTO.getDescription();
        this.user = user;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
