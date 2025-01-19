package com.example.S1mulado.domain.subject;

import com.example.S1mulado.domain.test.exception.TestNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    public Subject findById(Long id) {
        return subjectRepository.findById(id)
                .orElseThrow(() -> new TestNotFoundException(String.format("Subject with id %s not found", id)));
    }

}
