package com.example.S1mulado.domain.test;

import com.example.S1mulado.domain.question.Question;
import com.example.S1mulado.domain.question.QuestionService;
import com.example.S1mulado.domain.test.dto.TestDTO;
import com.example.S1mulado.domain.test.dto.UpdateTestDTO;
import com.example.S1mulado.domain.test.exception.TestNotFoundException;
import com.example.S1mulado.domain.testquestion.TestQuestion;
import com.example.S1mulado.domain.testquestion.TestQuestionRepository;
import com.example.S1mulado.domain.testquestion.TestQuestionService;
import com.example.S1mulado.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TestService {

    @Autowired
    private TestRepository testRepository;


    @Autowired
    private QuestionService questionService;


    @Autowired
    private TestQuestionService testQuestionService;


    public Test create(TestDTO testData) {

        Test newTest = testRepository.save(new Test());

        List<Question> questions = questionService.getRandomQuestions(testData.getQuestionsNumber(), testData.getKnowledgeArea());

        List<TestQuestion> testQuestions = testQuestionService.generateTestQuestionRelationship(questions, newTest);

        newTest.setTestQuestions(testQuestions);
        newTest.setQuestionsNumber(testData.getQuestionsNumber());
        newTest.setDate(LocalDateTime.now());

        return testRepository.save(newTest);

    }


    public List<Test> findAll(){

        return testRepository.findAll();

    }

    public Test findById(Long id) {

        return testRepository.findById(id)
                .orElseThrow(() -> new TestNotFoundException(String.format("Test with id %s not found", id)));

    }


    public Test answerTest(Long id, UpdateTestDTO testData){

        Test test = findById(id);
        LocalTime usedTime  = LocalTime.parse(testData.usedTime());
        test.setUsedTime(usedTime);

        testQuestionService.validateAnswers(testData.updateTestQuestionDTOS());

        return testRepository.save(test);

    }


    public List<Test> findOwnUserTests(User user) {
        return testRepository.findTestByUser(user);
    }


}
