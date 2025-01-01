package com.example.S1mulado.domain.test.dto;

import com.example.S1mulado.domain.subject.KnowledgeArea;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateTestDTO {

    @Valid
    @NotNull(message = "The number of questions is required.")
    private Long questionsNumber;


    @Valid
    @NotNull(message = "The knowledgeArea is required.")
    private  KnowledgeArea knowledgeArea;

}
