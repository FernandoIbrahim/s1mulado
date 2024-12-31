package com.example.S1mulado.domain.question;

import com.example.S1mulado.domain.subject.KnowledgeArea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;


    public List<Question> getRandomQuestions(Long total, KnowledgeArea knowledgeArea) {

        return questionRepository.getRandomQuestions(total, knowledgeArea.name());

    }


    public List<Question> getRandomQuestions(Long total, String subjectName) {

        return questionRepository.getRandomQuestionsBySubject(total, subjectName);

    }


}
