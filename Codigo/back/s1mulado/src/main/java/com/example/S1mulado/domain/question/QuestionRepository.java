package com.example.S1mulado.domain.question;

import com.example.S1mulado.domain.subject.KnowledgeArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Query(value = "SELECT * FROM questions WHERE knowledge_area = :knowledgeArea ORDER BY RANDOM() LIMIT :quantity", nativeQuery = true)
    List<Question> getRandomQuestions(
            @Param("quantity") Long quantity,
            @Param("knowledgeArea") String knowledgeArea
    );

    @Query(value = """
    SELECT
        q.id AS id,
        answer1,
        answer2,
        answer3,
        answer4,
        answer5,
        header,
        q.knowledge_area as knowledge_area,
        title,
        s.id AS subject_id,
        s.name AS subject_name
    FROM questions q
    INNER JOIN subject s ON q.subject_id = s.id
    WHERE s.name LIKE :subjectName
    ORDER BY RANDOM()
    LIMIT :quantity """, nativeQuery = true)
    List<Question> getRandomQuestionsBySubject(
            @Param("quantity") Long quantity,
            @Param("subjectName") String subjectName
    );


}
