package microservice.question_service.repository;

import microservice.question_service.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface QuestionRepository  extends JpaRepository<Question, Integer> {

    @Query(value = "select id from question where category = :categoryName ORDER BY RANDOM() LIMIT :numQuestions",nativeQuery = true)
    List<Integer> findRandomQuestionsByCategory(String categoryName, Integer numQuestions);

    List<Question> findByCategory(String category);
}
