package com.example.S1mulado.domain.user;

import com.example.S1mulado.domain.test.Test;
import com.example.S1mulado.domain.test.TestRepository;
import com.example.S1mulado.domain.user.dto.OwnUserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TestRepository testRepository;

    @GetMapping("/me")
    public ResponseEntity<OwnUserData> getMe(@AuthenticationPrincipal UserDetails userDetails){

        User user = (User)userDetails;

        String username = user.getUsername();
        String email = user.getEmail();
        String phoneNumber = user.getPhoneNumber();
        String role = user.getRole().toString();

        return ResponseEntity.ok(new OwnUserData(username, email, phoneNumber, role));

    }


    @GetMapping("/me/tests")
    public ResponseEntity<List<Test>> getMeTests(@AuthenticationPrincipal UserDetails userDetails){

        User user = (User)userDetails;

        List<Test> tests = testRepository.findTestByUser(user);

        return ResponseEntity.ok(tests);

    }

    @PutMapping("/me")
    public ResponseEntity<OwnUserData> putMe(@AuthenticationPrincipal UserDetails userDetails, @RequestBody OwnUserData ownUserData){

        User user = (User)userDetails;
        user.setEmail(ownUserData.email());
        user.setPhoneNumber(ownUserData.phoneNumber());

        userRepository.save(user);

        return ResponseEntity.ok(new OwnUserData(user.getUsername(), user.getEmail(), user.getPhoneNumber(), user.getRole().toString()));

    }

}
