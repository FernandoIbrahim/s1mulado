package com.example.S1mulado.domain.question.images;

import com.example.S1mulado.domain.question.Question;
import com.example.S1mulado.domain.question.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.Optional;

@RestController
public class QuestionImageController {

    @Autowired
    private QuestionImageRepository questionImageRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Value("${image.upload-dir}")
    private String imageUploadDir;


    @PostMapping("/questions/images")
    public ResponseEntity<QuestionImage> save(
            @RequestParam("file") MultipartFile file,
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("questionId") Long questionId) {

        try {

            String imageUrl = saveImage(file);

            Optional<Question> optionalQuestion = questionRepository.findById(questionId);


            if (optionalQuestion.isEmpty()) {
                return ResponseEntity.notFound().build();

            }


            QuestionImage questionImage = new QuestionImage();
            questionImage.setUrl(imageUrl);
            questionImage.setTitle(title);
            questionImage.setDescription(description);
            questionImage.setQuestion(optionalQuestion.get());
            QuestionImage savedQuestionImage = questionImageRepository.save(questionImage);

            return ResponseEntity.ok(savedQuestionImage);

        } catch (IOException e) {
            return ResponseEntity.status(500).body(null);
        }

    }

    private String saveImage(MultipartFile file) throws IOException {

        Path imageUploadPath = Paths.get(imageUploadDir);

        if (!Files.exists(imageUploadPath)) {
            Files.createDirectories(imageUploadPath);
        }

        String fileName = LocalDateTime.now() + file.getOriginalFilename();
        Path filePath = imageUploadPath.resolve(fileName);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        return filePath.toString();

    }

}
