package microservice.quiz_service.controller;

import microservice.quiz_service.dto.QuestionDto;
import microservice.quiz_service.dto.QuizDto;
import microservice.quiz_service.dto.Response;
import microservice.quiz_service.entity.Quiz;
import microservice.quiz_service.service.QuizService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @PostMapping("/create")
    public ResponseEntity<Quiz> create(@RequestBody QuizDto quizDto ) {

        return quizService.createQuiz(
                quizDto.categoryName(),
                quizDto.numQ(),
                quizDto.title()
        );

    }

    @GetMapping("/get/{id}")
    public ResponseEntity<List<QuestionDto>> getQuiz(@PathVariable Integer id) {
        return quizService.getQuizQuestions(id);
    }

    @PostMapping("/submit/{id}")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses, @PathVariable Integer id)
    {
        return quizService.getScore(responses,id);
    }
}
