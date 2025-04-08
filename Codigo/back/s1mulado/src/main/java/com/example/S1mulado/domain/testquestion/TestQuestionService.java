package com.example.S1mulado.domain.testquestion;

import com.example.S1mulado.domain.question.Question;
import com.example.S1mulado.domain.test.Test;
import com.example.S1mulado.domain.test.TestRepository;
import com.example.S1mulado.domain.test.TestService;
import com.example.S1mulado.domain.test.exception.TestNotFoundException;
import com.example.S1mulado.domain.testquestion.dto.UpdateTestQuestionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TestQuestionService {

    @Autowired
    private TestQuestionRepository testQuestionRepository;

    @Autowired
    private TestRepository testRepository;




    /**
     * Validates the answers for a list of test questions.
     *
     * <p>This method iterates through each {@link TestQuestion} in the provided list,
     * compares the given answer with the correct answer, marks the question as correct or incorrect,
     * and updates the record in the database.</p>
     *
     * @param testQuestions The list of {@link TestQuestion} entities to be validated.
     */
    public void validateAnswers(List<TestQuestion> testQuestions) {

        for (TestQuestion testQuestion : testQuestions) {

            testQuestion.setCorrect(testQuestion.getAnswer().equals(testQuestion.getQuestion().getCorrectAwnser()));

            testQuestionRepository.save(testQuestion);

        }

    }


    /**
     * Creates and persists the relationship between a test and its questions.
     *
     * <p>This method iterates through a list of {@link Question} entities,
     * creates a corresponding {@link TestQuestion} for each one, associates it with the given {@link Test},
     * saves it in the database, and returns the list of created relationships.</p>
     *
     * @param questions The list of {@link Question} entities to be associated with the test.
     * @param test The {@link Test} entity to which the questions will be linked.
     * @return A list of {@link TestQuestion} entities representing the test-question relationships.
     */
    public List<TestQuestion> generateTestQuestionRelationship(List<Question> questions, Test test){

        List<TestQuestion> testQuestions = new ArrayList<>();

        for(Question question : questions){

            TestQuestion testQuestion = new TestQuestion();
            testQuestion.setQuestion(question);
            testQuestion.setTest(test);

            testQuestion = testQuestionRepository.save(testQuestion);

            testQuestions.add(testQuestion);

        }

        return testQuestions;

    }


    /**
     * Updates the answer for a specific test question and records the time spent on the test.
     *
     * <p>This method retrieves a {@link TestQuestion} by its ID, updates its answer,
     * and saves the changes. Additionally, it updates the used time for the associated {@link Test}.</p>
     *
     * @param updateTestQuestionData The {@link UpdateTestQuestionDTO} containing the question ID,
     *        the provided answer, and the time spent on the test.
     * @throws TestNotFoundException If the test question with the given ID is not found.
     */
    public void answerQuestion(UpdateTestQuestionDTO updateTestQuestionData) {

        Long id = updateTestQuestionData.id();
        TestQuestion testQuestion = testQuestionRepository.findById(id).orElseThrow((() -> new TestNotFoundException(String.format("test_question with id %s not found", id))));
        testQuestion.setAnswer(updateTestQuestionData.answer());
        testQuestionRepository.save(testQuestion);

        Test test = testQuestion.getTest();
        test.setUsedTime(LocalTime.parse(updateTestQuestionData.usedTime()));
        testRepository.save(test);

    }

}
