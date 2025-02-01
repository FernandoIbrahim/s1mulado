package com.example.S1mulado.domain.testquestion.dto;

import java.time.LocalDateTime;
import java.time.LocalTime;

public record UpdateTestQuestionDTO(Long id, String answer, String usedTime) {
}
