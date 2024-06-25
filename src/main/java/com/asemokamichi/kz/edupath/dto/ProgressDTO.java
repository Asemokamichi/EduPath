package com.asemokamichi.kz.edupath.dto;

import com.asemokamichi.kz.edupath.Enum.Role;
import com.asemokamichi.kz.edupath.entity.Progress;
import com.asemokamichi.kz.edupath.service.ProgressService;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ProgressDTO {
    private Long id;
    private Long user_id;
    private Long lesson_id;
    private LocalDateTime completed_at;

    public ProgressDTO(Progress progress) {
        id = progress.getId();
        user_id = progress.getUser().getId();
        lesson_id = progress.getLesson().getId();
        completed_at = progress.getCompletedAt();
    }

}
