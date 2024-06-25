package com.asemokamichi.kz.edupath.service;

import com.asemokamichi.kz.edupath.dto.CourseDTO;
import com.asemokamichi.kz.edupath.entity.Course;
import com.asemokamichi.kz.edupath.entity.User;
import com.asemokamichi.kz.edupath.exceptions.InvalidRequest;
import com.asemokamichi.kz.edupath.exceptions.ResourceNotFound;
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

    private final static String INVALID_REQUEST = "title, description, and instructor ID";
    private final static String RESOURCE_NOT_FOUND = "course";

    @Transactional
    public Course createCourse(CourseDTO courseDTO) {
        User user = userRepository.findById(courseDTO.getInstructor_id()).orElseThrow(() ->
                new InvalidRequest(INVALID_REQUEST));
        if (!courseDTO.checkValidation()) throw new InvalidRequest(INVALID_REQUEST);

        Course course = new Course(courseDTO, user);

        return courseRepository.save(course);
    }

    @Transactional
    public Course findById(Long id) {
        return courseRepository.findById(id).orElseThrow(() -> new ResourceNotFound(RESOURCE_NOT_FOUND));
    }

    @Transactional
    public Course updateCourse(Long id, CourseDTO courseDTO) {
        Course course = courseRepository.findById(id).orElseThrow(() -> new ResourceNotFound(RESOURCE_NOT_FOUND));

        if (courseDTO.getTitle() != null && !courseDTO.getTitle().isBlank()) {
            course.setTitle(course.getTitle());
        }

        if (courseDTO.getDescription() != null && !courseDTO.getDescription().isBlank()) {
            course.setDescription(course.getDescription());
        }

        if (courseDTO.getInstructor_id() != null) {
            User user = userRepository.findById(courseDTO.getInstructor_id()).orElseThrow(() ->
                    new InvalidRequest(INVALID_REQUEST));
            course.setUser(user);
        }

        return courseRepository.save(course);
    }

    @Transactional
    public String deleteCourse(Long id) {
        Course course = courseRepository.findById(id).orElseThrow(() -> new ResourceNotFound(RESOURCE_NOT_FOUND));
        courseRepository.delete(course);

        return course.getTitle();
    }
}
