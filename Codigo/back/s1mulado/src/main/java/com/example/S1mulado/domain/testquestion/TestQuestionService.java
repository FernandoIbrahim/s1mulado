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


    public void validateAnswers(List<TestQuestion> testQuestions) {

        for (TestQuestion testQuestion : testQuestions) {

            testQuestion.setCorrect(testQuestion.getAnswer().equals(testQuestion.getQuestion().getCorrectAwnser()));

            testQuestionRepository.save(testQuestion);

        }

    }


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
