package com.example.S1mulado.domain.question;

import com.example.S1mulado.domain.question.exception.QuestionNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class QuestionController {

    @Autowired
    QuestionRepository questionRepository;

    @PostMapping("/questions")
    public  Question post(@Valid @RequestBody Question question){

        Question newQuestion = questionRepository.save(question);

        return newQuestion;

    }

    @GetMapping("/questions")
    public List<Question> getAll(){

        System.out.println(questionRepository.findAll());
        return questionRepository.findAll();

    }


    @GetMapping("/questions/{id}")
    public Question getOne(@PathVariable Long id) {

        return questionRepository.findById(id)
                .orElseThrow(() -> new QuestionNotFoundException(String.format("Question with id %s not found", id)));
    }


    @PutMapping("/questions/{id}")
    public ResponseEntity<Question> put(@Valid @RequestBody Question newQuestion, @PathVariable Long id) {

        Question existingQuestion = questionRepository.findById(id)
                .orElseThrow( () -> new QuestionNotFoundException(String.format("Question with id %s not found", id)));

        existingQuestion.setYear(newQuestion.getYear());
        existingQuestion.setKnowledgeArea(newQuestion.getKnowledgeArea());
        existingQuestion.setSubject(newQuestion.getSubject());
        existingQuestion.setTitle(newQuestion.getTitle());
        existingQuestion.setContext(newQuestion.getContext());
        existingQuestion.setAlternativesIntroduction(newQuestion.getAlternativesIntroduction());
        existingQuestion.setAlternatives(newQuestion.getAlternatives());

        questionRepository.save(existingQuestion);

        return ResponseEntity.ok(existingQuestion);

    }


}
