package com.asemokamichi.kz.edupath.service;

import com.asemokamichi.kz.edupath.dto.ProgressDTO;
import com.asemokamichi.kz.edupath.entity.*;
import com.asemokamichi.kz.edupath.exceptions.InvalidRequest;
import com.asemokamichi.kz.edupath.exceptions.ResourceNotFound;
import com.asemokamichi.kz.edupath.repository.LessonRepository;
import com.asemokamichi.kz.edupath.repository.ProgresRepository;
import com.asemokamichi.kz.edupath.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProgressService {
    @Autowired
    private ProgresRepository progresRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LessonRepository lessonRepository;

    private final static String INVALID_REQUEST = "user ID and lesson ID";
    private final static String RESOURCE_NOT_FOUND = "enrollment";

    @Transactional
    public Progress createProgress(ProgressDTO progressDTO){
        User user = userRepository.findById(progressDTO.getUser_id()).orElseThrow(()->
                new InvalidRequest(INVALID_REQUEST));

        Lesson lesson = lessonRepository.findById(progressDTO.getLesson_id()).orElseThrow(()->
                new InvalidRequest(INVALID_REQUEST));

        Progress progress = new Progress();
        progress.setUser(user);
        progress.setLesson(lesson);
        if (progress.getCompletedAt() !=null) progress.setCompletedAt(progressDTO.getCompleted_at());


        return progresRepository.save(progress);
    }

    @Transactional
    public Progress findById(Long id){
        return progresRepository.findById(id).orElseThrow(()->new ResourceNotFound(RESOURCE_NOT_FOUND));
    }

    @Transactional
    public void deleteProgress(Long id){
        progresRepository.deleteById(id);
    }
}
