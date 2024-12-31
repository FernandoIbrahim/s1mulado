package com.example.S1mulado.domain.question;

import com.example.S1mulado.domain.question.alternative.Alternative;
import com.example.S1mulado.domain.question.images.QuestionImage;
import com.example.S1mulado.domain.subject.KnowledgeArea;
import com.example.S1mulado.domain.subject.Subject;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "questions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor 
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long year;

    @Enumerated(EnumType.STRING)
    private KnowledgeArea knowledgeArea;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @Column(columnDefinition = "TEXT")
    private String title;

    @Column(columnDefinition = "TEXT")
    private String context;

    @Column(columnDefinition = "TEXT")
    private String alternativesIntroduction;

    @Column(columnDefinition = "TEXT")
    private String correctAwnser;

    @OneToMany(mappedBy = "question")
    private List<Alternative> alternatives;

    @OneToMany(mappedBy = "question")
    private List<QuestionImage> images;


}
