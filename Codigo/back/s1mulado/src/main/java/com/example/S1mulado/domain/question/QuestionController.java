package com.example.S1mulado.domain.question;

import com.example.S1mulado.domain.question.dto.CreateQuestionDTO;
import com.example.S1mulado.domain.question.exception.QuestionNotFoundException;
import com.example.S1mulado.domain.subject.Subject;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class QuestionController {


    @Autowired
    private QuestionService questionService;

    @PostMapping("/questions")
    public ResponseEntity<Question> post(@Valid @RequestBody CreateQuestionDTO dto){

        Question newQuestion = questionService.create(dto);

        return ResponseEntity.ok().body(newQuestion);

    }

    @GetMapping("/questions")
    public ResponseEntity<List<Question>> getAll(){

       List<Question> foundQuestions = questionService.findAll();

       return ResponseEntity.ok().body(foundQuestions);

    }


    @GetMapping("/questions/{id}")
    public ResponseEntity<Question> getOne(@PathVariable Long id) {

        Question foundQuestion = questionService.findById(id);

        return ResponseEntity.ok().body(foundQuestion);

    }


    @PatchMapping("/questions/{id}")
    public ResponseEntity<Question> patchMapping(@Valid @RequestBody Question questionData, @PathVariable Long id) {

        Question question = questionService.update(questionData, id);

        return ResponseEntity.ok(question);

    }


}
