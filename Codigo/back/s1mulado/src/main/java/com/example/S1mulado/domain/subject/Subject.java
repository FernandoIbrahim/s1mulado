package com.example.S1mulado.domain.subject;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private KnowledgeArea knowledgeArea;

    @ManyToOne
    @JoinColumn(name = "father_subject_id")
    private Subject fatherSubject;

}
