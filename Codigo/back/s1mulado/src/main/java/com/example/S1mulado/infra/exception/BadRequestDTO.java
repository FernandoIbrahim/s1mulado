package com.example.S1mulado.infra.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatusCode;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BadRequestDTO {

    private int status;
    private String error;
    private List<String> errorsMessages;
    private String path;

}
