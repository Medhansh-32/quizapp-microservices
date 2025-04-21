FROM openjdk:17

ADD target/quiz-service.jar  quiz-service.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar","quiz-service.jar"]