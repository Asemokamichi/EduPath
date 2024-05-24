package com.asemokamichi.kz.edupath.service;

import com.asemokamichi.kz.edupath.dto.EnrollmentDTO;
import com.asemokamichi.kz.edupath.entity.Course;
import com.asemokamichi.kz.edupath.entity.Enrollment;
import com.asemokamichi.kz.edupath.entity.User;
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

    @Transactional
    public Enrollment createEnrollment(EnrollmentDTO enrollmentDTO){
        User user = userRepository.findById(enrollmentDTO.getUser_id()).orElse(null);
        if (user == null) return null;

        Course course = courseRepository.findById(enrollmentDTO.getCourse_id()).orElse(null);
        if (course == null) return null;

        Enrollment enrollment = new Enrollment();
        enrollment.setUser(user);
        enrollment.setCourse(course);
        enrollment.setEnrolledAt(enrollmentDTO.getEnrolled_at());

        return enrollmentRepository.save(enrollment);
    }

    @Transactional
    public Enrollment findById(Long id){
        return enrollmentRepository.findById(id).orElse(null);
    }

    @Transactional
    public Enrollment updateEnrollment(Long id, EnrollmentDTO enrollmentDTO){
        Enrollment enrollment = enrollmentRepository.findById(id).orElse(null);
        if (enrollment == null) return null;

        if (enrollmentDTO.getUser_id()!=null){
            User user = userRepository.findById(enrollmentDTO.getUser_id()).orElse(null);
            if (user == null) return null;
            enrollment.setUser(user);
        }

        if (enrollmentDTO.getCourse_id()!=null){
            Course course = courseRepository.findById(enrollmentDTO.getCourse_id()).orElse(null);
            if (course == null) return null;
            enrollment.setCourse(course);
        }

        if(enrollmentDTO.getEnrolled_at()!=null){
            enrollment.setEnrolledAt(enrollmentDTO.getEnrolled_at());
        }

        return enrollmentRepository.save(enrollment);
    }

    @Transactional
    public void deleteEnrollment(Enrollment enrollment){
        enrollmentRepository.delete(enrollment);
    }
}
