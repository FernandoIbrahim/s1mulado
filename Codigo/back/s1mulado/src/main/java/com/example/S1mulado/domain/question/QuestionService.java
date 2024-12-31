package com.example.S1mulado.domain.question;

import com.example.S1mulado.domain.question.exception.QuestionNotFoundException;
import com.example.S1mulado.domain.subject.KnowledgeArea;
import com.example.S1mulado.util.PropertiesUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    public Question create(Question question){

        return questionRepository.save(question);

    }


    public List<Question> findAll(){
        return questionRepository.findAll();
    }

    public Question findById(Long id){
        return questionRepository.findById(id)
                .orElseThrow( () -> new QuestionNotFoundException(String.format("Question with id %s not found", id)));

    }

    public Question update(Question questionData, Long id){

        Question questionToUpdate = findById(id);

        BeanUtils.copyProperties(questionData, questionToUpdate, PropertiesUtils.getNullPropertyNames(questionData));

        return questionRepository.save(questionToUpdate);

    }


    public List<Question> getRandomQuestions(Long total, KnowledgeArea knowledgeArea) {

        return questionRepository.getRandomQuestions(total, knowledgeArea.name());

    }


    public List<Question> getRandomQuestions(Long total, String subjectName) {

        return questionRepository.getRandomQuestionsBySubject(total, subjectName);

    }


}
