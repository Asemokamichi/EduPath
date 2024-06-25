package com.asemokamichi.kz.edupath.service;

import com.asemokamichi.kz.edupath.dto.EnrollmentDTO;
import com.asemokamichi.kz.edupath.entity.Course;
import com.asemokamichi.kz.edupath.entity.Enrollment;
import com.asemokamichi.kz.edupath.entity.User;
import com.asemokamichi.kz.edupath.exceptions.InvalidRequest;
import com.asemokamichi.kz.edupath.exceptions.ResourceNotFound;
import com.asemokamichi.kz.edupath.repository.CourseRepository;
import com.asemokamichi.kz.edupath.repository.EnrollmentRepository;
import com.asemokamichi.kz.edupath.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EnrollmentService {
    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    private final static String INVALID_REQUEST = "user ID and course ID";
    private final static String RESOURCE_NOT_FOUND = "enrollment";

    @Transactional
    public Enrollment createEnrollment(EnrollmentDTO enrollmentDTO){
        User user = userRepository.findById(enrollmentDTO.getUser_id()).orElseThrow(()->
                new InvalidRequest(INVALID_REQUEST));

        Course course = courseRepository.findById(enrollmentDTO.getCourse_id()).orElseThrow(()->
                new InvalidRequest(INVALID_REQUEST));

        Enrollment enrollment = new Enrollment();
        enrollment.setUser(user);
        enrollment.setCourse(course);
        if (enrollmentDTO.getEnrolled_at() !=null) enrollment.setEnrolledAt(enrollmentDTO.getEnrolled_at());

        return enrollmentRepository.save(enrollment);
    }

    @Transactional
    public Enrollment findById(Long id){
        return enrollmentRepository.findById(id).orElseThrow(()-> new ResourceNotFound(RESOURCE_NOT_FOUND));
    }

    @Transactional
    public void deleteEnrollment(Long id){
        enrollmentRepository.deleteById(id);
    }
}
