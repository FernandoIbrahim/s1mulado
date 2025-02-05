package com.example.S1mulado.domain.test;


import com.example.S1mulado.domain.question.Question;
import com.example.S1mulado.domain.subject.KnowledgeArea;
import com.example.S1mulado.domain.testquestion.TestQuestion;
import com.example.S1mulado.domain.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "tests")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDateTime date;

    private LocalTime testTime;

    private LocalTime usedTime;

    private Boolean concluded;

    private Long questionsNumber;

    @Enumerated(EnumType.STRING)
    private KnowledgeArea knowledgeArea;

    @OneToMany(mappedBy = "test")
    private List<TestQuestion> testQuestions;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


}
