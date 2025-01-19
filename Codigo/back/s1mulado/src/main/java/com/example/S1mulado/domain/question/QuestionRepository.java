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
    FROM questions q
    INNER JOIN subject s ON q.subject_id = s.id
    WHERE s.name LIKE :subjectName
    ORDER BY RANDOM()
    LIMIT :quantity """, nativeQuery = true)
    List<Question> getRandomQuestionsBySubject(
            @Param("quantity") Long quantity,
            @Param("subjectName") String subjectName
    );


    @Query(value = """
        WITH RECURSIVE subject_tree AS (
            -- Inicia com o sujeito pai
            SELECT id
            FROM subjects
            WHERE name ILIKE :fatherSubject
            UNION ALL
            -- Recupera todos os sujeitos filhos (e seus filhos, e assim por diante)
            SELECT s.id
            FROM subjects s
            INNER JOIN subject_tree st ON s.father_subject_id = st.id
        )
        -- Agora, busca os IDs das questões associadas a qualquer um dos sujeitos filhos
        SELECT q.id
        FROM questions q
        INNER JOIN subjects s ON q.subject_id = s.id
        WHERE s.id IN (SELECT id FROM subject_tree)
        ORDER BY random()
        LIMIT :quantity
    """, nativeQuery = true)
    List<Long> findQuestionIdsBySubjectName(@Param("fatherSubject") String fatherSubject, @Param("quantity") int quantity);


}
