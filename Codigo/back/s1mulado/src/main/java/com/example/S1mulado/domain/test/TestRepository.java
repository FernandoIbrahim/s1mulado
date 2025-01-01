package com.example.S1mulado.domain.test;

import com.example.S1mulado.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface
TestRepository extends JpaRepository<Test, Long> {

    List<Test> findTestByUser(User user);

}
