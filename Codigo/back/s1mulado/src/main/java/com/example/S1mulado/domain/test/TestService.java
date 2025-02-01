package com.example.S1mulado.domain.test;

import com.example.S1mulado.domain.question.Question;
import com.example.S1mulado.domain.question.QuestionRepository;
import com.example.S1mulado.domain.question.QuestionService;
import com.example.S1mulado.domain.test.dto.CreateTestDTO;
import com.example.S1mulado.domain.test.dto.UpdateTestDTO;
import com.example.S1mulado.domain.test.exception.TestNotFoundException;
import com.example.S1mulado.domain.testquestion.TestQuestion;
import com.example.S1mulado.domain.testquestion.TestQuestionService;
import com.example.S1mulado.domain.testquestion.dto.UpdateTestQuestionDTO;
import com.example.S1mulado.domain.user.User;
import com.example.S1mulado.domain.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

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
        newTest.setUser(user);

        return testRepository.save(newTest);

    }


    public List<Test> findAll(){

        return testRepository.findAll();

    }

    public Test findById(Long id) {

        return testRepository.findById(id)
                .orElseThrow(() -> new TestNotFoundException(String.format("Test with id %s not found", id)));

    }

    public Test findCurrentTest(User user){

        return testRepository.findTestByUserAndConcluded(user, false).getFirst();

    }

    public boolean hasCurrentTest(User user) {

        return !testRepository.findTestByUserAndConcluded(user, false).isEmpty();

    }




    public Test answerTest(Long id, UpdateTestDTO testData){

        Test test = findById(id);
        LocalTime usedTime  = LocalTime.parse(testData.usedTime());
        test.setUsedTime(usedTime);

        testQuestionService.validateAnswers(testData.questionsAnswers());

        return testRepository.save(test);

    }

    public void answerQuestion(Long id, UpdateTestQuestionDTO testQuestionData){


        testQuestionService.answerQuestion(testQuestionData);


    }


    public List<Test> findOwnUserTests(User user) {
        return testRepository.findTestByUser(user);
    }


}
