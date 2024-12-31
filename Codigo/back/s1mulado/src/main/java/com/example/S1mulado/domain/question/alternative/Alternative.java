package com.example.S1mulado.domain.question.alternative;

import com.example.S1mulado.domain.question.Question;
import com.example.S1mulado.domain.question.images.QuestionImage;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "alternatives")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Alternative {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String text;

    private Boolean isCorrect;

    @Column(columnDefinition = "TEXT")
    private String letter;

    @Column(columnDefinition = "TEXT")
    private String imageUrl;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "question_id")
    private Question question;

}
