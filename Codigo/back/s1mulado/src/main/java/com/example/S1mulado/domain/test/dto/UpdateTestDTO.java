package com.example.S1mulado.domain.test.dto;

import com.example.S1mulado.domain.testquestion.dto.UpdateTestQuestionDTO;

import java.time.LocalTime;
import java.util.List;

public record UpdateTestDTO(List<UpdateTestQuestionDTO> updateTestQuestionDTOS, String usedTime) {
}
