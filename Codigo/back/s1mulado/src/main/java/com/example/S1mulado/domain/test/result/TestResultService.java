package com.example.S1mulado.domain.test.result;

import com.example.S1mulado.domain.user.User;
import com.example.S1mulado.domain.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestResultService {

    @Autowired
    private TestResultRepository testResultRepository;

    @Autowired
    private UserService userService;

    public List<TestResultDTO> getTestHistory(UserDetails userDetails) {

        User user = userService.findLoggedUser(userDetails);

        return testResultRepository.findTestResultHistory(user.getId());

    }

}
