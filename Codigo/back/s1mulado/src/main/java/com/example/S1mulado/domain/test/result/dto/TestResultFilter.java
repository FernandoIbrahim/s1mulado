package com.example.S1mulado.domain.test.result.dto;

import com.example.S1mulado.domain.subject.KnowledgeArea;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class TestResultFilter {

    private KnowledgeArea knowledgeArea;
    private LocalDateTime minDate;
    private LocalDateTime maxDate;

    public TestResultFilter(String knowledgeArea, String minDate, String maxDate) {

        if(knowledgeArea != null){
            this.knowledgeArea = KnowledgeArea.valueOf(knowledgeArea);
        }

        this.minDate = LocalDateTime.parse(minDate);
        this.maxDate = LocalDateTime.parse(maxDate);

    }

}
