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


    @PostMapping("/tests/{id}/end")
    public ResponseEntity<Test> finalizeTest(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {

        System.out.println(id);
        Test test = testService.finalize(id);
        return ResponseEntity.ok(test);

    }

    @PatchMapping("/tests/test-questions")
    public ResponseEntity<Test> answeringTestQuestion( @RequestBody UpdateTestQuestionDTO updateTestQuestionDTO) {

         testQuestionService.answerQuestion(updateTestQuestionDTO);
         return ResponseEntity.ok().build();

    }


}
