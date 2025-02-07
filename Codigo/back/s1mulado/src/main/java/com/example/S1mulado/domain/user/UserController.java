package com.example.S1mulado.domain.user;

import com.example.S1mulado.domain.test.Test;
import com.example.S1mulado.domain.test.TestService;
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
    UserService userService;

    @Autowired
    private TestService testService;

    @GetMapping("/me")
    public ResponseEntity<OwnUserData> getMe(@AuthenticationPrincipal UserDetails userDetails){

        User user = userService.findLoggedUser(userDetails);

        OwnUserData ownUserData = userService.findOwnUserData(user);

        return ResponseEntity.ok(ownUserData);

    }


    @GetMapping("/me/tests")
    public ResponseEntity<List<Test>> getMeTests(@AuthenticationPrincipal UserDetails userDetails){

        User user = userService.findLoggedUser(userDetails);

        List<Test> tests = testService.findOwnUserTests(user);

        return ResponseEntity.ok(tests);

    }

    @GetMapping("/me/current-test")
    public ResponseEntity<Test> getMyCurrentTest(@AuthenticationPrincipal UserDetails userDetails){

        User user = userService.findLoggedUser(userDetails);
        Test test = testService.findCurrentTest(user);

        return ResponseEntity.ok(test);

    }


    @PatchMapping("/me")
    public ResponseEntity<OwnUserData> putMe(@AuthenticationPrincipal UserDetails userDetails, @RequestBody OwnUserData newData){

        User user = userService.findLoggedUser(userDetails);

        OwnUserData updatedUserData = userService.updateOwnUserData(user, newData);

        return ResponseEntity.ok(updatedUserData);

    }


}
