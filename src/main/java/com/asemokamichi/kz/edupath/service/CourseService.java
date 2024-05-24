package com.asemokamichi.kz.edupath.service;

import com.asemokamichi.kz.edupath.dto.CourseDTO;
import com.asemokamichi.kz.edupath.entity.Course;
import com.asemokamichi.kz.edupath.entity.Lesson;
import com.asemokamichi.kz.edupath.entity.User;
import com.asemokamichi.kz.edupath.repository.CourseRepository;
import com.asemokamichi.kz.edupath.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public Course createCourse(CourseDTO courseDTO) {
        User user = userRepository.findById(courseDTO.getInstructor_id()).orElse(null);
        if (user == null) return null;

        Course course = new Course(courseDTO, user);

        return courseRepository.save(course);
    }

    @Transactional
    public Course findById(Long id) {
        return courseRepository.findById(id).orElse(null);
    }

    @Transactional
    public Course updateCourse(Long id, CourseDTO courseDTO) {
        Course course = courseRepository.findById(id).orElse(null);
        if (course == null) return null;

        if (courseDTO.getTitle() != null && !courseDTO.getTitle().isBlank()) {
            course.setTitle(course.getTitle());
        }

        if (courseDTO.getDescription() != null && !courseDTO.getDescription().isBlank()) {
            course.setDescription(course.getDescription());
        }

        if (courseDTO.getInstructor_id() != null) {
            User user = userRepository.findById(courseDTO.getInstructor_id()).orElse(null);
            if (user == null) return null;
            course.setUser(user);
        }

        return courseRepository.save(course);
    }

    @Transactional
    public void deleteCourse(Course course) {
        courseRepository.delete(course);
    }
}
