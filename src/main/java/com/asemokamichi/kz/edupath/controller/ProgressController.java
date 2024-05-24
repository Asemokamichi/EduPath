package com.asemokamichi.kz.edupath.controller;

import com.asemokamichi.kz.edupath.dto.LessonDTO;
import com.asemokamichi.kz.edupath.dto.ProgressDTO;
import com.asemokamichi.kz.edupath.entity.Progress;
import com.asemokamichi.kz.edupath.service.ProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/progress")
public class ProgressController {
    @Autowired
    private ProgressService progressService;

    @PostMapping
    public ResponseEntity<?> createProgress(@RequestBody ProgressDTO progressDTO){
        Progress progress = progressService.createProgress(progressDTO);

        if (progress == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Пользователь с таким именем уже существует");

        return ResponseEntity.ok(new ProgressDTO(progress));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProgress(@PathVariable Long id){
        Progress progress = progressService.findById(id);

        if (progress == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Пользователь с таким именем уже существует");

        return ResponseEntity.ok(new ProgressDTO(progress));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProgress(@PathVariable Long id){
        Progress progress = progressService.findById(id);

        if (progress == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Пользователь с таким именем уже существует");

        progressService.deleteProgress(progress);

        return ResponseEntity.ok(String.format("Прогресс #id'%s' удален", progress.getId()));
    }
}
