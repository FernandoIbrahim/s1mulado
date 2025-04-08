package com.example.S1mulado.domain.test.result;

import com.example.S1mulado.domain.test.result.dto.TestResultFilter;
import com.example.S1mulado.domain.user.User;
import com.example.S1mulado.domain.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class TestResultController {

    @Autowired
    public TestResultService testResultService;


    @GetMapping("/users/me/test-history")
    public ResponseEntity<Page<TestResultDTO>> getTestHistory(@AuthenticationPrincipal UserDetails userDetails,
                                                              @RequestParam(name = "knowledgeArea", defaultValue = "MATHEMATICS") String knowledgeArea,
                                                              @RequestParam(name = "minDate", defaultValue = "2000-01-01T00:00:00") String minDate,
                                                              @RequestParam(name = "maxDate", defaultValue = "2100-01-01T00:00:00") String maxDate,
                                                              @PageableDefault Pageable pageable){


        try {

            TestResultFilter testResultFilter = new TestResultFilter(knowledgeArea, minDate, maxDate);
            Page<TestResultDTO> results = testResultService.getTestHistory(userDetails, testResultFilter ,pageable);

            return ResponseEntity.ok(results);

        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        }


    }

}