package com.example.S1mulado.domain.testquestion;

import com.example.S1mulado.domain.question.Question;
import com.example.S1mulado.domain.test.Test;
import com.example.S1mulado.domain.test.exception.TestNotFoundException;
import com.example.S1mulado.domain.testquestion.dto.UpdateTestQuestionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TestQuestionService {

    @Autowired
    private TestQuestionRepository testQuestionRepository;


    public void validateAnswers(List<UpdateTestQuestionDTO> testQuestionsData) {

        for (UpdateTestQuestionDTO testQuestionData : testQuestionsData) {

            TestQuestion oldTestQuestion = testQuestionRepository.findById(testQuestionData.id())
                    .orElseThrow((() -> new TestNotFoundException(String.format("test_question with id %s not found", testQuestionData.id()))));

            oldTestQuestion.setAnswer(testQuestionData.answer());
            oldTestQuestion.setCorrect(oldTestQuestion.getAnswer().equals(oldTestQuestion.getQuestion().getCorrectAwnser()));

            testQuestionRepository.save(oldTestQuestion);

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


}
