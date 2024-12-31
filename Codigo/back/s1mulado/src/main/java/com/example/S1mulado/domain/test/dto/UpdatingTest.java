package com.example.S1mulado.domain.test.dto;

import com.example.S1mulado.domain.testquestion.TestQuestion;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

    public record UpdatingTest(List<TestQuestion> testQuestions, LocalTime usedTime) {
}
