package com.example.S1mulado.domain.test.result;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class TestResultRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<TestResultDTO> findTestResultHistory(Long userId) {

        String sql = """
            SELECT 
                t.id AS testId,
                t.concluded AS concluded,
                t.date AS date,
                t.user_id AS userId,
                COUNT(tq.id) AS totalQuestions,
                SUM(CASE WHEN tq.is_correct = TRUE THEN 1 ELSE 0 END) AS correctAnswers
            FROM tests t
            INNER JOIN test_question tq ON t.id = tq.test_id
            WHERE t.user_id = :userId AND t.concluded = true
            GROUP BY t.id
        """;

        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("userId", userId);

        List<Object[]> results = query.getResultList();
        return results.stream().map(row -> new TestResultDTO(
                ((Number) row[0]).longValue(),
                (Boolean) row[1],
                ((java.sql.Timestamp) row[2]).toLocalDateTime(),
                ((Number) row[3]).longValue(),
                ((Number) row[4]).longValue(),
                ((Number) row[5]).longValue()
        )).toList();
    }

}
