package com.asemokamichi.kz.edupath.repository;

import com.asemokamichi.kz.edupath.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {
}