package com.asemokamichi.kz.edupath.service;

import com.asemokamichi.kz.edupath.dto.LessonDTO;
import com.asemokamichi.kz.edupath.entity.Course;
import com.asemokamichi.kz.edupath.entity.Lesson;
import com.asemokamichi.kz.edupath.exceptions.InvalidRequest;
import com.asemokamichi.kz.edupath.exceptions.ResourceNotFound;
import com.asemokamichi.kz.edupath.repository.CourseRepository;
import com.asemokamichi.kz.edupath.repository.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LessonService {
    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private CourseRepository courseRepository;

    private final static String INVALID_REQUEST = "title, content, and course ID";
    private final static String RESOURCE_NOT_FOUND = "lesson";

    @Transactional
    public Lesson createLesson(LessonDTO lessonDTO) {
        Course course = courseRepository.findById(lessonDTO.getCourse_id()).orElseThrow(() ->
                new InvalidRequest(INVALID_REQUEST));
        if (!lessonDTO.checkValidation()) throw new InvalidRequest(INVALID_REQUEST);

        Lesson lesson = new Lesson(lessonDTO, course);

        return lessonRepository.save(lesson);
    }

    @Transactional
    public Lesson finById(Long id) {
        return lessonRepository.findById(id).orElseThrow(()->new ResourceNotFound(RESOURCE_NOT_FOUND));
    }

    @Transactional
    public Lesson updateLesson(Long id, LessonDTO lessonDTO) {
        Lesson lesson = lessonRepository.findById(id).orElseThrow(()->new ResourceNotFound(RESOURCE_NOT_FOUND));

        if (lessonDTO.getTitle() != null && !lessonDTO.getTitle().isBlank()) {
            lesson.setTitle(lessonDTO.getTitle());
        }

        if (lessonDTO.getContent() != null && !lessonDTO.getContent().isBlank()) {
            lesson.setContent(lessonDTO.getContent());
        }

        if (lessonDTO.getCourse_id() != null) {
            Course course = courseRepository.findById(lessonDTO.getCourse_id()).orElseThrow(()->
                    new InvalidRequest(INVALID_REQUEST));
            lesson.setCourse(course);
        }

        return lessonRepository.save(lesson);
    }

    @Transactional
    public String deleteLesson(Long id) {
        Lesson lesson = lessonRepository.findById(id).orElseThrow(()-> new ResourceNotFound(RESOURCE_NOT_FOUND));
        lessonRepository.delete(lesson);

        return lesson.getTitle();
    }
}
