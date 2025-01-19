package com.example.S1mulado.domain.question;

import com.example.S1mulado.domain.question.alternative.Alternative;
import com.example.S1mulado.domain.question.dto.CreateQuestionDTO;
import com.example.S1mulado.domain.question.exception.QuestionNotFoundException;
import com.example.S1mulado.domain.question.images.QuestionImage;
import com.example.S1mulado.domain.subject.KnowledgeArea;
import com.example.S1mulado.domain.subject.Subject;
import com.example.S1mulado.domain.subject.SubjectRepository;
import com.example.S1mulado.domain.subject.SubjectService;
import com.example.S1mulado.util.AssociationUtils;
import com.example.S1mulado.util.PropertiesUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    SubjectService subjectService;

    public Question create(CreateQuestionDTO questionData){

        Question newQuestion = new Question(questionData);
        newQuestion = questionRepository.save(newQuestion);

        if(questionData.getSubjectId() != null){
            Subject subject = subjectService.findById(questionData.getSubjectId());
            newQuestion.setSubject(subject);
        }

        AssociationUtils.associateParent(newQuestion, newQuestion.getAlternatives());
        AssociationUtils.associateParent(newQuestion, newQuestion.getImages());

        return questionRepository.save(newQuestion);

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


    public List<Question> getQuestionsByIds(List<Long> ids){
            return questionRepository.findAllById(ids);
    }



    public List<Question> getRandomQuestionsBySubjectName(String name, int quantity) {
        List<Long> ids = questionRepository.findQuestionIdsBySubjectName("%"+name+"%", 2);
        return getQuestionsByIds(ids);

    }


}
