package com.example.S1mulado.domain.question.alternative;

import com.example.S1mulado.domain.question.Question;
import com.example.S1mulado.util.interfaces.Relatable;
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
public class Alternative implements Relatable<Question> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String text;

    private boolean isCorrect;

    @Column(columnDefinition = "TEXT")
    private String letter;

    @Column(columnDefinition = "TEXT")
    private String imageUrl;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "question_id")
    private Question question;


    @Override
    public void setParent(Question parent) {
        this.question = parent;
    }


}
