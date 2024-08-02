package com.asemokamichi.kz.edupath.controller;

import com.asemokamichi.kz.edupath.dto.CourseDTO;
import com.asemokamichi.kz.edupath.entity.Course;
import com.asemokamichi.kz.edupath.kafka.producer.CourseProducer;
import com.asemokamichi.kz.edupath.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courses")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @Autowired
    private CourseProducer courseProducer;

    @PostMapping
    public ResponseEntity<?> createCourse(@RequestBody CourseDTO courseDTO){
        Course course = courseService.createCourse(courseDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(new CourseDTO(course));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCourse(@PathVariable Long id){
        Course course = courseService.findById(id);

        String message = "Данные о курсе: " + course + " успешно получены";
        courseProducer.sendMessage(message);

        return ResponseEntity.ok(new CourseDTO(course));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCourse(@PathVariable Long id, @RequestBody CourseDTO courseDTO){
        Course course = courseService.updateCourse(id, courseDTO);

        return ResponseEntity.ok(course);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable Long id){
        String courseTitle = courseService.deleteCourse(id);

        return ResponseEntity.ok(String.format("Курс '%s' удален", courseTitle));
    }
}
