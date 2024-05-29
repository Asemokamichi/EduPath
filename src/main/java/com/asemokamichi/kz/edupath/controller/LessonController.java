package com.asemokamichi.kz.edupath.controller;

import com.asemokamichi.kz.edupath.dto.LessonDTO;
import com.asemokamichi.kz.edupath.entity.Lesson;
import com.asemokamichi.kz.edupath.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lessons")
public class LessonController {
    @Autowired
    private LessonService lessonService;

    @PostMapping
    public ResponseEntity<?> createLesson(@RequestBody LessonDTO lessonDTO){
        Lesson lesson = lessonService.createLesson(lessonDTO);

//        if (lesson==null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Создание урока невозможно. Курс не найден...");

        return ResponseEntity.ok(new LessonDTO(lesson));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getLesson(@PathVariable Long id){
        Lesson lesson = lessonService.finById(id);

//        if (lesson==null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Урок не найден...");

        return ResponseEntity.ok(new LessonDTO(lesson));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateLesson(@PathVariable Long id, @RequestBody LessonDTO lessonDTO){
        Lesson lesson = lessonService.updateLesson(id, lessonDTO);

//        if (lesson == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Урок или Курс не найден...");

        return ResponseEntity.ok(new LessonDTO(lesson));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLesson(@PathVariable Long id){
        Lesson lesson = lessonService.finById(id);

//        if (lesson==null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Урок не найден...");

        lessonService.deleteLesson(lesson);

        return ResponseEntity.ok(String.format("Урок '%s' удален", lesson.getTitle()));
    }
}
