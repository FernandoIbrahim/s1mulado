package com.example.S1mulado.domain.test;


import com.example.S1mulado.domain.test.dto.CreateTestDTO;
import com.example.S1mulado.domain.test.dto.UpdateTestDTO;
import com.example.S1mulado.domain.testquestion.TestQuestionService;
import com.example.S1mulado.domain.testquestion.dto.UpdateTestQuestionDTO;
import com.example.S1mulado.domain.user.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TestController {


    @Autowired
    private TestService testService;

    @Autowired
    private TestQuestionService testQuestionService;



    @PostMapping("/tests")
    public ResponseEntity<Test> post(@AuthenticationPrincipal UserDetails userDetails, @Valid @RequestBody CreateTestDTO testData ) {

        try{
            Test newTest = testService.create(testData,userDetails);

            return ResponseEntity.ok(newTest);
        }catch (Exception e){

            return ResponseEntity.status(HttpStatus.CONFLICT).build();

        }


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
    @PatchMapping("/test/{id}")
    public ResponseEntity<Test> answeringTest(@PathVariable Long id, @RequestBody UpdateTestDTO updateTestDTO) {

        Test test = testService.answerTest(id, updateTestDTO);

        return ResponseEntity.ok(test);

    }

     */
    @PatchMapping("/test/test-question")
    public ResponseEntity<Test> answeringTestQuestion( @RequestBody UpdateTestQuestionDTO updateTestQuestionDTO) {

         testQuestionService.answerQuestion(updateTestQuestionDTO);
         return ResponseEntity.ok().build();

    }


}
