package com.example.S1mulado.domain.test.result.dto;

import com.example.S1mulado.domain.subject.KnowledgeArea;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TestResultDTO {

    private Long id;
    private boolean concluded;
    private LocalDateTime date;
    private Long userId;
    private Long totalQuestions;
    private Long correctAnswers;
    private KnowledgeArea knowledgeArea;

}
