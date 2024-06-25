package com.asemokamichi.kz.edupath.dto;

import com.asemokamichi.kz.edupath.Enum.Role;
import com.asemokamichi.kz.edupath.entity.Enrollment;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class EnrollmentDTO {
    private Long id;
    private Long user_id;
    private Long course_id;
    private LocalDateTime enrolled_at;

    public EnrollmentDTO(Enrollment enrollment) {
        id = enrollment.getId();
        user_id = enrollment.getUser().getId();
        course_id = enrollment.getCourse().getId();
        enrolled_at = enrollment.getEnrolledAt();
    }

    public boolean checkValidation() {
        return enrolled_at != null;
    }
}
