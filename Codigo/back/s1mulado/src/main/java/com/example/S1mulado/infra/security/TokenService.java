package com.example.S1mulado.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.S1mulado.domain.user.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(User user) {

        try {

            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.create()
                    .withIssuer("auth-api")
                    .withSubject(user.getUsername())
                    .withClaim("role", user.getRole().toString())
                    .withExpiresAt(getExpirationDate())
                    .sign(algorithm);

        }catch (JWTCreationException e) {

            throw  new RuntimeException("Error while generating token" + e);

        }

    }

    public String validateToken(String token) {

        try {

            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token)
                    .getSubject();

        }catch (JWTVerificationException e) {

            throw new RuntimeException("Error while validating token" + e);

        }


    }


    public Instant getExpirationDate() {

       return LocalDateTime.now().plusHours(24).toInstant(ZoneOffset.of("-03:00"));

    }

}
