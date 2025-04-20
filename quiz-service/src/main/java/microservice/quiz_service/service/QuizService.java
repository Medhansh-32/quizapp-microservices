package microservice.quiz_service.service;

import microservice.quiz_service.dto.Response;
import microservice.quiz_service.feign.QuizInterface;
import microservice.quiz_service.dto.QuestionDto;
import microservice.quiz_service.entity.Quiz;
import microservice.quiz_service.repository.QuizRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuizService {


    private final QuizRepository quizRepository;
    private QuizInterface quizInterface;

    public QuizService(QuizRepository quizRepository, QuizInterface quizInterface) {

        this.quizRepository = quizRepository;
        this.quizInterface = quizInterface;
    }

    public ResponseEntity<Quiz> createQuiz(String category, Integer numQ, String title) {

        List<Integer> questions = quizInterface.getQuestionsForQuiz(category, numQ).getBody();
        Quiz quiz=new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);

       return new ResponseEntity<>(quizRepository.save(quiz),HttpStatus.OK);


    }

    public ResponseEntity<List<QuestionDto>> getQuizQuestions(Integer id) {

        List<Integer> questionList=quizRepository.findById(id).get().getQuestions();
        return  quizInterface.getQuestionsFromIds(questionList);
    }

    public ResponseEntity<Integer> getScore(List<Response> responses, Integer id) {
    return quizInterface.getQuestionScore(responses);
    }
}
