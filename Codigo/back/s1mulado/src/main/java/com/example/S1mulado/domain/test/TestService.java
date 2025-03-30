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


    /**
     * Creates a new test for the logged-in user.
     *
     * <p>This method first retrieves the logged-in user and verifies if they already have an ongoing test.
     * If the user has an active test, a {@link TestNotFoundException} is thrown. Otherwise, a new test is created
     * and associated with randomly selected questions based on a specified subject.</p>
     *
     * @param testData The data required to create the test, including the number of questions and knowledge area.
     * @param userDetails The details of the logged-in user.
     * @return The newly created {@link Test} entity with associated questions.
     * @throws TestNotFoundException If the user already has an ongoing test.
     */
    public Test create(CreateTestDTO testData, UserDetails userDetails) {

        User user = userService.findLoggedUser(userDetails);

        if(hasCurrentTest(user)){
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


    /**
     * Retrieves the current ongoing test for a given user.
     *
     * This method checks if the user has an active (not concluded) test. If a test is found,
     * it returns the first ongoing test associated with the user. Otherwise, it returns {@code null}.
     *
     * @param user The user whose ongoing test is being searched for.
     * @return The current ongoing {@link Test} for the user, or {@code null} if no active test exists.
     */
    public Test findCurrentTest(User user){

        if(hasCurrentTest(user)){
            return testRepository.findTestByUserAndConcluded(user, false).getFirst();
        }
        return null;

    }


    /**
     *
     * @param user
     * @return a boolean that identifies if the user has an open test
     */
    public boolean hasCurrentTest(User user) {

        return !testRepository.findTestByUserAndConcluded(user, false).isEmpty();

    }


    /**
     * Finalizes a test by marking it as concluded and validating the answers.
     *
     * <p>This method retrieves the test by its ID, marks it as concluded,
     * validates the answers of the associated test_questions, and then saves the updated test.</p>
     *
     * @param id The ID of the test to be finalized.
     * @return The updated {@link Test} entity after being marked as concluded.
     */
    public Test finalize(Long id){

        Test test = findById(id);
        test.setConcluded(true);
        testQuestionService.validateAnswers(test.getTestQuestions());
        testRepository.save(test);

        return test;

    }


    /**
     * Search for a user completed tests
     *
     * @param user
     * @return a List of completed {@link Test} done by the passed user
     */
    public List<Test> findOwnUserTests(User user) {
        return testRepository.findTestByUser(user);
    }


}
