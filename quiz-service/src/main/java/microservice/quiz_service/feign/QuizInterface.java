package microservice.quiz_service.feign;


import microservice.quiz_service.dto.QuestionDto;
import microservice.quiz_service.dto.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {

    @GetMapping("/questions/generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String categoryName, @RequestParam Integer numQuestions );


    @PostMapping("/questions/getQuestions")
    public ResponseEntity<List<QuestionDto>> getQuestionsFromIds(@RequestBody List<Integer> questionIds);

    @PostMapping("/questions/getScore")
    public ResponseEntity<Integer> getQuestionScore(@RequestBody List<Response> responses) ;



}
