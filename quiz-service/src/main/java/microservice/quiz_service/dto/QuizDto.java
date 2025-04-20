package microservice.quiz_service.dto;

public record QuizDto(
        String categoryName,
        Integer numQ,
        String title
        ) {

}
