package com.example.S1mulado.domain.test.result;

import com.example.S1mulado.domain.user.User;
import com.example.S1mulado.domain.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("me/test-history")
public class TestResultController {

    @Autowired
    public TestResultService testResultService;


    @GetMapping
    public ResponseEntity<List<TestResultDTO>> getTestHistory(@AuthenticationPrincipal UserDetails userDetails) {

        try {
            List<TestResultDTO> results = testResultService.getTestHistory(userDetails);
            return ResponseEntity.ok(results);

        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        }

    }

}