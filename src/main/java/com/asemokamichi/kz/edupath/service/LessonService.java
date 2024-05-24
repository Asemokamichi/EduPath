package com.asemokamichi.kz.edupath.service;

import com.asemokamichi.kz.edupath.dto.LessonDTO;
import com.asemokamichi.kz.edupath.entity.Course;
import com.asemokamichi.kz.edupath.entity.Lesson;
import com.asemokamichi.kz.edupath.entity.User;
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

    @Transactional
    public Lesson createLesson(LessonDTO lessonDTO) {
        Course course = courseRepository.findById(lessonDTO.getCourse_id()).orElse(null);
        if (course == null) return null;

        Lesson lesson = new Lesson(lessonDTO, course);

        return lessonRepository.save(lesson);
    }

    @Transactional
    public Lesson finById(Long id) {
        return lessonRepository.findById(id).orElse(null);
    }

    @Transactional
    public Lesson updateLesson(Long id, LessonDTO lessonDTO) {
        Lesson lesson = lessonRepository.findById(id).orElse(null);
        if (lesson == null) return null;

        if (lessonDTO.getTitle()!=null && !lessonDTO.getTitle().isBlank()){
            lesson.setTitle(lessonDTO.getTitle());
        }

        if (lessonDTO.getContent()!=null && !lessonDTO.getContent().isBlank()){
            lesson.setContent(lessonDTO.getContent());
        }

        if (lessonDTO.getCourse_id()!=null){
            Course course = courseRepository.findById(lessonDTO.getCourse_id()).orElse(null);
            if (course == null) return null;
            lesson.setCourse(course);
        }

        return lessonRepository.save(lesson);
    }

    @Transactional
    public void deleteLesson(Lesson lesson) {
        lessonRepository.delete(lesson);
    }
}
