package com.example.S1mulado.domain.auth;

import com.example.S1mulado.domain.auth.dto.AuthenticationDTO;
import com.example.S1mulado.domain.auth.dto.LoginReponseDTO;
import com.example.S1mulado.domain.auth.dto.RegisterDTO;
import com.example.S1mulado.domain.user.Role;
import com.example.S1mulado.domain.user.User;
import com.example.S1mulado.domain.user.UserRepository;
import com.example.S1mulado.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginReponseDTO> login(@RequestBody @Valid AuthenticationDTO data){

        var usernamePassword = new UsernamePasswordAuthenticationToken(data.username(), data.password());
        var auth = authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User)auth.getPrincipal());

        return ResponseEntity.ok(new LoginReponseDTO(token));

    }


    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data){

        if(userRepository.findByUsername(data.username()) != null){

            return ResponseEntity.badRequest().build();

        }

        String encrytedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.username(), data.email(), data.phoneNumber(), encrytedPassword, Role.STUDENT);

        userRepository.save(newUser);

        return ResponseEntity.ok().build();

    }


}
