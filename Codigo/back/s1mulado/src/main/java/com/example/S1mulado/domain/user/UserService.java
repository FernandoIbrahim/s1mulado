package com.example.S1mulado.domain.user;

import com.example.S1mulado.domain.test.Test;
import com.example.S1mulado.domain.test.TestService;
import com.example.S1mulado.domain.user.dto.OwnUserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    private TestService testService;

    public User FindLoggedUser(UserDetails userDetails) {
        return (User)userDetails;
    }


    public OwnUserData findOwnUserData(User user) {

        return new OwnUserData(user.getUsername(), user.getEmail(), user.getPhoneNumber(), user.getRole().toString());

    }

    public OwnUserData updateOwnUserData(User user, OwnUserData newData){

        user.setEmail(newData.email());
        user.setPhoneNumber(newData.phoneNumber());

        userRepository.save(user);

        return new OwnUserData(user.getUsername(), user.getEmail(), user.getPhoneNumber(), user.getRole().toString());

    }


}
