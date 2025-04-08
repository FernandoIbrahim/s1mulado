package com.example.S1mulado.domain.test.result;

import com.example.S1mulado.domain.subject.KnowledgeArea;
import com.example.S1mulado.domain.test.result.dto.TestResultFilter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class TestResultRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Page<TestResultDTO> findTestResultHistory(Long userId, TestResultFilter testResultFilter, Pageable pageable) {

        String sql = """
            SELECT 
                t.id AS testId,
                t.concluded AS concluded,
                t.date AS date, 
                t.user_id AS userId,
                COUNT(tq.id) AS totalQuestions,
                SUM(CASE WHEN tq.is_correct = TRUE THEN 1 ELSE 0 END) AS correctAnswers,
                t.knowledge_area
            FROM tests t
            INNER JOIN test_question tq ON t.id = tq.test_id
            WHERE t.user_id = :userId 
                AND t.concluded = true
                    AND t.date BETWEEN COALESCE(:startDate, t.date) AND COALESCE(:endDate, t.date)
                AND (:knowledgeArea IS NULL OR t.knowledge_area = :knowledgeArea ::varchar)
            GROUP BY t.id
            ORDER BY t.date DESC
        """;

        Query query = entityManager.createNativeQuery(sql)
                .setParameter("userId", userId)
                .setParameter("startDate", testResultFilter.getMinDate())
                .setParameter("endDate", testResultFilter.getMaxDate())
                .setParameter("knowledgeArea", testResultFilter.getKnowledgeArea().toString())
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize());

        List<Object[]> results = query.getResultList();

        List<TestResultDTO > testResults =  results.stream().map(row -> new TestResultDTO(
                ((Number) row[0]).longValue(),
                (Boolean) row[1],
                ((java.sql.Timestamp) row[2]).toLocalDateTime(),
                ((Number) row[3]).longValue(),
                ((Number) row[4]).longValue(),
                ((Number) row[5]).longValue(),
                KnowledgeArea.valueOf((String) row[6])
        )).toList();

        String countSql = """
                SELECT COUNT(DISTINCT t.id)\s
                FROM tests t
                WHERE t.user_id = :userId
                    AND t.concluded = true
                    AND t.date >= COALESCE(:startDate, t.date)
                    AND t.date <= COALESCE(:endDate, t.date)
                    AND (:knowledgeArea IS NULL OR t.knowledge_area = :knowledgeArea ::varchar)
        """;

        Query countQuery = entityManager.createNativeQuery(countSql)
                .setParameter("userId", userId)
                .setParameter("startDate", testResultFilter.getMinDate())
                .setParameter("endDate", testResultFilter.getMaxDate())
                .setParameter("knowledgeArea", testResultFilter.getKnowledgeArea().toString());

        long total = ((Number) countQuery.getSingleResult()).longValue();
        System.out.println(total);
        return new PageImpl<TestResultDTO>(testResults, pageable, total);

    }

}
