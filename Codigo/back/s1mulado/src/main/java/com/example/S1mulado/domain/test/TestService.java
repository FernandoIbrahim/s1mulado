package com.example.S1mulado.domain.test;

import com.example.S1mulado.domain.question.Question;
import com.example.S1mulado.domain.question.QuestionService;
import com.example.S1mulado.domain.test.dto.CreateTestDTO;
import com.example.S1mulado.domain.test.exception.TestNotFoundException;
import com.example.S1mulado.domain.testquestion.TestQuestion;
import com.example.S1mulado.domain.testquestion.TestQuestionService;
import com.example.S1mulado.domain.user.User;
import com.example.S1mulado.domain.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TestService {

    @Autowired
    private TestRepository testRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private QuestionService questionService;


    @Autowired
    private TestQuestionService testQuestionService;



    public Test create(CreateTestDTO testData, UserDetails userDetails) {

        User user = userService.findLoggedUser(userDetails);

        if(hasCurrentTest(user)){
            System.out.println("oi");
            throw new TestNotFoundException("O usuário já tem um teste em progresso");
        }

        Test newTest = testRepository.save(new Test());

        //List<Question> questions = questionService.getRandomQuestions(testData.getQuestionsNumber(), testData.getKnowledgeArea());
        List<Question> questions = questionService.getRandomQuestionsBySubjectName("biologia",  (int) testData.getQuestionsNumber());

        List<TestQuestion> testQuestions = testQuestionService.generateTestQuestionRelationship(questions, newTest);

        newTest.setTestQuestions(testQuestions);
        newTest.setQuestionsNumber(testData.getQuestionsNumber());
        newTest.setDate(LocalDateTime.now());
        newTest.setConcluded(false);
        newTest.setKnowledgeArea(testData.getKnowledgeArea());
        newTest.setUser(user);

        return testRepository.save(newTest);

    }


    public List<Test> findAll(){

        return testRepository.findAll();

    }

    public Test findById(Long id) {

        System.out.println(id);
        return testRepository.findById(id)
                .orElseThrow(() -> new TestNotFoundException(String.format("Test with id %s not found", id)));

    }

    public Test findCurrentTest(User user){

        if(hasCurrentTest(user)){
            return testRepository.findTestByUserAndConcluded(user, false).getFirst();
        }
        return null;

    }

    public boolean hasCurrentTest(User user) {

        return !testRepository.findTestByUserAndConcluded(user, false).isEmpty();

    }


    public Test finalize(Long id){

        Test test = findById(id);
        test.setConcluded(true);
        testQuestionService.validateAnswers(test.getTestQuestions());
        testRepository.save(test);

        return test;

    }


    public List<Test> findOwnUserTests(User user) {
        return testRepository.findTestByUser(user);
    }


}
