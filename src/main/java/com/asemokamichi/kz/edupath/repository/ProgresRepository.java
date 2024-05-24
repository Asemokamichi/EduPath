package com.asemokamichi.kz.edupath.repository;

import com.asemokamichi.kz.edupath.entity.Progress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgresRepository extends JpaRepository<Progress, Long> {
}