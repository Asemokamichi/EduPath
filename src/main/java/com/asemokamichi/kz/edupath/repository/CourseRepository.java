package com.asemokamichi.kz.edupath.repository;

import com.asemokamichi.kz.edupath.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

}