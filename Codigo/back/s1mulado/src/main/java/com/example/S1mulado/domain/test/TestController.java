package com.example.S1mulado.domain.test;


import com.example.S1mulado.domain.test.dto.CreateTestDTO;
import com.example.S1mulado.domain.test.dto.UpdateTestDTO;
import com.example.S1mulado.domain.testquestion.TestQuestionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TestController {

    @Autowired
    private TestRepository testRepository;

    @Autowired
    private TestService testService;

    @Autowired
    private TestQuestionService testQuestionService;



    @PostMapping("/tests")
    public ResponseEntity<Test> post(@Valid @RequestBody CreateTestDTO testData) {

        Test newTest = testService.create(testData);
        return ResponseEntity.ok(newTest);

    }

    @GetMapping("/tests")
    public ResponseEntity<List<Test>> getAll() {

        List<Test> tests = testService.findAll();

        return ResponseEntity.ok(tests);

    }

    @GetMapping("/tests/{id}")
    public ResponseEntity<Test> getOne(@PathVariable Long id) {

        Test test = testService.findById(id);

        return ResponseEntity.ok(test);

    }


    /*
        Atualiza o tempo da realização do teste e as respostas.
     */
    @PatchMapping("/test/{id}")
    public ResponseEntity<Test> answeringTest(@PathVariable Long id, @RequestBody UpdateTestDTO updateTestDTO) {

        Test test = testService.answerTest(id, updateTestDTO);

        return ResponseEntity.ok(test);

    }


}
