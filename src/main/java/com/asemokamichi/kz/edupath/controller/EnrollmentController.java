package com.asemokamichi.kz.edupath.controller;

import com.asemokamichi.kz.edupath.dto.EnrollmentDTO;
import com.asemokamichi.kz.edupath.entity.Enrollment;
import com.asemokamichi.kz.edupath.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {
    @Autowired
    private EnrollmentService enrollmentService;

    @PostMapping
    public ResponseEntity<?> createEnrollment(@RequestBody EnrollmentDTO enrollmentDTO){
        Enrollment enrollment = enrollmentService.createEnrollment(enrollmentDTO);

        if (enrollment == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Пользователь или Курс не найден...");

        return ResponseEntity.ok(new EnrollmentDTO(enrollment));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEnrollment(@PathVariable Long id){
        Enrollment enrollment = enrollmentService.findById(id);

        if (enrollment == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Запись не найдена...");

        return ResponseEntity.ok(new EnrollmentDTO(enrollment));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEnrollment(@PathVariable Long id, @RequestBody EnrollmentDTO enrollmentDTO){
        Enrollment enrollment = enrollmentService.updateEnrollment(id, enrollmentDTO);

        if (enrollment == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Пользователь или Курс не найден...");

        return ResponseEntity.ok(new EnrollmentDTO(enrollment));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEnrollment(@PathVariable Long id){
        Enrollment enrollment = enrollmentService.findById(id);

        if (enrollment == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Запись не найдена...");

        enrollmentService.deleteEnrollment(enrollment);

        return ResponseEntity.ok(String.format("Запись '%s' удалена", enrollment.getId()));
    }
}
