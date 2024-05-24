package com.asemokamichi.kz.edupath.service;

import com.asemokamichi.kz.edupath.dto.ProgressDTO;
import com.asemokamichi.kz.edupath.entity.Progress;
import com.asemokamichi.kz.edupath.repository.ProgresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProgressService {
    @Autowired
    private ProgresRepository progresRepository;

    @Transactional
    public Progress createProgress(ProgressDTO progressDTO){
        return null;
    }

    @Transactional
    public Progress findById(Long id){
        return progresRepository.findById(id).orElse(null);
    }

    @Transactional
    public void deleteProgress(Progress progress){
        progresRepository.delete(progress);
    }
}
