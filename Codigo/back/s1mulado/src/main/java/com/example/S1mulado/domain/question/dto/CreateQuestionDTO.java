package com.example.S1mulado.domain.question.dto;

import com.example.S1mulado.domain.question.alternative.Alternative;
import com.example.S1mulado.domain.question.images.QuestionImage;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateQuestionDTO {

    private Long year;

    private Long subjectId;

    private String title;

    private String context;

    private String knowledgeArea;

    private String alternativesIntroduction;

    private String correctAnswer;

    private List<Alternative> alternatives;

    private List<QuestionImage> images;

}
